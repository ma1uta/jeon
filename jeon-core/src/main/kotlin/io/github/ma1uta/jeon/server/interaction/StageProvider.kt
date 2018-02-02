package io.github.ma1uta.jeon.server.interaction

import io.github.ma1uta.matrix.client.model.account.AuthenticationData

interface StageProvider {

    fun stage(): String

    fun params(): MutableMap<String, String>

    fun authenticate(authenticationData: AuthenticationData): Boolean
}
