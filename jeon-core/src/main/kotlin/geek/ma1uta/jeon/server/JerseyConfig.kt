package geek.ma1uta.jeon.server

import geek.ma1uta.jeon.server.exception.ExceptionHandler
import geek.ma1uta.matrix.client.api.ClientApi
import org.glassfish.jersey.server.ResourceConfig
import org.springframework.boot.autoconfigure.jersey.ResourceConfigCustomizer
import org.springframework.stereotype.Component

@Component
class JerseyConfig(val clientApis: List<ClientApi>, val exceptionHandler: ExceptionHandler) : ResourceConfigCustomizer {
    override fun customize(config: ResourceConfig?) {
        clientApis.forEach { config!!.register(it) }
        config!!.register(exceptionHandler)
    }
}
