package geek.ma1uta.jeon.server

import geek.ma1uta.jeon.server.rest.client.Version
import org.glassfish.jersey.server.ResourceConfig
import org.springframework.boot.autoconfigure.jersey.ResourceConfigCustomizer
import org.springframework.stereotype.Component

@Component
class JerseyConfig(val version : Version): ResourceConfigCustomizer {
    override fun customize(config: ResourceConfig?) {
        config!!.register(version)
    }
}