package io.github.ma1uta.identity.jdbc

class Query(val association: Association, val session: Session, val invitation: Invitation) {

    class Association(val findByAddressMedium: String,
                      val insertOrIgnore: String,
                      val expire: String)

    class Session(val insertOrUpdate: String,
                  val findBySecretEmail: String,
                  val findBySecretSid: String,
                  val findBySecretTokenSid: String,
                  val deleteOldest: String,
                  val validate: String)

    class Invitation(val insert: String)
}
