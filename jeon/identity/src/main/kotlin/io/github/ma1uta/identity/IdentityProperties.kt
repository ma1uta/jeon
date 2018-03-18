package io.github.ma1uta.identity

import org.springframework.boot.context.properties.ConfigurationProperties
import java.time.Period

@ConfigurationProperties(prefix = "jidentity")
class IdentityProperties {

    val longTermKeys = KeyStore()

    val shortTermKeys = KeyStore()

    val usedShortTermKeys = KeyStore()

    var initialShortKeyPool = 20

    val selfKeyGenerator = SelfKeyGenerator()

    var useServerKey = false

    var hostname = "localhost"

    val email = Email()

    var associationTTL = Period.ofYears(40)

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
        var hostname = "identity@localhost.lan"

        var subject = "Create a session"

        var body = "Token: %s\nClient secret: %s\nSession id: %s\nUrl: %s"
    }
}
