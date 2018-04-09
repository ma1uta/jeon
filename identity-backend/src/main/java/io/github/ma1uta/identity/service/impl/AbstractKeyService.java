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

package io.github.ma1uta.identity.service.impl;

import io.github.ma1uta.identity.configuration.KeyServiceConfiguration;
import io.github.ma1uta.identity.configuration.ServerKeyConfiguration;
import io.github.ma1uta.identity.key.KeyGenerator;
import io.github.ma1uta.identity.key.KeyProvider;
import io.github.ma1uta.identity.key.LongTermKeyProvider;
import io.github.ma1uta.identity.key.ShortTermKeyProvider;
import io.github.ma1uta.identity.service.KeyService;
import io.github.ma1uta.jeon.exception.MatrixException;
import org.apache.commons.lang3.tuple.Pair;
import org.bouncycastle.operator.OperatorCreationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Default implementation.
 * <p/>
 * There are default implementation for all methods of the {@link KeyService}.
 * <p/>
 * You can create you own class for example with annotation @Transactional (jpa) or another and invoke the same method
 * with suffix "Internal".
 * <pre>
 * {@code
 *     @literal @Service
 *     public class MyKeyService extends AbstractKeyService {
 *         ...
 *         @literal @Override
 *         @literal @Transactional
 *         @literal @MyFavouriteAnnotation
 *         public String init() {
 *             // wrap next link to transaction via annotation or code.
 *             return super.initInternal();
 *         }
 *         ...
 *     }
 * }
 * </pre>
 */
public abstract class AbstractKeyService implements KeyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractKeyService.class);

    /**
     * Error message when cannot find the key.
     */
    public static final String M_MISSING_KEY = "M_MISSING_KEY";

    /**
     * Long-term keys.
     */
    private KeyProvider longTermProvider;

    /**
     * Short-term keys.
     */
    private KeyProvider shortTermProvider;

    /**
     * New keys generator.
     */
    private final KeyGenerator keyGenerator;

    /**
     * Service configuration.
     */
    private final KeyServiceConfiguration configuration;

    public AbstractKeyService(KeyGenerator keyGenerator, KeyServiceConfiguration configuration) {
        this.keyGenerator = keyGenerator;
        this.configuration = configuration;
    }

    /**
     * Default implementation.
     * <p/>
     * {@link KeyService#init()}
     */
    protected void initInternal() {
        this.longTermProvider = new LongTermKeyProvider(getConfiguration().getLongTermConfiguration(),
            getConfiguration().getSecureRandomSeed());
        this.longTermProvider.init();
        this.shortTermProvider = new ShortTermKeyProvider(getConfiguration().getUsedShortTermConfiguration(),
            getConfiguration().getShortTermConfiguration(), getConfiguration().getSecureRandomSeed());
        this.shortTermProvider.init();
    }

    /**
     * Default implementation.
     * <p/>
     * {@link KeyService#key(String)}
     */
    protected Optional<Pair<String, Certificate>> keyInternal(String key) {
        Optional<Pair<String, Certificate>> pair = getLongTermProvider().key(key);
        if (pair.isPresent()) {
            return pair;
        }

        return getShortTermProvider().key(key);
    }

    /**
     * Default implementation.
     * <p/>
     * {@link KeyService#valid(String, boolean)}
     */
    protected boolean validInternal(String publicKey, boolean longTerm) {
        return longTerm ? getLongTermProvider().valid(publicKey) : getShortTermProvider().valid(publicKey);
    }

    /**
     * Default implementation.
     * <p/>
     * {@link KeyService#sign(String, boolean)}
     */
    protected Optional<Map<String, Map<String, String>>> signInternal(String content, boolean longTerm) {
        KeyProvider provider = longTerm ? getLongTermProvider() : getShortTermProvider();
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
     * Default implementation.
     * <p/>
     * {@link KeyService#nextKey(boolean)}
     */
    protected String nextKeyInternal(boolean longTerm) {
        KeyProvider provider = longTerm ? getLongTermProvider() : getShortTermProvider();
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
     * Default implemetation.
     * <p/>
     * {@link KeyService#create(int, boolean)}
     */
    protected void createInternal(int count, boolean longTerm) {
        long startFrom = Math.max(getLongTermProvider().maxId(), getShortTermProvider().maxId());
        try {
            Key key = null;
            if (getConfiguration().isUseServerKey()) {
                ServerKeyConfiguration serverKeyConfiguration = getConfiguration().getServerKeyConfiguration();
                KeyStore rootStore = KeyStore.getInstance(serverKeyConfiguration.getKeyStoreType(), serverKeyConfiguration.getProvider());
                try (InputStream inputStream = Files.newInputStream(Paths.get(serverKeyConfiguration.getKeyStore()))) {
                    rootStore.load(inputStream, serverKeyConfiguration.getKeyStorePassword().toCharArray());
                }
                key = rootStore.getKey(serverKeyConfiguration.getKeyAlias(), serverKeyConfiguration.getKeyPassword().toCharArray());
            }
            KeyProvider provider = longTerm ? getLongTermProvider() : getShortTermProvider();
            for (long i = (startFrom + 1); i < (startFrom + count + 1); i++) {
                getKeyGenerator().generate(provider, key, Long.toString(i));
            }
        } catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | UnrecoverableKeyException
            | IOException | OperatorCreationException e) {
            String msg = "Cannot create new keys.";
            LOGGER.error(msg, e);
            throw new MatrixException(MatrixException.M_INTERNAL, msg);
        }
    }

    protected KeyServiceConfiguration getConfiguration() {
        return configuration;
    }

    protected KeyProvider getLongTermProvider() {
        return longTermProvider;
    }

    protected KeyProvider getShortTermProvider() {
        return shortTermProvider;
    }

    protected KeyGenerator getKeyGenerator() {
        return keyGenerator;
    }
}
