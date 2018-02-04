package io.github.ma1uta.jeon.server.service

import io.github.ma1uta.jeon.server.exception.MatrixException
import io.github.ma1uta.matrix.client.model.auth.AuthenticationFlows

data class UserInteractiveException(val authenticationFlows: AuthenticationFlows) :
        MatrixException(authenticationFlows.errcode, authenticationFlows.error, null, 401)
