/*
 * Copyright Anatoliy Sablin tolya@sablin.xyz
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.ma1uta.matrix.client.api;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

/**
 * Open API definition.
 */
@OpenAPIDefinition(
    info = @Info(
        title = "Client API",
        version = "0.5.0",
        description = "The client-server API provides a simple lightweight API to let clients send messages, control rooms"
            + " and synchronise conversation history. It is designed to support both lightweight clients which store no state"
            + " and lazy-load data from the server as required - as well as heavyweight clients which maintain a full local"
            + " persistent copy of server state.",
        contact = @Contact(
            name = "Anatoly Sablin",
            email = "tolya@sablin.xyz"
        ),
        license = @License(
            url = "http://www.apache.org/licenses/LICENSE-2.0.html",
            name = "Apache 2.0"
        )
    ),
    security = {
        @SecurityRequirement(
            name = "accessToken"
        )
    }
)
public interface Api {
}

