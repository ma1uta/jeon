package io.github.ma1uta.jidentity

import io.github.ma1uta.jeon.exception.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

const val M_MISSING_KEY = "M_MISSING_KEY"

@RestControllerAdvice
class JIdentityExceptionHandler: ExceptionHandler()
