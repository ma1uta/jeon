package io.github.ma1uta.identity.model

data class Association(val address: String,
                       val medium: String,
                       val mxid: String,
                       val created: Long,
                       val expired: Long,
                       val ts: Long)
