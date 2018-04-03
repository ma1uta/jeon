package io.github.ma1uta.identity.key

import io.github.ma1uta.identity.IdentityProperties
import java.security.KeyStore
import java.security.PrivateKey
import java.security.cert.Certificate
import kotlin.math.max

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

    override fun maxId(): Long {
        return max(maxId(keyStore), maxId(usedKeyStore))
    }

    override fun sign(alias: String, content: String): Pair<String, String>? {
        return when {
            keyStore.getCertificate(alias) != null -> sign(content, alias, keyStore, props)
            usedKeyStore.getCertificate(alias) != null -> sign(content, alias, usedKeyStore, usedProps)
            else -> null
        }
    }

    override fun findKey(alias: String): Certificate? = super.findKey(alias) ?: usedKeyStore.getCertificate(alias)

    override fun nextKey(): String? {
        val alias = super.nextKey() ?: return null
        val certificate = keyStore.getCertificate(alias) ?: return null
        val privateKey = keyStore.getKey(alias, props.keyPassword.toCharArray()) as PrivateKey
        keyStore.deleteEntry(alias)
        usedKeyStore.setKeyEntry(alias, privateKey, usedProps.keyPassword.toCharArray(), arrayOf(certificate))
        store(keyStore, props)
        store(usedKeyStore, usedProps)
        init()
        return alias
    }
}
