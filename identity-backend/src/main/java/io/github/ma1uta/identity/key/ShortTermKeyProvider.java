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
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.SignatureException;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Key store provider for the short-term keys.
 * <p/>
 * After get next available key, this key moved to another key store.
 */
public class ShortTermKeyProvider implements KeyProvider {

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

    private final StoreHelper storeHelper;

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public ShortTermKeyProvider(KeyStoreConfiguration usedKeyStoreConfiguration, KeyStoreConfiguration keyStoreConfiguration,
                                String secureRandomSeed) {
        this.keyStoreConfiguration = keyStoreConfiguration;
        this.usedKeyStoreConfiguration = usedKeyStoreConfiguration;
        this.storeHelper = new StoreHelper(secureRandomSeed);
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

    public StoreHelper getStoreHelper() {
        return storeHelper;
    }

    @Override
    public void init() throws KeyStoreException, CertificateException, NoSuchAlgorithmException, IOException {
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
        try {
            writeLock.lock();
            this.keyStore = getStoreHelper().init(getKeyStoreConfiguration());
            this.usedKeyStore = getStoreHelper().init(getUsedKeyStoreConfiguration());
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public boolean valid(String publicKey) throws KeyStoreException {
        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        try {
            readLock.lock();
            return getStoreHelper().valid(publicKey, getKeyStore()) || getStoreHelper().valid(publicKey, getUsedKeyStore());
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public long maxId() throws KeyStoreException {
        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        try {
            readLock.lock();
            return Math.max(getStoreHelper().maxId(getKeyStore()), getStoreHelper().maxId(getUsedKeyStore()));
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public void addKey(String key, KeyPair keyPair, Certificate certificate) throws KeyStoreException, CertificateException,
        NoSuchAlgorithmException, IOException {
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
        try {
            writeLock.lock();
            this.keyStore = getStoreHelper().addKey(key, keyPair, certificate, getKeyStore(), getKeyStoreConfiguration());
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public Optional<Pair<String, String>> sign(String alias, String content) throws UnrecoverableKeyException, NoSuchAlgorithmException,
        KeyStoreException, InvalidKeyException, SignatureException {
        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        try {
            readLock.lock();
            if (getKeyStore().getCertificate(alias) != null) {
                return Optional.ofNullable(getStoreHelper().sign(alias, content, getKeyStore(), getKeyStoreConfiguration()));
            } else if (getUsedKeyStore().getCertificate(alias) != null) {
                return Optional.ofNullable(getStoreHelper().sign(alias, content, getUsedKeyStore(), getUsedKeyStoreConfiguration()));
            } else {
                return Optional.empty();
            }
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public Optional<String> nextKey() throws KeyStoreException {
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
        try {
            writeLock.lock();
            Optional<String> nextKey = getStoreHelper().nextKey(getKeyStore());
            if (!nextKey.isPresent()) {
                return Optional.empty();
            }
            String alias = nextKey.get();
            Certificate certificate = getKeyStore().getCertificate(alias);
            if (certificate == null) {
                return Optional.empty();
            }

            PrivateKey privateKey;
            try {
                privateKey = (PrivateKey) getKeyStore().getKey(alias, getKeyStoreConfiguration().getKeyPassword().toCharArray());
            } catch (NoSuchAlgorithmException | UnrecoverableKeyException e) {
                String msg = "Failed get the private key";
                LOGGER.error(msg, e);
                throw new MatrixException(MatrixException.M_INTERNAL, msg);
            }
            getKeyStore().deleteEntry(alias);

            getUsedKeyStore()
                .setKeyEntry(alias, privateKey, getUsedKeyStoreConfiguration().getKeyPassword().toCharArray(),
                    new Certificate[] {certificate});
            try {
                getStoreHelper().store(getKeyStore(), getKeyStoreConfiguration());
                getStoreHelper().store(getUsedKeyStore(), getUsedKeyStoreConfiguration());
                init();
            } catch (IOException | CertificateException | NoSuchAlgorithmException e) {
                String msg = "Failed store and reinitialize key stories";
                LOGGER.error(msg, e);
                throw new MatrixException(MatrixException.M_INTERNAL, msg);
            }
            return Optional.of(alias);
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public Optional<Pair<String, Certificate>> key(String key) throws KeyStoreException {
        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        try {
            readLock.lock();
            Optional<Pair<String, Certificate>> pair = getStoreHelper().key(key, getKeyStore());
            return pair.isPresent() ? pair : getStoreHelper().key(key, getUsedKeyStore());
        } finally {
            readLock.unlock();
        }
    }
}
