package geek.ma1uta.jeon.server

class Query(val user: User, val device: Device, val token: Token) {

    class User(val read: String)

    class Device(val insertOrUpdate: String)

    class Token(val insertOrUpdate: String)
}
