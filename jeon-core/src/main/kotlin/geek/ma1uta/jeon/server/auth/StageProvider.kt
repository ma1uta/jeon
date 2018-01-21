package geek.ma1uta.jeon.server.auth

import geek.ma1uta.matrix.client.model.auth.AuthenticationStage

interface StageProvider {

    fun stage(): AuthenticationStage

    fun params(): MutableMap<String, String>

    fun authenticate(params: MutableMap<String, String>?): Boolean
}
