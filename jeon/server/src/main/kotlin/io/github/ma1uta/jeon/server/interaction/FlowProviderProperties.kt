package io.github.ma1uta.jeon.server.interaction

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "jeon.flow")
class FlowProviderProperties {

    val flows: List<Flow> = arrayListOf()

    class Flow(val stages: List<String>)
}
