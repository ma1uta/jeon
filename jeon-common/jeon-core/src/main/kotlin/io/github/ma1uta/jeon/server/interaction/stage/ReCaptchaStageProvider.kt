package io.github.ma1uta.jeon.server.interaction.stage

import io.github.ma1uta.jeon.server.ServerProperties
import io.github.ma1uta.jeon.server.interaction.StageProvider
import io.github.ma1uta.matrix.client.model.account.AuthenticationData
import io.github.ma1uta.matrix.client.model.auth.AuthType
import org.springframework.web.client.RestTemplate
import java.net.URL

class ReCaptchaStageProvider(val serverProperties: ServerProperties, val restTemplate: RestTemplate) : StageProvider {
    override fun stage() = AuthType.RECAPTCHA

    override fun params() = mutableMapOf(Pair("public_key", serverProperties.reCaptchaKey))

    override fun authenticate(authenticationData: AuthenticationData): Boolean {
        if (authenticationData.type != AuthType.RECAPTCHA || authenticationData.response.isNullOrBlank()) {
            return false
        }

        val url = URL(serverProperties.reCaptchaUrl + "?secret={secret}&response={response}&remoteip={remoteip}").toURI()
        val response = restTemplate.postForEntity(url,
                mutableMapOf(Pair("secret", serverProperties.reCaptchaSecretKey), Pair("response", authenticationData.response),
                        Pair("remoteip", "")), Map::class.java)
        val success = response.body["success"]

        return success is Boolean && success
    }
}
