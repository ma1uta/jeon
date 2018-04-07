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

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.Optional;

public interface KeyProvider {
    /**
     * Initialize key store provider.
     *
     * @throws KeyStoreException        if the security provider is missed.
     * @throws CertificateException     if any certificate isn't loaded.
     * @throws NoSuchAlgorithmException if the algorithm is missed.
     * @throws IOException              if cannot read key store.
     */
    void init() throws KeyStoreException, CertificateException, NoSuchAlgorithmException, IOException;

    /**
     * Retrieve next the unused key.
     *
     * @return key or empty.
     * @throws KeyStoreException when the key store isn't loaded.
     */
    Optional<String> nextKey() throws KeyStoreException;

    /**
     * Retrieve a pair of the alias and the certificate by the key id.
     *
     * @param key the key id.
     * @return the alias and certificate.
     * @throws KeyStoreException when the key store isn't loaded.
     */
    Optional<Pair<String, Certificate>> key(String key) throws KeyStoreException;

    /**
     * Verify the public key.
     *
     * @param publicKey the public key.
     * @return {@code true} is valid else {@code false}.
     * @throws KeyStoreException when the key store isn't loaded.
     */
    boolean valid(String publicKey) throws KeyStoreException;

    /**
     * Sign the content.
     *
     * @param alias   the key alias.
     * @param content the content to sign.
     * @return the pair of the key alias and the signature.
     * @throws UnrecoverableKeyException if the key cannot be recovered.
     * @throws NoSuchAlgorithmException  if th algorithm is missing.
     * @throws KeyStoreException         if key store isn't loaded.
     * @throws InvalidKeyException       if the key is invalid.
     * @throws SignatureException        if the signature object isn't initialized.
     */
    Optional<Pair<String, String>> sign(String alias, String content) throws UnrecoverableKeyException, NoSuchAlgorithmException,
        KeyStoreException, InvalidKeyException, SignatureException;

    /**
     * Find max alias.
     * <p/>
     * Used to create new keys.
     *
     * @return max alias.
     * @throws KeyStoreException when key store isn't loaded.
     */
    long maxId() throws KeyStoreException;

    /**
     * Add a new key to the key store.
     *
     * @param key         alias of the a new key.
     * @param keyPair     the pair of the new key (public and private).
     * @param certificate a certificate of the new key.
     * @throws KeyStoreException        when key store isn't loaded.
     * @throws CertificateException     when the certificate is invalid.
     * @throws NoSuchAlgorithmException when missing algorithm.
     * @throws IOException              when cannot write to the key store.
     */
    void addKey(String key, KeyPair keyPair, Certificate certificate) throws KeyStoreException, CertificateException,
        NoSuchAlgorithmException, IOException;
}
