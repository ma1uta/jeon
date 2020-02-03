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

package io.github.ma1uta.matrix.event.nested;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ma1uta.matrix.Signed;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;

/**
 * Third-party invites.
 */
@Schema(
    description = "Third-party invites."
)
public class Invite {

    /**
     * Required. A name which can be displayed to represent the user instead of their third party identifier.
     */
    @Schema(
        name = "display_name",
        description = "A name which can be displayed to represent the user instead of their third party identifier.",
        required = true
    )
    @JsonbProperty("display_name")
    private String displayName;

    /**
     * Required. A block of content which has been signed, which servers can use to verify the event. Clients should ignore this.
     */
    @Schema(
        description = "A block of content which has been signed, which servers can use to verify the event. Clients should ignore this.",
        required = true
    )
    private Signed signed;

    @JsonProperty("display_name")
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Signed getSigned() {
        return signed;
    }

    public void setSigned(Signed signed) {
        this.signed = signed;
    }
}
