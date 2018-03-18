package io.github.ma1uta.identity.jdbc

class Query(val association: Association, val session: Session) {

    class Association(val findByAddressMedium: String,
                      val insertOrUpdate: String)

    class Session(val insertOrUpdate: String,
                  val findBySecretAndEmail: String,
                  val findBySecretAndSid: String,
                  val findBySecretTokenSid: String,
                  val deleteOldest: String,
                  val validate: String)
}
