package geek.ma1uta.jeon.server

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.PropertySource

@PropertySource("query")
@ConfigurationProperties
class Query {

    val user = User()
    val token = Token()

    class User {
        var create = ""
        var read = ""
        var update = ""
    }

    class Token {
        var validate = ""
    }
}
