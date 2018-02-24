package io.github.ma1uta.jeon.server.interaction.stage

import io.github.ma1uta.jeon.server.auth.UsernamePasswordLoginProvider
import io.github.ma1uta.jeon.exception.MatrixException
import io.github.ma1uta.jeon.server.interaction.StageProvider
import io.github.ma1uta.matrix.client.model.account.AuthenticationData
import io.github.ma1uta.matrix.client.model.auth.AuthType

class PasswordStageProvider(private val usernamePasswordLoginProvider: UsernamePasswordLoginProvider) : StageProvider {

    override fun stage() = AuthType.PASSWORD

    override fun params() = mutableMapOf<String, String>()

    override fun authenticate(authenticationData: AuthenticationData): Boolean {

        if (authenticationData.type != AuthType.PASSWORD || authenticationData.user.isNullOrBlank() || authenticationData.password.isNullOrBlank()) {
            return false
        }

        return try {
            usernamePasswordLoginProvider.validateUsernameAndPassword(authenticationData.user.trim(), authenticationData.password.trim())
            true
        } catch (e: MatrixException) {
            false
        }
    }
}
