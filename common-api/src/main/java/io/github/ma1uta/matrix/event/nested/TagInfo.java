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

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * TagContent info.
 */
@Schema(
    description = "TagContent info."
)
public class TagInfo {

    /**
     * A number in a range [0,1] describing a relative position of the room under the given tag.
     */
    @Schema(
        description = "A number in a range [0,1] describing a relative position of the room under the given tag."
    )
    private Long order;

    public Long getOrder() {
        return order;
    }

    public void setOrder(Long order) {
        this.order = order;
    }
}
