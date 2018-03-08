package io.github.ma1uta.identity.key

import io.github.ma1uta.identity.IdentityProperties
import java.security.KeyStore
import java.security.PrivateKey

class ShortTermKeyStoreProvider(private val usedProps: IdentityProperties.KeyStore, props: IdentityProperties.KeyStore) :
        KeyStoreProvider(props) {

    lateinit var usedKeyStore: KeyStore

    override fun init() {
        super.init()
        usedKeyStore = initKeyStore(usedProps)
    }

    override fun valid(publicKey: String): Boolean {
        return super.valid(publicKey) || valid(publicKey, usedKeyStore)
    }

    override fun revoke(key: String) {
        val (alias, certificate) = key(key) ?: return
        val privateKey = keyStore.getKey(alias, props.keyPassword.toCharArray()) as PrivateKey
        keyStore.deleteEntry(alias)
        usedKeyStore.setKeyEntry(key, privateKey, usedProps.keyPassword.toCharArray(), arrayOf(certificate))
        store(keyStore, props)
        store(usedKeyStore, usedProps)
        init()
    }

    override fun sign(key: String, content: String): Pair<String, String>? {
        val keySignaturePair = super.sign(key, content) ?: return null
        revoke(keySignaturePair.first)
        return keySignaturePair
    }
}
