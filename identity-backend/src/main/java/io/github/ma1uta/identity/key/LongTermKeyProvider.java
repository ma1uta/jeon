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
import org.apache.commons.lang3.tuple.Pair;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Encapsulation of the keystore actions.
 */
public class LongTermKeyProvider implements KeyProvider {

    /**
     * Keystore configuration.
     */
    private final KeyStoreConfiguration keyStoreConfiguration;

    /**
     * Storage for keys.
     */
    private KeyStore keyStore;

    private final StoreHelper storeHelper;

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public LongTermKeyProvider(KeyStoreConfiguration keyStoreConfiguration, String secureRandomSeed) {
        this.keyStoreConfiguration = Objects.requireNonNull(keyStoreConfiguration, "Key store configuration shouldn't be empty");
        this.storeHelper = new StoreHelper(secureRandomSeed);
    }

    public KeyStoreConfiguration getKeyStoreConfiguration() {
        return keyStoreConfiguration;
    }

    public KeyStore getKeyStore() {
        return keyStore;
    }

    protected StoreHelper getStoreHelper() {
        return storeHelper;
    }

    @Override
    public void init() throws KeyStoreException, CertificateException, NoSuchAlgorithmException, IOException {
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
        try {
            writeLock.lock();
            this.keyStore = getStoreHelper().init(getKeyStoreConfiguration());
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public Optional<String> nextKey() throws KeyStoreException {
        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        try {
            readLock.lock();
            return getStoreHelper().nextKey(getKeyStore());
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public Optional<Pair<String, Certificate>> key(String key) throws KeyStoreException {
        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        try {
            readLock.lock();
            return getStoreHelper().key(key, getKeyStore());
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public boolean valid(String publicKey) throws KeyStoreException {
        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        try {
            readLock.lock();
            return getStoreHelper().valid(publicKey, getKeyStore());
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public Optional<Pair<String, String>> sign(String alias, String content) throws UnrecoverableKeyException, NoSuchAlgorithmException,
        KeyStoreException, InvalidKeyException, SignatureException {
        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        try {
            readLock.lock();
            if (getKeyStore().getCertificate(alias) == null) {
                return Optional.empty();
            }
            return Optional.ofNullable(getStoreHelper().sign(alias, content, getKeyStore(), getKeyStoreConfiguration()));
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public long maxId() throws KeyStoreException {
        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        try {
            readLock.lock();
            return getStoreHelper().maxId(getKeyStore());
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
}
