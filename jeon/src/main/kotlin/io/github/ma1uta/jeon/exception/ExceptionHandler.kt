package io.github.ma1uta.jeon.exception

import io.github.ma1uta.matrix.ErrorResponse
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper

open class ExceptionHandler : ExceptionMapper<Throwable> {

    private val logger = LoggerFactory.getLogger(ExceptionHandler::class.java)!!

    override fun toResponse(exception: Throwable?): Response {
        val matrixException = exception ?: MatrixException(M_INTERNAL, "Internal error.")
        val message = handle(matrixException)
        val status = Response.Status.fromStatusCode((matrixException as? MatrixException)?.status ?: 500)
        return Response.status(status).entity(message).build()
    }

    @ExceptionHandler(Throwable::class)
    @ResponseBody
    open fun handle(exception: Throwable): ErrorResponse {
        logger.error("Exception:", exception)
        return when (exception) {
            is MatrixException -> ErrorResponse(exception.errcode, exception.message, exception.retryAfterMs)
            else -> ErrorResponse(M_INTERNAL, exception.message)
        }
    }
}
