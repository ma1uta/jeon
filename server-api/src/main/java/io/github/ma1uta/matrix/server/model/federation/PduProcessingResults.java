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

package io.github.ma1uta.matrix.server.model.federation;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

/**
 * PDU processing results.
 */
@Schema(
    description = "PDU processing results."
)
public class PduProcessingResults {

    /**
     * Required. The PDUs from the original transaction. The string key represents the ID of the PDU (event) that was processed.
     */
    @Schema(
        description = "The PDUs from the original transaction. The string key represents the ID of the PDU (event) that was processed.",
        required = true
    )
    private Map<String, PduProcessingResult> pdus;

    public Map<String, PduProcessingResult> getPdus() {
        return pdus;
    }

    public void setPdus(Map<String, PduProcessingResult> pdus) {
        this.pdus = pdus;
    }
}
