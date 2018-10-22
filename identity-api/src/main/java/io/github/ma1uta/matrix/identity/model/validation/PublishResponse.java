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

package io.github.ma1uta.matrix.identity.model.validation;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ma1uta.matrix.support.DeserializerUtil;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;
import javax.json.bind.annotation.JsonbProperty;

/**
 * Result of the publishing association.
 */
@Schema(
    description = "Result of the publishing association."
)
public class PublishResponse {

    /**
     * Required. The 3pid address of the user being looked up.
     */
    @Schema(
        description = "The 3pid address of the user being looked up.",
        required = true
    )
    private String address;

    /**
     * Required. The medium type of the 3pid.
     */
    @Schema(
        description = "The medium type of the 3pid.",
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
    private String mxid;

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
     * Required. The signatures of the verifying identity servers which show that the association should be trusted, if you trust
     * the verifying identity services.
     */
    @Schema(
        description = "The signatures of the verifying identity servers which show that the association should be trusted, if you trust"
            + " the verifying identity services.",
        required = true
    )
    private Map<String, Map<String, String>> signatures;

    public PublishResponse() {
    }

    @SuppressWarnings("unchecked")
    public PublishResponse(Map props) {
        this.address = DeserializerUtil.toString(props, "address");
        this.medium = DeserializerUtil.toString(props, "medium");
        this.mxid = DeserializerUtil.toString(props, "mxid");
        this.notBefore = DeserializerUtil.toLong(props, "not_before");
        this.notAfter = DeserializerUtil.toLong(props, "not_after");
        this.ts = DeserializerUtil.toLong(props, "ts");
        this.signatures = DeserializerUtil.toMap(props, "signatures",
            entry -> (String) entry.getKey(),
            entry -> (Map<String, String>) entry.getValue()
        );
    }

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

    public String getMxid() {
        return mxid;
    }

    public void setMxid(String mxid) {
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
