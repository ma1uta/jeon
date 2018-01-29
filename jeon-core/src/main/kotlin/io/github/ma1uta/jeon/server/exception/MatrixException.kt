package io.github.ma1uta.jeon.server.exception

val M_INTERNAL = "M_INTERNAL"

data class MatrixException(val errcode: String, private val error: String, val retryAfterMs: Long?, val status: Int = 500) : RuntimeException(error) {
    constructor(errcode: String, error: String) : this(errcode, error, null)
}
