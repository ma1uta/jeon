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

package io.github.ma1uta.matrix.identity.model.lookup;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * JSON body request.
 */
@Schema(
    description = "JSON body request."
)
public class LookupRequest {

    /**
     * Required. The algorithm the client is using to encode the addresses. This should be one of the available options from /hash_details.
     */
    @Schema(
        description = "The algorithm the client is using to encode the addresses. "
            + "This should be one of the available options from /hash_details.",
        required = true
    )
    private String algorithm;

    /**
     * Required. The pepper from /hash_details. This is required even when the algorithm does not make use of it.
     */
    @Schema(
        description = "The pepper from /hash_details. This is required even when the algorithm does not make use of it.",
        required = true
    )
    private String pepper;

    /**
     * Required. The addresses to look up. The format of the entries here depend on the algorithm used.
     * Note that queries which have been incorrectly hashed or formatted will lead to no matches.
     */
    @Schema(
        description = "The addresses to look up. The format of the entries here depend on the algorithm used. "
            + " Note that queries which have been incorrectly hashed or formatted will lead to no matches.",
        required = true
    )
    private List<String> addresses;

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public String getPepper() {
        return pepper;
    }

    public void setPepper(String pepper) {
        this.pepper = pepper;
    }

    public List<String> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<String> addresses) {
        this.addresses = addresses;
    }
}
