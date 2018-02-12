package io.github.ma1uta.jidentity

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "jidentity")
class IdentityProperties {

    val longTermKeys = KeyStore()

    val shortTermKeys = KeyStore()

    val selfKeyGenerator = SelfKeyGenerator()

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

        var useServerKey = false
    }
}
