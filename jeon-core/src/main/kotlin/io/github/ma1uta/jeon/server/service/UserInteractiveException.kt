package io.github.ma1uta.jeon.server.service

import io.github.ma1uta.matrix.client.model.auth.AuthenticationFlows

data class UserInteractiveException(val authenticationFlows: AuthenticationFlows): RuntimeException(authenticationFlows.error)
