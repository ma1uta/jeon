package geek.ma1uta.jeon.server.exception

val M_INTERNAL = "M_INTERNAL"

data class MatrixException(val errcode: String, private val error: String, val retryAfterMs: Long?) : RuntimeException(error) {
    constructor(errcode: String, error: String) : this(errcode, error, null)
}
