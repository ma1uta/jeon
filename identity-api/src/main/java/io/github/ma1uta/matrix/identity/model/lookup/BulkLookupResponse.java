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
 * Response of the lookup Matrix user IDs for a list of 3pids.
 */
@Schema(
    description = "Response of the lookup Matrix user IDs for a list of 3pids."
)
public class BulkLookupResponse {

    /**
     * Required. An array of array containing the 3PID Types with the medium in first position, the address in second position
     * and Matrix ID in third position.
     */
    @Schema(
        description = "An array of array containing the 3PID Types with the medium in first position, the address in second"
            + " position and Matrix user ID in third position.",
        required = true
    )
    private List<List<String>> threepids;

    public List<List<String>> getThreepids() {
        return threepids;
    }

    public void setThreepids(List<List<String>> threepids) {
        this.threepids = threepids;
    }
}
