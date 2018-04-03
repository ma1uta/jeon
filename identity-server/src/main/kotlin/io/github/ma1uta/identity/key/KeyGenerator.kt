package io.github.ma1uta.identity.key

import java.security.Key

interface KeyGenerator {

    /**
     * Generate new long-term key pair with certificate to store them in the key store.
     *
     * @param keyStoreProvider the key store where keys are stored.
     * @param parentPrivateKey the parent or root private key to create and sign new certificate.
     * @param keyId the key identifier. First part. Second part always is 'Ed25519'.
     */
    fun generate(keyStoreProvider: KeyStoreProvider, parentPrivateKey: Key?, keyId: String)
}
