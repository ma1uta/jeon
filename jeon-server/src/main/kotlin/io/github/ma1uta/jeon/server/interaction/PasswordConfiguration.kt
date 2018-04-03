package io.github.ma1uta.jeon.server.interaction

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Configuration

@Configuration
@ConditionalOnProperty(name = ["jeon.auth.password.enable"], havingValue = "true")
class PasswordConfiguration
