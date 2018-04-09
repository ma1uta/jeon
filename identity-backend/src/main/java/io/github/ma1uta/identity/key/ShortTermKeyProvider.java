/*
 * Copyright sablintolya@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.ma1uta.identity.key;

import io.github.ma1uta.identity.configuration.KeyStoreConfiguration;
import io.github.ma1uta.jeon.exception.MatrixException;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Key store provider for the short-term keys.
 * <p/>
 * After post next available key, this key moved to another key store.
 */
public class ShortTermKeyProvider extends AbstractKeyProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShortTermKeyProvider.class);

    /**
     * Keystore configuration.
     */
    private final KeyStoreConfiguration keyStoreConfiguration;

    /**
     * Keystore for used keys configuration.
     */
    private final KeyStoreConfiguration usedKeyStoreConfiguration;

    /**
     * Storage for keys.
     */
    private KeyStore keyStore;

    /**
     * Storage for used keys.
     */
    private KeyStore usedKeyStore;

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public ShortTermKeyProvider(KeyStoreConfiguration usedKeyStoreConfiguration, KeyStoreConfiguration keyStoreConfiguration,
                                String secureRandomSeed) {
        super(secureRandomSeed);
        this.keyStoreConfiguration = keyStoreConfiguration;
        this.usedKeyStoreConfiguration = usedKeyStoreConfiguration;
    }

    public KeyStoreConfiguration getUsedKeyStoreConfiguration() {
        return usedKeyStoreConfiguration;
    }

    public KeyStore getUsedKeyStore() {
        return usedKeyStore;
    }

    public KeyStoreConfiguration getKeyStoreConfiguration() {
        return keyStoreConfiguration;
    }

    public KeyStore getKeyStore() {
        return keyStore;
    }

    @Override
    public void init() {
        writeLock(() -> {
            this.keyStore = getStoreHelper().init(getKeyStoreConfiguration());
            this.usedKeyStore = getStoreHelper().init(getUsedKeyStoreConfiguration());
            return null;
        });
    }

    @Override
    public boolean valid(String publicKey) {
        return readLock(() -> getStoreHelper().valid(publicKey, getKeyStore()) || getStoreHelper().valid(publicKey, getUsedKeyStore()));
    }

    @Override
    public long maxId() {
        return readLock(() -> Math.max(getStoreHelper().maxId(getKeyStore()), getStoreHelper().maxId(getUsedKeyStore())));
    }

    @Override
    public void addKey(String key, KeyPair keyPair, Certificate certificate) {
        writeLock(() -> {
            this.keyStore = getStoreHelper().addKey(key, keyPair, certificate, getKeyStore(), getKeyStoreConfiguration());
            return null;
        });
    }

    @Override
    public Optional<Pair<String, String>> sign(String alias, String content) {
        return readLock(() -> {
            try {
                if (getKeyStore().getCertificate(alias) != null) {
                    return Optional.ofNullable(getStoreHelper().sign(alias, content, getKeyStore(), getKeyStoreConfiguration()));
                } else if (getUsedKeyStore().getCertificate(alias) != null) {
                    return Optional.ofNullable(getStoreHelper().sign(alias, content, getUsedKeyStore(), getUsedKeyStoreConfiguration()));
                } else {
                    return Optional.empty();
                }
            } catch (KeyStoreException e) {
                String msg = "Key stores isn't initialized.";
                LOGGER.error(msg, e);
                throw new MatrixException(MatrixException.M_INTERNAL, msg);
            }
        });
    }

    @Override
    public Optional<String> nextKey() {
        return writeLock(() -> {
            Optional<String> nextKey = getStoreHelper().nextKey(getKeyStore());
            if (!nextKey.isPresent()) {
                return Optional.empty();
            }
            String alias = nextKey.get();
            Certificate certificate;
            try {
                certificate = getKeyStore().getCertificate(alias);
            } catch (KeyStoreException e) {
                String msg = "Key store isn't initialized";
                LOGGER.error(msg, e);
                throw new MatrixException(MatrixException.M_INTERNAL, msg);
            }
            if (certificate == null) {
                return Optional.empty();
            }

            PrivateKey privateKey;
            try {
                privateKey = (PrivateKey) getKeyStore().getKey(alias, getKeyStoreConfiguration().getKeyPassword().toCharArray());
            } catch (NoSuchAlgorithmException | UnrecoverableKeyException | KeyStoreException e) {
                String msg = "Failed post the private key";
                LOGGER.error(msg, e);
                throw new MatrixException(MatrixException.M_INTERNAL, msg);
            }
            try {
                getKeyStore().deleteEntry(alias);

                getUsedKeyStore()
                    .setKeyEntry(alias, privateKey, getUsedKeyStoreConfiguration().getKeyPassword().toCharArray(),
                        new Certificate[] {certificate});
                getStoreHelper().store(getKeyStore(), getKeyStoreConfiguration());
                getStoreHelper().store(getUsedKeyStore(), getUsedKeyStoreConfiguration());
                init();
            } catch (IOException | CertificateException | NoSuchAlgorithmException | KeyStoreException e) {
                String msg = "Failed store and reinitialize key stories";
                LOGGER.error(msg, e);
                throw new MatrixException(MatrixException.M_INTERNAL, msg);
            }
            return Optional.of(alias);
        });
    }

    @Override
    public Optional<Pair<String, Certificate>> key(String key) {
        return readLock(() -> {
            Optional<Pair<String, Certificate>> pair = getStoreHelper().key(key, getKeyStore());
            return pair.isPresent() ? pair : getStoreHelper().key(key, getUsedKeyStore());
        });
    }
}
