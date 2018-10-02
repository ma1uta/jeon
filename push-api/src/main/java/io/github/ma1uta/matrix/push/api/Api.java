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

package io.github.ma1uta.matrix.push.api;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

/**
 * Open API definition.
 */
@OpenAPIDefinition(
    info = @Info(
        title = "Push API",
        version = "r0.1.0-2-SNAPSHOT",
        description = "Clients may want to receive push notifications when events are received at the homeserver."
            + " This is managed by a distinct entity called the Push Gateway.",
        contact = @Contact(
            name = "Anatoly Sablin",
            email = "sablintolya@gmail.com"
        ),
        license = @License(
            url = "http://www.apache.org/licenses/LICENSE-2.0.html",
            name = "Apache 2.0"
        )
    )
)
public interface Api {
}

