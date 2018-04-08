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

import org.apache.commons.lang3.tuple.Pair;

import java.security.KeyPair;
import java.security.cert.Certificate;
import java.util.Optional;

public interface KeyProvider {
    /**
     * Initialize key store provider.
     */
    void init();

    /**
     * Retrieve next the unused key.
     *
     * @return key or empty.
     */
    Optional<String> nextKey();

    /**
     * Retrieve a pair of the alias and the certificate by the key id.
     *
     * @param key the key id.
     * @return the alias and certificate.
     */
    Optional<Pair<String, Certificate>> key(String key);

    /**
     * Verify the public key.
     *
     * @param publicKey the public key.
     * @return {@code true} is valid else {@code false}.
     */
    boolean valid(String publicKey);

    /**
     * Sign the content.
     *
     * @param alias   the key alias.
     * @param content the content to sign.
     * @return the pair of the key alias and the signature.
     */
    Optional<Pair<String, String>> sign(String alias, String content);

    /**
     * Find max alias.
     * <p/>
     * Used to create new keys.
     *
     * @return max alias.
     */
    long maxId();

    /**
     * Add a new key to the key store.
     *
     * @param key         alias of the a new key.
     * @param keyPair     the pair of the new key (public and private).
     * @param certificate a certificate of the new key.
     */
    void addKey(String key, KeyPair keyPair, Certificate certificate);
}
