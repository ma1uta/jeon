package io.github.ma1uta.jeon.server.exception

import io.github.ma1uta.jeon.server.service.UserInteractiveException
import io.github.ma1uta.matrix.ErrorResponse
import io.github.ma1uta.matrix.client.model.ErrorMessage
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class JServerExceptionHandler: io.github.ma1uta.jeon.exception.ExceptionHandler() {

    val logger = LoggerFactory.getLogger(ExceptionHandler::class.java)!!

    @ExceptionHandler(Throwable::class)
    @ResponseBody
    override fun handle(exception: Throwable): ErrorResponse {
        logger.error("Exception:", exception)
        return when (exception) {
            is UserInteractiveException -> {
                val authenticationFlows = exception.authenticationFlows
                ErrorMessage(authenticationFlows.errcode, exception.message,
                        authenticationFlows.completed, authenticationFlows.flows,
                        authenticationFlows.params, authenticationFlows.session)
            }
            else -> super.handle(exception)
        }
    }
}
