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
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;
import javax.json.bind.annotation.JsonbProperty;

/**
 * Receipts.
 */
@Schema(
    description = "Receipts."
)
public class ReceiptInfo {

    /**
     * A collection of users who have sent m.read receipts for this event.
     */
    @Schema(
        name = "m.read",
        description = "A collection of users who have sent m.read receipts for this event."
    )
    @JsonbProperty("m.read")
    private Map<String, ReceiptTs> read;

    @JsonProperty("m.read")
    public Map<String, ReceiptTs> getRead() {
        return read;
    }

    public void setRead(Map<String, ReceiptTs> read) {
        this.read = read;
    }
}
