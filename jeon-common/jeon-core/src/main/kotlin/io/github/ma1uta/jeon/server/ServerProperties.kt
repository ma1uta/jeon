package io.github.ma1uta.jeon.server

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("jeon")
class ServerProperties {
    val versions = ArrayList<String>()

    var name = "localhost"

    var macaroon = "macaroon_seed"

    var updateLastSeen = true

    var reCaptchaKey = ""

    var reCaptchaSecretKey = ""

    var reCaptchaUrl = ""
}
