package io.github.ma1uta.identity

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "identity")
class IdentityProperties {

    val longTermKeys = KeyStore()

    val shortTermKeys = KeyStore()

    val usedShortTermKeys = KeyStore()

    val selfKeyGenerator = SelfKeyGenerator()

    var useServerKey = false

    var hostname = "localhost"

    val email = Email()

    var amountKeysToCreate = 20

    var onBindUrl = "_matrix/federation/v1/3pid/onbind"

    var onBindPort = 8448

    var onBindProtocol = "https"

    /**
     * Time to live in seconds of the association.
     * <p/>
     * Default value is 10 years (60 seconds * 60 minutes * 24 hours * 30 days * 12 month * 10 years).
     */
    var associationTTL: Long = 60 * 60 * 24 * 30 * 12 * 10

    class SelfKeyGenerator {

        var issuerName = "jidentity"

        var secureRandomSeed = "seed"

        var serialNumberLength = 20

        var certificateValidTs: Long = 60 * 60 * 24 * 30 * 3
    }

    class KeyStore {

        var keyStore = "keyStore.jks"

        var keyStorePassword = "dummy"

        var keyPassword = "dummy"

        var keyStoreType = "pkcs12"
    }

    class Email {
        var hostname = "server@identity.local"

        var subject = "Session has been created"

        var body = "Token: {0}\nClient secret: {1}\nSession id: {2}\nUrl: {3}"
    }
}
