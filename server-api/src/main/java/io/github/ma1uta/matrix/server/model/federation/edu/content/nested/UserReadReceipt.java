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

package io.github.ma1uta.matrix.server.model.federation.edu.content.nested;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ma1uta.matrix.Id;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import javax.json.bind.annotation.JsonbProperty;

/**
 * User read receipt.
 */
@Schema(
    description = "User read receipt."
)
public class UserReadReceipt {

    /**
     * Required. The extremity event IDs that the user has read up to.
     */
    @Schema(
        name = "event_ids",
        description = "The extremity event IDs that the user has read up to.",
        required = true
    )
    @JsonbProperty("event_ids")
    private List<Id> eventIds;

    /**
     * Required. Metadata for the read receipt.
     */
    @Schema(
        description = "Metadata for the read receipt.",
        required = true
    )
    private ReadReceiptMetadata data;

    @JsonProperty("event_ids")
    public List<Id> getEventIds() {
        return eventIds;
    }

    public void setEventIds(List<Id> eventIds) {
        this.eventIds = eventIds;
    }

    public ReadReceiptMetadata getData() {
        return data;
    }

    public void setData(ReadReceiptMetadata data) {
        this.data = data;
    }
}
