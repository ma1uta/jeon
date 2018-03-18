package io.github.ma1uta.identity.jdbc

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.core.env.Environment
import org.springframework.core.env.get

@PropertySource("classpath:/query.properties")
@Configuration
class QueryConfiguration {

    @Autowired
    lateinit var env: Environment

    @Bean
    fun query(): Query {
        val association = Query.Association(env["association.findByAddressMedium"],
                env["association.insertOrIgnore"],
                env["association.expire"])

        val session = Query.Session(env["session.insertOrUpdate"],
                env["session.findBySecretEmail"],
                env["session.findBySecretSid"],
                env["session.findBySecretTokenSid"],
                env["session.deleteOldest"],
                env["session.validate"])

        val invitation = Query.Invitation(env["invitation.insert"])

        return Query(association, session, invitation)
    }
}
