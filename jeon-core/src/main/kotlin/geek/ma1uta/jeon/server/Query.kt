package geek.ma1uta.jeon.server

class Query(val user: User, val device: Device, val token: Token) {

    class User(val read: String, val insert: String)

    class Device(val insertOrUpdate: String, val updateLastSeen: String)

    class Token(val insertOrUpdate: String, val deleteByUserAndDevice: String, val findByToken: String, val deleteByToken: String)
}
