package io.github.ma1uta.identity.model

import java.time.LocalDateTime

data class Session(val sid: String,
                   val token: String,
                   val clientSecret: String,
                   val email: String,
                   val sendAttempt: Long?,
                   val nextLink: String,
                   val validated: LocalDateTime?)
