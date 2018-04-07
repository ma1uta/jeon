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

package io.github.ma1uta.identity.service;

import io.github.ma1uta.identity.key.KeyGenerator;
import io.github.ma1uta.identity.configuration.KeyServiceConfiguration;
import io.github.ma1uta.identity.key.KeyStoreProvider;
import io.github.ma1uta.identity.configuration.ServerKeyConfiguration;
import io.github.ma1uta.identity.key.ShortTermKeyStoreProvider;
import io.github.ma1uta.jeon.exception.MatrixException;
import org.apache.commons.lang3.tuple.Pair;
import org.bouncycastle.operator.OperatorCreationException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Service to manipulate keys.
 * <p/>
 * Used to store keys, create new keys and sign some string.
 */
public class KeyService {

    /**
     * Long-term keys.
     */
    private KeyStoreProvider longTermProvider;

    /**
     * Short-term keys.
     */
    private KeyStoreProvider shortTermProvider;

    /**
     * New keys generator.
     */
    private final KeyGenerator keyGenerator;

    /**
     * Service configuration.
     */
    private final KeyServiceConfiguration configuration;

    public KeyService(KeyGenerator keyGenerator, KeyServiceConfiguration configuration) {
        this.keyGenerator = keyGenerator;
        this.configuration = configuration;
    }

    /**
     * Long and short keys provider initialization.
     */
    public void init() {
        this.longTermProvider = new KeyStoreProvider(getConfiguration().getLongTermConfiguration(),
            getConfiguration().getSecureRandomSeed());
        this.shortTermProvider = new ShortTermKeyStoreProvider(getConfiguration().getUsedShortTermConfiguration(),
            getConfiguration().getShortTermConfiguration(), getConfiguration().getSecureRandomSeed());
    }

    /**
     * Find certificate by key.
     *
     * @param key key id.
     * @return pair (alias, certificate).
     */
    public Optional<Pair<String, Certificate>> key(String key) throws KeyStoreException {
        Optional<Pair<String, Certificate>> pair = getLongTermProvider().key(key);
        if (pair.isPresent()) {
            return pair;
        }

        return getShortTermProvider().key(key);
    }

    /**
     * Check if the public key is valid.
     *
     * @param publicKey public key.
     * @param longTerm  true if long-term key else short-term key.
     */
    public boolean valid(String publicKey, boolean longTerm) throws KeyStoreException {
        return longTerm ? getLongTermProvider().valid(publicKey) : getShortTermProvider().valid(publicKey);
    }

    /**
     * Sign content.
     *
     * @param content  content.
     * @param longTerm true if should use long-term keys else use short-term key.
     * @return map { "hostname" -> { "key": "signature" } }
     */
    public Optional<Map<String, Map<String, String>>> sign(String content, boolean longTerm) throws CertificateException,
        UnrecoverableKeyException,
        NoSuchAlgorithmException, KeyStoreException, OperatorCreationException, IOException, SignatureException, InvalidKeyException {
        KeyStoreProvider provider = longTerm ? getLongTermProvider() : getShortTermProvider();
        Optional<Pair<String, String>> pair = provider.sign(nextKey(longTerm), content);
        if (!pair.isPresent()) {
            return Optional.empty();
        }
        Map<String, Map<String, String>> result = new HashMap<>();
        Map<String, String> pairMap = new HashMap<>();
        pairMap.put("ed25519" + pair.get().getKey(), pair.get().getValue());
        result.put(getConfiguration().getHostname(), pairMap);
        return Optional.of(result);
    }

    /**
     * Retrieve next available key.
     * <p/>
     * For long-term keys can use the same keys.
     * <p/>
     * For short-term keys must use only new keys.
     *
     * @param longTerm true if use long-term key else use short-term key.
     */
    public String nextKey(boolean longTerm) throws KeyStoreException, UnrecoverableKeyException, CertificateException,
        OperatorCreationException, NoSuchAlgorithmException, IOException {
        KeyStoreProvider provider = longTerm ? getLongTermProvider() : getShortTermProvider();
        Optional<String> key = provider.nextKey();
        if (!key.isPresent()) {
            create(getConfiguration().getAmountKeysToCreate(), longTerm);
            key = provider.nextKey();
        }
        if (!key.isPresent()) {
            throw new MatrixException(MatrixException.M_INTERNAL, "Cannot retrieve keys.");
        }
        return key.get();
    }

    /**
     * Create new keys.
     *
     * @param count amount of the new keys which should be create.
     */
    public void create(int count, boolean longTerm) throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException,
        UnrecoverableKeyException, OperatorCreationException {
        long startFrom = Math.max(getLongTermProvider().maxId(), getShortTermProvider().maxId());
        Key key = null;
        if (getConfiguration().isUseServerKey()) {
            ServerKeyConfiguration serverKeyConfiguration = getConfiguration().getServerKeyConfiguration();
            KeyStore rootStore = KeyStore.getInstance(serverKeyConfiguration.getKeyStoreType(), serverKeyConfiguration.getProvider());
            try (InputStream inputStream = Files.newInputStream(Paths.get(serverKeyConfiguration.getKeyStore()))) {
                rootStore.load(inputStream, serverKeyConfiguration.getKeyStorePassword().toCharArray());
            }
            key = rootStore.getKey(serverKeyConfiguration.getKeyAlias(), serverKeyConfiguration.getKeyPassword().toCharArray());
        }
        KeyStoreProvider provider = longTerm ? getLongTermProvider() : getShortTermProvider();
        for (long i = (startFrom + 1); i < (startFrom + count + 1); i++) {
            getKeyGenerator().generate(provider, key, Long.toString(i));
        }
    }

    public KeyServiceConfiguration getConfiguration() {
        return configuration;
    }

    public KeyStoreProvider getLongTermProvider() {
        return longTermProvider;
    }

    public KeyStoreProvider getShortTermProvider() {
        return shortTermProvider;
    }

    public KeyGenerator getKeyGenerator() {
        return keyGenerator;
    }
}
