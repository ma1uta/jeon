package geek.ma1uta.jeon.server.rest.client

import geek.ma1uta.jeon.server.ServerProperties
import geek.ma1uta.matrix.client.api.VersionApi
import geek.ma1uta.matrix.client.model.version.VersionsResponse
import org.springframework.stereotype.Component

@Component
class Version(val serverProperties: ServerProperties) : VersionApi {

    override fun versions() = VersionsResponse(serverProperties.versions.toTypedArray())
}