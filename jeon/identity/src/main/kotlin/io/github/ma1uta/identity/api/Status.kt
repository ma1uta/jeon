package io.github.ma1uta.identity.api

import io.github.ma1uta.matrix.EmptyResponse
import io.github.ma1uta.matrix.identity.api.StatusApi
import org.springframework.stereotype.Component

@Component
class Status : StatusApi {
    override fun v1Status() = EmptyResponse()
}
