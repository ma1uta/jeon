package io.github.ma1uta.jeon.server.exception

import io.github.ma1uta.matrix.client.model.ErrorMessage
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper

@RestControllerAdvice
class ExceptionHandler : ExceptionMapper<Throwable> {

    val logger = LoggerFactory.getLogger(ExceptionHandler::class.java)!!

    override fun toResponse(exception: Throwable?): Response {
        val matrixException = exception ?: MatrixException(M_INTERNAL, "Internal error.")
        val message = handle(matrixException)
        val status = Response.Status.fromStatusCode((matrixException as? MatrixException)?.status ?: 500)
        return Response.status(status).entity(message).build()
    }

    @ExceptionHandler(Throwable::class)
    @ResponseBody
    fun handle(exception: Throwable): ErrorMessage {
        logger.error("Exception:", exception)
        return when (exception) {
            is MatrixException -> ErrorMessage(exception.errcode, exception.message, exception.retryAfterMs)
            else -> ErrorMessage(M_INTERNAL, exception.message, null)
        }
    }
}
