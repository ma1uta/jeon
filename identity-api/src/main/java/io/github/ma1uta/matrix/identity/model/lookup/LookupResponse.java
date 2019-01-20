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

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ma1uta.matrix.Id;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;
import javax.json.bind.annotation.JsonbProperty;

/**
 * Look up response the Matrix user ID for a 3pid.
 */
@Schema(
    description = "Look up response the Matrix user ID for a 3pid."
)
public class LookupResponse {

    /**
     * Required. The 3pid address of the user being looked up, matching the address requested.
     */
    @Schema(
        description = "The 3pid address of the user being looked up, matching the address requested.",
        required = true
    )
    private String address;

    /**
     * Required. A medium from the 3PID Types Appendix, matching the medium requested.
     */
    @Schema(
        description = "A medium from the 3PID Types Appendix, matching the medium requested.",
        required = true
    )
    private String medium;

    /**
     * Required. The Matrix user ID associated with the 3pid.
     */
    @Schema(
        description = "The Matrix user ID associated with the 3pid.",
        required = true
    )
    private Id mxid;

    /**
     * Required. A unix timestamp before which the association is not known to be valid.
     */
    @Schema(
        name = "not_before",
        description = "A unix timestamp before which the association is not known to be valid.",
        required = true
    )
    @JsonbProperty("not_before")
    private Long notBefore;

    /**
     * Required. A unix timestamp after which the association is not known to be valid.
     */
    @Schema(
        name = "not_after",
        description = "A unix timestamp after which the association is not known to be valid.",
        required = true
    )
    @JsonbProperty("not_after")
    private Long notAfter;

    /**
     * Required. The unix timestamp at which the association was verified.
     */
    @Schema(
        description = "The unix timestamp at which the association was verified.",
        required = true
    )
    private Long ts;

    /**
     * Required. The signatures of the verifying identity services which show that the association should be trusted,
     * if you trust the verifying identity services.
     */
    @Schema(
        description = "The signatures of the verifying identity services which show that the association should be trusted, if you trust"
            + " the verifying identity services.",
        required = true
    )
    private Map<String, Map<String, String>> signatures;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public Id getMxid() {
        return mxid;
    }

    public void setMxid(Id mxid) {
        this.mxid = mxid;
    }

    @JsonProperty("not_before")
    public Long getNotBefore() {
        return notBefore;
    }

    public void setNotBefore(Long notBefore) {
        this.notBefore = notBefore;
    }

    @JsonProperty("not_after")
    public Long getNotAfter() {
        return notAfter;
    }

    public void setNotAfter(Long notAfter) {
        this.notAfter = notAfter;
    }

    public Long getTs() {
        return ts;
    }

    public void setTs(Long ts) {
        this.ts = ts;
    }

    public Map<String, Map<String, String>> getSignatures() {
        return signatures;
    }

    public void setSignatures(Map<String, Map<String, String>> signatures) {
        this.signatures = signatures;
    }
}
