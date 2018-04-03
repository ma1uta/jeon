package io.github.ma1uta.jeon.exception

const val M_INTERNAL = "M_INTERNAL"

open class MatrixException(val errcode: String, error: String, val retryAfterMs: Long? = null, val status: Int = 500) :
        RuntimeException(error)
