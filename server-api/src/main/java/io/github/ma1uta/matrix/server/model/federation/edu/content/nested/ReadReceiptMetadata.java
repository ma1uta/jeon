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

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Read receipt metadata.
 */
@Schema(
    description = "Read receipt metadata."
)
public class ReadReceiptMetadata {

    /**
     * Required. A POSIX timestamp in milliseconds for when the user read the event specified in the read receipt.
     */
    @Schema(
        description = "A POSIX timestamp in milliseconds for when the user read the event specified in the read receipt.",
        required = true
    )
    private Long ts;

    public Long getTs() {
        return ts;
    }

    public void setTs(Long ts) {
        this.ts = ts;
    }
}
