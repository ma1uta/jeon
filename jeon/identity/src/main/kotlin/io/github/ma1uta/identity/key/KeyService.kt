package io.github.ma1uta.identity.key

import io.github.ma1uta.identity.IdentityProperties
import org.springframework.boot.autoconfigure.web.ServerProperties
import org.springframework.stereotype.Component
import java.nio.file.Files
import java.nio.file.Paths
import java.security.Key
import java.security.KeyStore
import java.security.cert.Certificate
import javax.annotation.PostConstruct

@Component
class KeyService(val properties: IdentityProperties, val keyGenerator: KeyGenerator, val serverProperties: ServerProperties) {

    lateinit var longTermKeys: KeyStoreProvider
    lateinit var shortTermKeys: KeyStoreProvider

    @PostConstruct
    fun init() {
        longTermKeys = KeyStoreProvider(properties.longTermKeys)
        shortTermKeys = ShortTermKeyStoreProvider(properties.usedShortTermKeys, properties.shortTermKeys)
    }

    fun revoke(key: String, longTerm: Boolean) {
        if (longTerm) {
            longTermKeys.revoke(key)
        } else {
            shortTermKeys.revoke(key)
        }
    }

    fun key(key: String): Pair<String, Certificate>? {
        return key(key, false) ?: key(key, true)
    }

    fun key(key: String, longTerm: Boolean): Pair<String, Certificate>? {
        return if (longTerm) longTermKeys.key(key) else shortTermKeys.key(key)
    }

    fun valid(publicKey: String, longTerm: Boolean): Boolean {
        return if (longTerm) longTermKeys.valid(publicKey) else shortTermKeys.valid(publicKey)
    }

    fun sign(content: String, longTerm: Boolean): Pair<String, String>? {
        val keyStore = if (longTerm) longTermKeys else shortTermKeys
        val key = keyStore.nextKey() ?: return null
        return keyStore.sign(key, content)
    }

    fun sign(key: String, content: String, longTerm: Boolean): String? {
        return if (longTerm) longTermKeys.sign(key, content)?.second else shortTermKeys.sign(key, content)?.second
    }

    fun createNew(count: Int, longTerm: Boolean = false) {
        val keyStore = if (longTerm) longTermKeys else shortTermKeys

        val startFrom = keyStore.maxId()

        var key: Key? = null
        if (properties.useServerKey) {
            val rootStore = KeyStore.getInstance(serverProperties.ssl.keyStoreType, serverProperties.ssl.keyStoreProvider)
            Files.newInputStream(Paths.get(serverProperties.ssl.keyStore)).use {
                rootStore.load(it, serverProperties.ssl.keyStorePassword.toCharArray())
            }
            key = rootStore.getKey(serverProperties.ssl.keyAlias, serverProperties.ssl.keyPassword.toCharArray())
        }
        for (i in (startFrom + 1)..(startFrom + count + 1)) {
            keyGenerator.generate(keyStore, key, i.toString())
        }
    }
}
