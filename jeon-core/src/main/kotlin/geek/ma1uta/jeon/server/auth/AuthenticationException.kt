package geek.ma1uta.jeon.server.auth

import org.springframework.security.core.AuthenticationException

class AuthenticationException(val errcode: String, error: String): AuthenticationException(error)
