package io.github.ma1uta.identity

import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
open class Identity : SpringBootServletInitializer(), ApplicationRunner {

    override fun run(args: ApplicationArguments?) {
    }

    override fun configure(builder: SpringApplicationBuilder?) = builder!!.sources(Identity::class.java)!!
}

fun main(args: Array<String>) {
    SpringApplication.run(Identity::class.java, *args)
}
