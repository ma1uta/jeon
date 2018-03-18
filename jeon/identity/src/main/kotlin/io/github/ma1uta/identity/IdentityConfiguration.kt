package io.github.ma1uta.identity

import com.fasterxml.jackson.databind.MapperFeature
import io.github.ma1uta.identity.key.KeyGenerateSelfCertificate
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.web.client.RestTemplate

@Configuration
@EnableConfigurationProperties(value = [IdentityProperties::class])
@EnableScheduling
class IdentityConfiguration {

    @Bean
    @ConditionalOnMissingBean
    fun defaultKeyGenerator(properties: IdentityProperties) = KeyGenerateSelfCertificate(properties)

    @Bean
    fun propertySourcesPlaceholderConfigurer() = PropertySourcesPlaceholderConfigurer().apply { setPlaceholderPrefix("%{") }

    @Bean
    fun mapper() = Jackson2ObjectMapperBuilderCustomizer {
        it.featuresToEnable(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY)
    }

    @Bean
    fun restTemplate() = RestTemplate()
}
