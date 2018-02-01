package io.github.ma1uta.jeon.server.interaction

import io.github.ma1uta.matrix.client.model.auth.AuthenticationStage

interface StageProvider {

    fun stage(): AuthenticationStage

    fun params(): MutableMap<String, String>

    fun authenticate(params: MutableMap<String, String>?): Boolean
}
