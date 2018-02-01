package io.github.ma1uta.jeon.server

class Query(val user: User,
            val device: Device) {

    class User(val read: String,
               val insert: String)

    class Device(val insertOrUpdate: String,
                 val updateLastSeen: String,
                 val findByToken: String,
                 val deleteToken: String)
}
