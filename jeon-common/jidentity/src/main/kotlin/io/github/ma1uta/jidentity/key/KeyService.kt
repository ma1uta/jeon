package io.github.ma1uta.jidentity.key

import io.github.ma1uta.jidentity.IdentityProperties
import org.springframework.boot.autoconfigure.web.ServerProperties
import org.springframework.stereotype.Component
import java.nio.file.Files
import java.nio.file.Paths
import java.security.Key
import java.security.KeyStore
import java.security.PrivateKey
import java.security.Signature
import java.security.cert.Certificate
import java.util.regex.Pattern
import javax.annotation.PostConstruct

@Component
class KeyService(val properties: IdentityProperties, val keyGenerator: KeyGenerator, val serverProperties: ServerProperties) {

    val keyPattern: Pattern = Pattern.compile("(\\w+):(\\w+)")

    lateinit var longTermKeyStore: KeyStore
    lateinit var shortTermKeyStore: KeyStore

    @PostConstruct
    fun init() {
        longTermKeyStore = keyStoreInit(properties.longTermKeys)
        shortTermKeyStore = keyStoreInit(properties.shortTermKeys)
    }

    private fun keyStoreInit(properties: IdentityProperties.KeyStore): KeyStore {
        val keyStore = KeyStore.getInstance(properties.keyStoreType)
        Files.newInputStream(Paths.get(properties.keyStore)).use {
            keyStore.load(it, properties.keyStorePassword.toCharArray())
        }
        return keyStore
    }

    fun revoke(key: String, longTerm: Boolean) {
        if (longTerm) {
            val newStore = revoke(key, longTermKeyStore, properties.longTermKeys)
            if (newStore != null) {
                longTermKeyStore = newStore
            }
        } else {
            val newStore = revoke(key, shortTermKeyStore, properties.shortTermKeys)
            if (newStore != null) {
                shortTermKeyStore = newStore
            }
        }
    }

    private fun revoke(key: String, keyStore: KeyStore, properties: IdentityProperties.KeyStore): KeyStore? {
        val (alias, _) = key(key, keyStore) ?: return null
        keyStore.deleteEntry(alias)
        return keyStoreInit(properties)
    }

    fun key(key: String): Pair<String, Certificate>? {
        return key(key, false) ?: key(key, true)
    }

    fun key(key: String, longTerm: Boolean): Pair<String, Certificate>? {
        return key(key, if (longTerm) longTermKeyStore else shortTermKeyStore)
    }

    fun valid(publicKey: String, longTerm: Boolean): Boolean {
        val keyStore = if (longTerm) longTermKeyStore else shortTermKeyStore
        val aliases = keyStore.aliases()
        while (aliases.hasMoreElements()) {
            val certificate = keyStore.getCertificate(aliases.nextElement())
            if (certificate.publicKey.encoded.toString(Charsets.UTF_8) == publicKey) {
                return true
            }
        }
        return false
    }

    private fun key(key: String, keyStore: KeyStore): Pair<String, Certificate>? {
        val matcher = keyPattern.matcher(key.trim())
        if (!matcher.matches()) {
            throw IllegalAccessException("Wrong key: $key")
        }

        val algorithm = matcher.group(1)
        val alias = matcher.group(2)
        val certificate = keyStore.getCertificate(alias) ?: return null
        if (certificate.publicKey.algorithm != algorithm) {
            throw IllegalAccessException("Wrong key: $key")
        }
        return Pair(alias, certificate)
    }

    fun sign(key: String, content: String, longTerm: Boolean): String? {
        val (alias, _) = key(key, longTerm) ?: return null
        val sign = Signature.getInstance("Ed25519")
        val props = if (longTerm) properties.longTermKeys else properties.shortTermKeys
        val keyStore = if (longTerm) longTermKeyStore else shortTermKeyStore
        sign.initSign(keyStore.getKey(alias, props.keyPassword.toCharArray()) as PrivateKey)
        sign.update(content.toByteArray())
        val signature = sign.sign().toString(Charsets.UTF_8)
        if (!longTerm) {
            keyStore.deleteEntry(alias)
            shortTermKeyStore = keyStoreInit(props)
            if (keyStore.aliases().toList().isEmpty()) {
                createNew(properties.initialShortKeyPool, false)
            }
        }
        return signature
    }

    fun createNew(count: Int, longTerm: Boolean = false) {
        val keyStore = if (longTerm) longTermKeyStore else shortTermKeyStore
        val props = if (longTerm) properties.longTermKeys else properties.shortTermKeys

        val startFrom = keyStore.aliases().toList().map { it.toLong() }.max() ?: -1

        var key: Key? = null
        if (props.useServerKey) {
            val rootStore = KeyStore.getInstance(serverProperties.ssl.keyStoreType, serverProperties.ssl.keyStoreProvider)
            Files.newInputStream(Paths.get(serverProperties.ssl.keyStore)).use {
                rootStore.load(it, serverProperties.ssl.keyStorePassword.toCharArray())
            }
            key = rootStore.getKey(serverProperties.ssl.keyAlias, serverProperties.ssl.keyPassword.toCharArray())
        }
        for (i in (startFrom + 1)..(startFrom + count + 1)) {
            keyGenerator.generate(keyStore, key, i.toString(), props.keyPassword.toCharArray())
        }

        Files.newOutputStream(Paths.get(props.keyStore)).use {
            keyStore.store(it, props.keyStorePassword.toCharArray())
        }
        if (longTerm) {
            longTermKeyStore = keyStoreInit(props)
        } else {
            shortTermKeyStore = keyStoreInit(props)
        }
    }
}
