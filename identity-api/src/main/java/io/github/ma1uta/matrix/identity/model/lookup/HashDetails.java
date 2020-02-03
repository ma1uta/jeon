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

package io.github.ma1uta.matrix.identity.model.lookup;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import javax.json.bind.annotation.JsonbProperty;

/**
 * Hash details.
 */
@Schema(
    description = "Hash details."
)
public class HashDetails {

    /**
     * Required. The pepper the client MUST use in hashing identifiers, and MUST supply to the /lookup endpoint when performing lookups.
     * <br>
     * Servers SHOULD rotate this string often.
     */
    @Schema(
        name = "lookup_pepper",
        description = "The pepper the client MUST use in hashing identifiers, and MUST supply to the /lookup endpoint when "
            + "performing lookups. Servers SHOULD rotate this string often.",
        required = true
    )
    @JsonbProperty("lookup_pepper")
    private String lookupPepper;

    /**
     * Required. The algorithms the server supports. Must contain at least sha256.
     */
    @Schema(
        description = "The algorithms the server supports. Must contain at least sha256.",
        required = true
    )
    private List<String> algorithms;

    @JsonProperty("lookup_pepper")
    public String getLookupPepper() {
        return lookupPepper;
    }

    public void setLookupPepper(String lookupPepper) {
        this.lookupPepper = lookupPepper;
    }

    public List<String> getAlgorithms() {
        return algorithms;
    }

    public void setAlgorithms(List<String> algorithms) {
        this.algorithms = algorithms;
    }
}
