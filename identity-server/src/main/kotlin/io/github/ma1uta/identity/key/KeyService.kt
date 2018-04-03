package io.github.ma1uta.identity.key

import io.github.ma1uta.identity.IdentityProperties
import io.github.ma1uta.jeon.exception.M_INTERNAL
import io.github.ma1uta.jeon.exception.MatrixException
import org.springframework.boot.autoconfigure.web.ServerProperties
import org.springframework.stereotype.Component
import java.nio.file.Files
import java.nio.file.Paths
import java.security.Key
import java.security.KeyStore
import java.security.cert.Certificate
import javax.annotation.PostConstruct
import kotlin.math.max

@Component
class KeyService(val properties: IdentityProperties, val keyGenerator: KeyGenerator, val serverProperties: ServerProperties) {

    lateinit var longTermKeys: KeyStoreProvider
    lateinit var shortTermKeys: KeyStoreProvider

    /**
     * Initialize long-term and short-term keystories.
     */
    @PostConstruct
    fun init() {
        longTermKeys = KeyStoreProvider(properties.longTermKeys)
        shortTermKeys = ShortTermKeyStoreProvider(properties.usedShortTermKeys, properties.shortTermKeys)
    }

    /**
     * Find certificate by key.
     *
     * @param key key id.
     * @return pair (alias, certificate).
     */
    fun key(key: String): Pair<String, Certificate>? {
        return longTermKeys.key(key) ?: shortTermKeys.key(key)
    }

    /**
     * Check if the public key is valid.
     *
     * @param publicKey public key.
     * @param longTerm true if long-term key else short-term key.
     */
    fun valid(publicKey: String, longTerm: Boolean): Boolean {
        return if (longTerm) longTermKeys.valid(publicKey) else shortTermKeys.valid(publicKey)
    }

    /**
     * Sign content.
     *
     * @param content content.
     * @param longTerm true if should use long-term keys else use short-term key.
     * @return map { "hostname" -> { "key": "signature" } }
     */
    fun sign(content: String, longTerm: Boolean = true): Map<String, Map<String, String>>? {
        val provider = if (longTerm) longTermKeys else shortTermKeys
        val pair = provider.sign(nextKey(longTerm), content) ?: return null
        return mutableMapOf(Pair(properties.hostname, mutableMapOf(Pair("ed25519:" + pair.first, pair.second))))
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
    fun nextKey(longTerm: Boolean = false): String {
        val provider = if (longTerm) longTermKeys else shortTermKeys
        var key = provider.nextKey()
        if (key == null) {
            create(properties.amountKeysToCreate, longTerm)
            key = provider.nextKey()
        }
        if (key == null) {
            throw MatrixException(M_INTERNAL, "Cannot retrieve keys.")
        }
        return key
    }

    /**
     * Create new keys.
     *
     * @param count amount of the new keys which should be create.
     */
    fun create(count: Int, longTerm: Boolean = false) {
        val startFrom = max(longTermKeys.maxId(), shortTermKeys.maxId())

        var key: Key? = null
        if (properties.useServerKey) {
            // if uses root store then load this store.
            val rootStore = KeyStore.getInstance(serverProperties.ssl.keyStoreType, serverProperties.ssl.keyStoreProvider)
            Files.newInputStream(Paths.get(serverProperties.ssl.keyStore)).use {
                rootStore.load(it, serverProperties.ssl.keyStorePassword.toCharArray())
            }
            key = rootStore.getKey(serverProperties.ssl.keyAlias, serverProperties.ssl.keyPassword.toCharArray())
        }
        val keyStore = if (longTerm) longTermKeys else shortTermKeys
        for (i in (startFrom + 1)..(startFrom + count + 1)) {
            keyGenerator.generate(keyStore, key, i.toString())
        }
    }
}
