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

package io.github.ma1uta.matrix.client.model.capability;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;
import javax.json.bind.annotation.JsonbProperty;

/**
 * The room versions the server supports.
 */
@Schema(
    description = "The room versions the server supports."
)
public class RoomVersionsCapability {

    /**
     * Available values of the `available` property.
     */
    public static class Availability {

        private Availability() {
            //singleton
        }

        /**
         * Stable.
         */
        public static final String STABLE = "stable";

        /**
         * Unstable.
         */
        public static final String UNSTABLE = "unstable";
    }

    /**
     * Required. The default room version the server is using for new rooms.
     */
    @Schema(
        name = "default",
        description = "The default room version the server is using for new rooms.",
        required = true
    )
    @JsonbProperty("default")
    private String defaultVersion;

    /**
     * Required. A detailed description of the room versions the server supports.
     */
    @Schema(
        description = "A detailed description of the room versions the server supports.",
        required = true
    )
    private Map<String, String> available;

    @JsonProperty("default")
    public String getDefaultVersion() {
        return defaultVersion;
    }

    public void setDefaultVersion(String defaultVersion) {
        this.defaultVersion = defaultVersion;
    }

    public Map<String, String> getAvailable() {
        return available;
    }

    public void setAvailable(Map<String, String> available) {
        this.available = available;
    }
}
