package io.github.ma1uta.jeon.server

import io.github.ma1uta.jeon.server.exception.ExceptionHandler
import io.github.ma1uta.matrix.client.api.ClientApi
import org.glassfish.jersey.server.ResourceConfig
import org.springframework.boot.autoconfigure.jersey.ResourceConfigCustomizer
import org.springframework.stereotype.Component

@Component
class JerseyConfig(val clientApis: List<ClientApi>, val exceptionHandler: ExceptionHandler) : ResourceConfigCustomizer {
    override fun customize(config: ResourceConfig?) {
        clientApis.forEach { config!!.register(it::class.java) }
        config!!.register(exceptionHandler)
    }
}
