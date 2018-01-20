package geek.ma1uta.jeon.server.auth

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Configuration

@Configuration
@ConditionalOnProperty(name = ["jeon.auth.password.enable"], havingValue = "true")
class PasswordConfiguration {


}