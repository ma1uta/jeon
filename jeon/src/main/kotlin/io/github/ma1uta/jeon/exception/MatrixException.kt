package io.github.ma1uta.jeon.exception

const val M_INTERNAL = "M_INTERNAL"

open class MatrixException(val errcode: String, error: String, val retryAfterMs: Long?, val status: Int = 500) : RuntimeException(error) {
    constructor(errcode: String, error: String) : this(errcode, error, null)
}
