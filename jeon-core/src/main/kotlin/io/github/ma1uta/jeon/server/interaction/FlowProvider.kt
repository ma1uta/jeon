package io.github.ma1uta.jeon.server.interaction

interface FlowProvider {

    fun stages(): Array<StageProvider>
}
