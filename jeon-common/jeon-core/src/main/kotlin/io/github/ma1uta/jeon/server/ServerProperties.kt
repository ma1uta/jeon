package io.github.ma1uta.jeon.server

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("jeon")
class ServerProperties {
    val versions = ArrayList<String>()

    var name: String = "localhost"

    var macaroon: String = "macaroon_seed"

    var updateLastSeen: Boolean = true

    var reCaptchaKey = ""

    var reCaptchaSecretKey = ""

    var reCaptchaUrl = ""
}
