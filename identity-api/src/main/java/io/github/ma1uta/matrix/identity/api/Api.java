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

package io.github.ma1uta.matrix.identity.api;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

/**
 * Open API definition.
 */
@OpenAPIDefinition(
    info = @Info(
        title = "Identity API",
        version = "0.2.1",
        description = "The purpose of an identity server is to validate, store, and answer questions about the identities of users."
            + " In particular, it stores associations of the form \"identifier X represents the same user as identifier Y\", where"
            + " identities may exist on different systems (such as email addresses, phone numbers, Matrix user IDs, etc)."
            + " The identity server has some private-public keypairs. When asked about an association, it will sign details"
            + " of the association with its private key. Clients may validate the assertions about associations by verifying"
            + " the signature with the public key of the identity server."
            + "In general, identity servers are treated as reliable oracles. They do not necessarily provide evidence that they"
            + " have validated associations, but claim to have done so. Establishing the trustworthiness of an individual identity"
            + " server is left as an exercise for the client.\n",
        contact = @Contact(
            name = "Anatoly Sablin",
            email = "tolya@sablin.xyz"
        ),
        license = @License(
            url = "http://www.apache.org/licenses/LICENSE-2.0.html",
            name = "Apache 2.0"
        )
    )
)
public interface Api {
}

