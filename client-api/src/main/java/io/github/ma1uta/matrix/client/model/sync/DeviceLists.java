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

package io.github.ma1uta.matrix.client.model.sync;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * Device lists.
 */
@Schema(
    description = "Device lists."
)
public class DeviceLists {

    /**
     * List of users who have updated their device identity keys since the previous sync response.
     */
    @Schema(
        description = "List of users who have updated their device identity keys since the previous sync response."
    )
    private List<String> changed;

    /**
     * List of users with whom we do not share any encrypted rooms anymore since the previous sync response.
     */
    @Schema(
        description = "List of users with whom we do not share any encrypted rooms anymore since the previous sync response."
    )
    private List<String> left;

    public List<String> getChanged() {
        return changed;
    }

    public void setChanged(List<String> changed) {
        this.changed = changed;
    }

    public List<String> getLeft() {
        return left;
    }

    public void setLeft(List<String> left) {
        this.left = left;
    }
}
