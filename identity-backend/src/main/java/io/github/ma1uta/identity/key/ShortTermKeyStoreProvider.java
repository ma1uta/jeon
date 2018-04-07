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
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.SignatureException;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.Optional;

/**
 * Key store provider for the short-term keys.
 * <p/>
 * After get next available key, this key moved to another key store.
 */
public class ShortTermKeyStoreProvider extends KeyStoreProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShortTermKeyStoreProvider.class);

    private final KeyStoreConfiguration usedKeyStoreConfiguration;
    private KeyStore usedKeyStore;

    public ShortTermKeyStoreProvider(KeyStoreConfiguration usedKeyStoreConfiguration, KeyStoreConfiguration keyStoreConfiguration,
                                     String secureRandomSeed) {
        super(keyStoreConfiguration, secureRandomSeed);
        this.usedKeyStoreConfiguration = usedKeyStoreConfiguration;
    }

    public KeyStoreConfiguration getUsedKeyStoreConfiguration() {
        return usedKeyStoreConfiguration;
    }

    public KeyStore getUsedKeyStore() {
        return usedKeyStore;
    }

    @Override
    public void init() throws KeyStoreException, CertificateException, NoSuchAlgorithmException, IOException {
        super.init();
        this.usedKeyStore = init(getUsedKeyStoreConfiguration());
    }

    @Override
    public boolean valid(String publicKey) throws KeyStoreException {
        return super.valid(publicKey) || valid(publicKey, getUsedKeyStore());
    }

    @Override
    public long maxId() throws KeyStoreException {
        return Math.max(super.maxId(), maxId(getUsedKeyStore()));
    }

    @Override
    public Optional<Pair<String, String>> sign(String alias, String content) throws UnrecoverableKeyException, NoSuchAlgorithmException,
        KeyStoreException, InvalidKeyException, SignatureException {
        if (getKeyStore().getCertificate(alias) != null) {
            return Optional.ofNullable(sign(alias, content, getKeyStore(), getKeyStoreConfiguration()));
        } else if (getUsedKeyStore().getCertificate(alias) != null) {
            return Optional.ofNullable(sign(alias, content, getUsedKeyStore(), getUsedKeyStoreConfiguration()));
        } else {
            return Optional.empty();
        }
    }

    @Override
    protected Optional<Certificate> find(String alias) throws KeyStoreException {
        Optional<Certificate> certificate = super.find(alias);
        if (certificate.isPresent()) {
            return certificate;
        } else {
            return Optional.ofNullable(getUsedKeyStore().getCertificate(alias));
        }
    }

    @Override
    public Optional<String> nextKey() throws KeyStoreException {
        Optional<String> nextKey = super.nextKey();
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
            .setKeyEntry(alias, privateKey, getUsedKeyStoreConfiguration().getKeyPassword().toCharArray(), new Certificate[] {certificate});
        try {
            store(getKeyStore(), getKeyStoreConfiguration());
            store(getUsedKeyStore(), getUsedKeyStoreConfiguration());
            init();
        } catch (IOException | CertificateException | NoSuchAlgorithmException e) {
            String msg = "Failed store and reinitialize key stories";
            LOGGER.error(msg, e);
            throw new MatrixException(MatrixException.M_INTERNAL, msg);
        }
        return Optional.of(alias);
    }
}
