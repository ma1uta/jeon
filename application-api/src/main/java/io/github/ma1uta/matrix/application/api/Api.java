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

package io.github.ma1uta.matrix.application.api;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

/**
 * Open API definition.
 */
@OpenAPIDefinition(
    info = @Info(
        title = "Application API",
        version = "0.1.1",
        description = "Application services are passive and can only observe events from homeserver."
            + " They can inject events into rooms they are participating in. They cannot prevent events from being sent, nor can they"
            + " modify the content of the event being sent. In order to observe events from a homeserver, the homeserver needs to be"
            + " configured to pass certain types of traffic to the application service. This is achieved by manually configuring"
            + " the homeserver with information about the application service.",
        contact = @Contact(
            name = "Anatoly Sablin",
            email = "tolya@sablin.xyz"
        ),
        license = @License(
            url = "https://www.apache.org/licenses/LICENSE-2.0.html",
            name = "Apache 2.0"
        )
    )
)
public interface Api {
}

