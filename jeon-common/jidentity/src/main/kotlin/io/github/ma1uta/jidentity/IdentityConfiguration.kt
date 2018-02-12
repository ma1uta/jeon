package io.github.ma1uta.jidentity

import io.github.ma1uta.jidentity.key.KeyGenerateSelfCertificate
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(value = [IdentityProperties::class])
class IdentityConfiguration {

    @Bean
    @ConditionalOnMissingBean
    fun defaultKeyGenerator(properties: IdentityProperties) = KeyGenerateSelfCertificate(properties)

}
