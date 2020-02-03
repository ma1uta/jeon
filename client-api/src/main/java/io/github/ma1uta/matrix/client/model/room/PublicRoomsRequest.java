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

package io.github.ma1uta.matrix.client.model.room;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;

/**
 * JSON body request.
 */
@Schema(
    description = "JSON body request."
)
public class PublicRoomsRequest {

    /**
     * Limit the number of results returned.
     */
    @Schema(
        description = "Limit the number of results returned."
    )
    private Long limit;

    /**
     * A pagination token from a previous request, allowing clients to get the next (or previous) batch of rooms.
     * The direction of pagination is specified solely by which token is supplied, rather than via an explicit flag.
     */
    @Schema(
        description = "A pagination token from a previous request, allowing clients to get the next (or previous) batch of rooms. "
            + "The direction of pagination is specified solely by which token is supplied, rather than via an explicit flag."
    )
    private String since;

    /**
     * Filter to apply to the results.
     */
    @Schema(
        description = "Filter to apply to the results."
    )
    private PublicRoomsFilter filter;

    /**
     * Whether or not to include all known networks/protocols from application services on the homeserver. Defaults to false.
     */
    @Schema(
        description = "Whether or not to include all known networks/protocols from application services on the homeserver.",
        defaultValue = "false"
    )
    @JsonbProperty("include_all_networks")
    private Boolean includeAllNetworks;

    /**
     * The specific third party network/protocol to request from the homeserver. Can only be used if include_all_networks is false.
     */
    @Schema(
        description = "The specific third party network/protocol to request from the homeserver. Can only be used if include_all_networks"
            + " is false."
    )
    @JsonbProperty("third_party_instance_id")
    private String thirdPartyInstanceId;

    public Long getLimit() {
        return limit;
    }

    public void setLimit(Long limit) {
        this.limit = limit;
    }

    public String getSince() {
        return since;
    }

    public void setSince(String since) {
        this.since = since;
    }

    public PublicRoomsFilter getFilter() {
        return filter;
    }

    public void setFilter(PublicRoomsFilter filter) {
        this.filter = filter;
    }

    @JsonProperty("include_all_networkd")
    public Boolean getIncludeAllNetworks() {
        return includeAllNetworks;
    }

    public void setIncludeAllNetworks(Boolean includeAllNetworks) {
        this.includeAllNetworks = includeAllNetworks;
    }

    @JsonProperty("third_party_instance_id")
    public String getThirdPartyInstanceId() {
        return thirdPartyInstanceId;
    }

    public void setThirdPartyInstanceId(String thirdPartyInstanceId) {
        this.thirdPartyInstanceId = thirdPartyInstanceId;
    }
}
