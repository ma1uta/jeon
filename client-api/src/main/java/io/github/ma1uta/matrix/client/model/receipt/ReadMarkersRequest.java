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

package io.github.ma1uta.matrix.client.model.receipt;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;

/**
 * JSON body of the read marker request.
 */
@Schema(
    description = "JSON body of the read marker request."
)
public class ReadMarkersRequest {

    /**
     * Required. The event ID the read marker should be located at. The event MUST belong to the room.
     */
    @Schema(
        description = "The event ID the read marker should be located at. The event MUST belong to the room."
    )
    @JsonbProperty("m.fully_read")
    private String fullyRead;

    /**
     * The event ID to set the read receipt location at. This is equivalent to calling /receipt/m.read/$elsewhere:domain.com
     * and is provided here to save that extra call.
     */
    @Schema(
        description = "The event ID to set the read receipt location at. This is equivalent to calling"
            + " /receipt/m.read/$elsewhere:domain.com and is provided here to save that extra call."
    )
    @JsonbProperty("m.read")
    private String read;

    @JsonProperty("m.fully_read")
    public String getFullyRead() {
        return fullyRead;
    }

    public void setFullyRead(String fullyRead) {
        this.fullyRead = fullyRead;
    }

    @JsonProperty("m.read")
    public String getRead() {
        return read;
    }

    public void setRead(String read) {
        this.read = read;
    }
}
