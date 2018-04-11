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

import org.bouncycastle.operator.OperatorCreationException;

import java.io.IOException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

/**
 * Generator new key pairs.
 */
public interface KeyGenerator {

    /**
     * Generate new key pair with certificate to store them in the key store.
     *
     * @param keyProvider      the key store where keys are stored.
     * @param parentPrivateKey the parent or root private key to create and sign new certificate.
     * @param keyId            the key identifier. First part. Second part always is 'Ed25519'.
     * @throws NoSuchAlgorithmException  if algorithm 'Ed25519' is missing.
     * @throws IOException               if there are I/O exception with the key store.
     * @throws CertificateException      if certificate is invalid.
     * @throws OperatorCreationException if cannot create issuer object.
     */
    void generate(KeyProvider keyProvider, Key parentPrivateKey, String keyId) throws NoSuchAlgorithmException,
        OperatorCreationException, IOException, CertificateException;
}
