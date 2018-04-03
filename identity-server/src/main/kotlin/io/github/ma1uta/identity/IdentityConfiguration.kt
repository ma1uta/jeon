package io.github.ma1uta.identity

import com.fasterxml.jackson.databind.MapperFeature
import io.github.ma1uta.identity.key.KeyGenerateSelfCertificate
import io.github.ma1uta.jeon.exception.ExceptionHandler
import io.github.ma1uta.matrix.identity.api.IdentityApi
import org.glassfish.jersey.server.ResourceConfig
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.web.client.RestTemplate
import java.net.Socket
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.annotation.PostConstruct
import javax.net.ssl.HttpsURLConnection
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLEngine
import javax.net.ssl.TrustManager
import javax.net.ssl.X509ExtendedTrustManager

@Configuration
@EnableConfigurationProperties(value = [IdentityProperties::class])
@EnableScheduling
open class IdentityConfiguration {

    companion object {
        @Bean
        fun propertySourcesPlaceholderConfigurer() = PropertySourcesPlaceholderConfigurer().apply { setPlaceholderPrefix("%{") }
    }

    @Bean
    open fun jerseyContext() = ResourceConfig()

    @Bean
    open fun jerseyConfig(clientApis: List<IdentityApi>, exceptionHandler: ExceptionHandler) = JerseyConfig(clientApis, exceptionHandler)

    @Bean
    @ConditionalOnMissingBean
    open fun defaultKeyGenerator(properties: IdentityProperties) = KeyGenerateSelfCertificate(properties)


    @Bean
    open fun mapper() = Jackson2ObjectMapperBuilderCustomizer {
        it.featuresToEnable(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY)
    }

    @Bean
    open fun restTemplate() = RestTemplate()

    @Bean
    @ConditionalOnProperty(prefix = "identity", name = ["trustall"], havingValue = "true")
    open fun trustAllCertificates() = object : Any() {
        @PostConstruct
        fun init() {
            val trustAllCerts = arrayOf<TrustManager>(object : X509ExtendedTrustManager() {
                override fun getAcceptedIssuers(): Array<X509Certificate>? = null

                @Throws(CertificateException::class)
                override fun checkClientTrusted(x509Certificates: Array<X509Certificate>, s: String) {
                }

                @Throws(CertificateException::class)
                override fun checkServerTrusted(x509Certificates: Array<X509Certificate>, s: String) {
                }

                @Throws(CertificateException::class)
                override fun checkClientTrusted(x509Certificates: Array<X509Certificate>, s: String, socket: Socket) {
                }

                @Throws(CertificateException::class)
                override fun checkServerTrusted(x509Certificates: Array<X509Certificate>, s: String, socket: Socket) {
                }

                @Throws(CertificateException::class)
                override fun checkClientTrusted(x509Certificates: Array<X509Certificate>, s: String, sslEngine: SSLEngine) {
                }

                @Throws(CertificateException::class)
                override fun checkServerTrusted(x509Certificates: Array<X509Certificate>, s: String, sslEngine: SSLEngine) {
                }
            })

            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, java.security.SecureRandom())
            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.socketFactory)
        }
    }
}
