/*
 * Copyright sablintolya@gmail.com
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

package io.github.ma1uta.matrix.server.api;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

/**
 * Open API definition.
 */
@OpenAPIDefinition(
    info = @Info(
        title = "Server API",
        version = "r0.1.0-1",
        description = "Matrix homeservers use the Federation APIs (also known as server-server APIs) to communicate with each other."
            + " Homeservers use these APIs to push messages to each other in real-time, to retrieve historic messages from each other,"
            + " and to query profile and presence information about users on each other's servers.",
        contact = @Contact(
            name = "Anatoly Sablin",
            email = "sablintolya@gmail.com"
        ),
        license = @License(
            url = "https://www.apache.org/licenses/LICENSE-2.0.html",
            name = "Apache 2.0"
        )
    )
)
public interface Api {
}

