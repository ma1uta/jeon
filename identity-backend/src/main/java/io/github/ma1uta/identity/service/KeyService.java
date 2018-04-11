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

import org.apache.commons.lang3.tuple.Pair;

import java.security.cert.Certificate;
import java.util.Map;
import java.util.Optional;

/**
 * Service to manipulate keys.
 * <p/>
 * Used to store keys, create new keys and sign some string.
 */
public interface KeyService {
    /**
     * Long and short keys provider initialization.
     */
    void init();

    /**
     * Find certificate by key.
     *
     * @param key key id.
     * @return pair (alias, certificate).
     */
    Optional<Pair<String, Certificate>> key(String key);

    /**
     * Check if the public key is valid.
     *
     * @param publicKey public key.
     * @param longTerm  true if long-term key else short-term key.
     * @return {@code true} if key valid, else {@code false}.
     */
    boolean valid(String publicKey, boolean longTerm);

    /**
     * Sign content.
     *
     * @param content  content.
     * @param longTerm true if should use long-term keys else use short-term key.
     * @return map { "hostname" -> { "key": "signature" } }
     */
    Optional<Map<String, Map<String, String>>> sign(String content, boolean longTerm);

    /**
     * Retrieve next available key.
     * <p/>
     * For long-term keys can use the same keys.
     * <p/>
     * For short-term keys must use only new keys.
     *
     * @param longTerm true if use long-term key else use short-term key.
     * @return alias of the next free key.
     */
    String nextKey(boolean longTerm);

    /**
     * Create new keys.
     *
     * @param count    amount of the new keys which should be create.
     * @param longTerm determine what kind of key should be created, long-term or short-term.
     */
    void create(int count, boolean longTerm);
}
