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

package io.github.ma1uta.matrix.server.model.key;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;

/**
 * Old verify key.
 */
@Schema(
    description = "Old verify key."
)
public class OldVerifyKey extends VerifyKey {

    /**
     * POSIX timestamp in milliseconds for when this key expired.
     */
    @Schema(
        name = "expired_ts",
        description = " POSIX timestamp in milliseconds for when this key expired."
    )
    @JsonbProperty("expired_ts")
    private Long expiredTs;

    @JsonProperty("expired_ts")
    public Long getExpiredTs() {
        return expiredTs;
    }

    public void setExpiredTs(Long expiredTs) {
        this.expiredTs = expiredTs;
    }
}
