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

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;

/**
 * Unread notification counts.
 */
@Schema(
    description = "Unread notification counts."
)
public class UnreadNotificationCounts {

    /**
     * The number of unread notifications for this room with the highlight flag set.
     */
    @Schema(
        description = "The number of unread notifications for this room with the highlight flag set."
    )
    @JsonbProperty("highlight_count")
    private Long highlightCount;

    /**
     * The total number of unread notifications for this room.
     */
    @Schema(
        description = "The total number of unread notifications for this room."
    )
    @JsonbProperty("notification_count")
    private Long notificationCount;

    @JsonProperty("highlight_count")
    public Long getHighlightCount() {
        return highlightCount;
    }

    public void setHighlightCount(Long highlightCount) {
        this.highlightCount = highlightCount;
    }

    @JsonProperty("notification_count")
    public Long getNotificationCount() {
        return notificationCount;
    }

    public void setNotificationCount(Long notificationCount) {
        this.notificationCount = notificationCount;
    }
}
