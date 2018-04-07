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
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.Enumeration;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Encapsulation of the keystore actions.
 */
public class StoreHelper {

    /**
     * All keys have format: <algorithm>:<alias>.
     */
    public static final Pattern KEY_PATTERN = Pattern.compile("(\\w+):(\\w+)");

    /**
     * Used to sign content.
     */
    private final SecureRandom secureRandom;

    public StoreHelper(String secureRandomSeed) {
        Objects.requireNonNull(secureRandomSeed, "Secure random seed must be specified");
        this.secureRandom = new SecureRandom(secureRandomSeed.getBytes(StandardCharsets.UTF_8));
    }

    public SecureRandom getSecureRandom() {
        return secureRandom;
    }

    /**
     * Initialize key store provider.
     *
     * @param configuration key storage configuration.
     * @throws KeyStoreException        if the security provider is missed.
     * @throws CertificateException     if any certificate isn't loaded.
     * @throws NoSuchAlgorithmException if the algorithm is missed.
     * @throws IOException              if cannot read key store.
     */
    public KeyStore init(KeyStoreConfiguration configuration) throws KeyStoreException, IOException, CertificateException,
        NoSuchAlgorithmException {
        KeyStore keyStore = KeyStore.getInstance(configuration.getKeyStoreType());
        try (InputStream inputStream = Files.newInputStream(Paths.get(configuration.getKeyStore()))) {
            keyStore.load(inputStream, configuration.getKeyStorePassword().toCharArray());
        }
        return keyStore;
    }

    /**
     * Retrieve next the unused key.
     *
     * @param keyStore key storage.
     * @return key or empty.
     * @throws KeyStoreException when the key store isn't loaded.
     */
    public Optional<String> nextKey(KeyStore keyStore) throws KeyStoreException {
        Enumeration<String> aliases = keyStore.aliases();
        return aliases.hasMoreElements() ? Optional.of(aliases.nextElement()) : Optional.empty();
    }

    /**
     * Retrieve a pair of the alias and the certificate by the key id.
     *
     * @param key      the key id.
     * @param keyStore key storage.
     * @return the alias and certificate.
     * @throws KeyStoreException when the key store isn't loaded.
     */
    public Optional<Pair<String, Certificate>> key(String key, KeyStore keyStore) throws KeyStoreException {
        Matcher matcher = KEY_PATTERN.matcher(key.trim());
        if (!matcher.matches()) {
            throw new IllegalArgumentException(String.format("Wrong key: %s", key));
        }

        String algorithm = matcher.group(1);
        String alias = matcher.group(2);
        Certificate certificate = keyStore.getCertificate(alias);
        if (certificate == null) {
            return Optional.empty();
        }
        if (!certificate.getPublicKey().getAlgorithm().equals(algorithm)) {
            throw new IllegalArgumentException(String.format("Wrong key: %s", key));
        }

        return Optional.of(new ImmutablePair<>(alias, certificate));
    }

    /**
     * Verify the public key.
     *
     * @param publicKey the public key.
     * @param keyStore  key storage.
     * @return {@code true} is valid else {@code false}.
     * @throws KeyStoreException when the key store isn't loaded.
     */
    protected boolean valid(String publicKey, KeyStore keyStore) throws KeyStoreException {
        Enumeration<String> aliases = keyStore.aliases();
        while (aliases.hasMoreElements()) {
            Certificate certificate = keyStore.getCertificate(aliases.nextElement());
            if (new String(certificate.getPublicKey().getEncoded(), StandardCharsets.UTF_8).equals(publicKey)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Add a new key to the key store.
     *
     * @param key           alias of the a new key.
     * @param keyPair       the pair of the new key (public and private).
     * @param certificate   a certificate of the new key.
     * @param keyStore      key storage.
     * @param configuration key storage configuration.
     * @return key storage with added key.
     * @throws KeyStoreException        when key store isn't loaded.
     * @throws CertificateException     when the certificate is invalid.
     * @throws NoSuchAlgorithmException when missing algorithm.
     * @throws IOException              when cannot write to the key store.
     */
    public KeyStore addKey(String key, KeyPair keyPair, Certificate certificate, KeyStore keyStore,
                           KeyStoreConfiguration configuration) throws KeyStoreException, CertificateException, NoSuchAlgorithmException,
        IOException {
        keyStore.setKeyEntry(key, keyPair.getPrivate(), configuration.getKeyPassword().toCharArray(), new Certificate[] {certificate});
        store(keyStore, configuration);
        return init(configuration);
    }

    /**
     * Store key storage into the file.
     *
     * @param keyStore      key storage.
     * @param configuration key storage configuration.
     * @throws KeyStoreException        if the keystore has not been initialized (loaded).
     * @throws IOException              if there was an I/O problem with data
     * @throws NoSuchAlgorithmException if the appropriate data integrity algorithm could not be found
     * @throws CertificateException     if any of the certificates included in the keystore data could not be stored
     */
    public void store(KeyStore keyStore, KeyStoreConfiguration configuration) throws IOException, CertificateException,
        NoSuchAlgorithmException, KeyStoreException {
        try (OutputStream outputStream = Files.newOutputStream(Paths.get(configuration.getKeyStore()))) {
            keyStore.store(outputStream, configuration.getKeyStorePassword().toCharArray());
        }
    }

    /**
     * Sign the content.
     *
     * @param alias         the key alias.
     * @param content       the content to sign.
     * @param keyStore      key storage.
     * @param configuration key storage configuration.
     * @return the pair of the key alias and the signature.
     * @throws UnrecoverableKeyException if the key cannot be recovered.
     * @throws NoSuchAlgorithmException  if th algorithm is missing.
     * @throws KeyStoreException         if key store isn't loaded.
     * @throws InvalidKeyException       if the key is invalid.
     * @throws SignatureException        if the signature object isn't initialized.
     */
    public Pair<String, String> sign(String alias, String content, KeyStore keyStore, KeyStoreConfiguration configuration) throws
        NoSuchAlgorithmException, UnrecoverableKeyException, KeyStoreException, InvalidKeyException, SignatureException {
        Signature signature = Signature.getInstance("Ed25519");
        signature.initSign((PrivateKey) keyStore.getKey(alias, configuration.getKeyPassword().toCharArray()), getSecureRandom());
        signature.update(content.getBytes(StandardCharsets.UTF_8));
        return new ImmutablePair<>(alias, new String(signature.sign(), StandardCharsets.UTF_8));
    }

    /**
     * Find max alias.
     * <p/>
     * Used to create new keys.
     *
     * @param keyStore key storage.
     * @return max alias.
     * @throws KeyStoreException when key store isn't loaded.
     */
    public long maxId(KeyStore keyStore) throws KeyStoreException {
        long max = 0L;
        Enumeration<String> aliases = keyStore.aliases();
        while (aliases.hasMoreElements()) {
            max = Math.max(max, Long.parseLong(aliases.nextElement()));
        }
        return max;
    }
}
