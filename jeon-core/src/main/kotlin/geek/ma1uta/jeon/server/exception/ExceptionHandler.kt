package geek.ma1uta.jeon.server.exception

import geek.ma1uta.matrix.client.model.ErrorMessage
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionHandler {

    @ExceptionHandler
    fun handle(exception: Exception) : ErrorMessage {
        return when(exception) {
            is MatrixException -> ErrorMessage(exception.errcode, exception.message, exception.retryAfterMs)
            else -> ErrorMessage(M_INTERNAL, exception.message, null)
        }
    }
}