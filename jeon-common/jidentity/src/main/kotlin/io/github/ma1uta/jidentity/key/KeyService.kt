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
        longTermKeyStore = KeyStore.getInstance(properties.longTermkeyStoreType)
        Files.newInputStream(Paths.get(properties.longTermKeyStore)).use {
            longTermKeyStore.load(it, properties.longTermKeyStorePassword.toCharArray())
        }
    }

    private fun keyStoreInit(keyStore: KeyStore, properties: IdentityProperties.KeyStore) {
        longTermKeyStore = KeyStore.getInstance(properties.longTermkeyStoreType)
        Files.newInputStream(Paths.get(properties.longTermKeyStore)).use {
            longTermKeyStore.load(it, properties.longTermKeyStorePassword.toCharArray())
        }
    }

    private fun longTermInit() {
        longTermKeyStore = KeyStore.getInstance(properties.longTermkeyStoreType)
        Files.newInputStream(Paths.get(properties.longTermKeyStore)).use {
            longTermKeyStore.load(it, properties.longTermKeyStorePassword.toCharArray())
        }
    }

    private fun shortTermInit() {
        shortTermKeyStore = KeyStore.getInstance(properties.longTermkeyStoreType)
        Files.newInputStream(Paths.get(properties.longTermKeyStore)).use {
            shortTermKeyStore.load(it, properties.longTermKeyStorePassword.toCharArray())
        }
    }

    fun revoke(key: String) {
        val (alias, _) = key(key)
        longTermKeyStore.deleteEntry(alias)
        init()
    }

    fun key(key: String): Pair<String, Certificate> {
        val matcher = keyPattern.matcher(key.trim())
        if (!matcher.matches()) {
            throw IllegalAccessException("Wrong key: $key")
        }

        val algorithm = matcher.group(1)
        val alias = matcher.group(2)
        val certificate = longTermKeyStore.getCertificate(alias) ?: throw IllegalAccessException("Wrong key $key")
        if (certificate.publicKey.algorithm != algorithm) {
            throw IllegalAccessException("Wrong key: $key")
        }
        return Pair(alias, certificate)
    }

    fun sign(key: String, content: String): String {
        val (alias, _) = key(key)
        val signature = Signature.getInstance("Ed25519")
        signature.initSign(longTermKeyStore.getKey(alias, properties.keyPassword.toCharArray()) as PrivateKey)
        signature.update(content.toByteArray())
        return signature.sign().toString(Charsets.UTF_8)
    }

    fun createNew(count: Int) {
        val startFrom = longTermKeyStore.aliases().toList().map { it.toLong() }.max() ?: -1

        var key: Key? = null
        if (properties.useServerKey) {
            val rootStore = KeyStore.getInstance(serverProperties.ssl.keyStoreType, serverProperties.ssl.keyStoreProvider)
            Files.newInputStream(Paths.get(serverProperties.ssl.keyStore)).use {
                rootStore.load(it, serverProperties.ssl.keyStorePassword.toCharArray())
            }
            key = rootStore.getKey(serverProperties.ssl.keyAlias, serverProperties.ssl.keyPassword.toCharArray())
        }
        for (i in (startFrom + 1)..(startFrom + count + 1)) {
            keyGenerator.generate(longTermKeyStore, key, i.toString(), properties.keyPassword.toCharArray())
        }

        Files.newOutputStream(Paths.get(properties.longTermKeyStore)).use {
            longTermKeyStore.store(it, properties.longTermKeyStorePassword.toCharArray())
        }
        init()
    }
}
