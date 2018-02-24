package io.github.ma1uta.jeon.server

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer

@SpringBootApplication
class Server : SpringBootServletInitializer() {

    override fun configure(builder: SpringApplicationBuilder?) = builder!!.sources(Server::class.java)!!
}

fun main(args: Array<String>) {
    SpringApplication.run(Server::class.java, *args)
}
