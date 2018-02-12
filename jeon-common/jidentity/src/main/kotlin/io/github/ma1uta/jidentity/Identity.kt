package io.github.ma1uta.jidentity

import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.web.support.SpringBootServletInitializer

@SpringBootApplication
class Server : SpringBootServletInitializer(), ApplicationRunner {
    override fun run(args: ApplicationArguments?) {
    }

    override fun configure(builder: SpringApplicationBuilder?) = builder!!.sources(Server::class.java)!!
}

fun main(args: Array<String>) {
    SpringApplication.run(Server::class.java, *args)
}
