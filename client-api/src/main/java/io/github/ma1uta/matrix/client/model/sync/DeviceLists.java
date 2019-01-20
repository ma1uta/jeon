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

package io.github.ma1uta.matrix.client.model.sync;

import io.github.ma1uta.matrix.Id;
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
    private List<Id> changed;

    /**
     * List of users with whom we do not share any encrypted rooms anymore since the previous sync response.
     */
    @Schema(
        description = "List of users with whom we do not share any encrypted rooms anymore since the previous sync response."
    )
    private List<Id> left;

    public List<Id> getChanged() {
        return changed;
    }

    public void setChanged(List<Id> changed) {
        this.changed = changed;
    }

    public List<Id> getLeft() {
        return left;
    }

    public void setLeft(List<Id> left) {
        this.left = left;
    }
}
