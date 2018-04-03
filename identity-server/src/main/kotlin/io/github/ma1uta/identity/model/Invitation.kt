package io.github.ma1uta.identity.model

data class Invitation(val medium: String,
                      val address: String,
                      val roomId: String,
                      val sender: String,
                      val token: String,
                      val publicKey: String,
                      val displayName: String)
