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

package io.github.ma1uta.matrix.client.model.version;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import java.util.Map;
import javax.json.bind.annotation.JsonbProperty;

/**
 * The versions supported by the server.
 *
 * @author ma1uta
 */
@Schema(
    description = "The versions supported by the server."
)
public class VersionsResponse {

    /**
     * The supported versions.
     */
    @Schema(
        description = "The supported versions.",
        required = true
    )
    private List<String> versions;

    @Schema(
        name = "unstable_features",
        description = "Experimental features the server supports. Features not listed here, or the lack of this property all together,"
            + " indicate that a feature is not supported."
    )
    @JsonbProperty("unstable_features")
    private Map<String, Boolean> unstableFeatures;

    public List<String> getVersions() {
        return versions;
    }

    public void setVersions(List<String> versions) {
        this.versions = versions;
    }

    @JsonProperty("unstable_features")
    public Map<String, Boolean> getUnstableFeatures() {
        return unstableFeatures;
    }

    public void setUnstableFeatures(Map<String, Boolean> unstableFeatures) {
        this.unstableFeatures = unstableFeatures;
    }
}
