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

/**
 * Event container.
 */
@Schema(
    description = "Event container."
)
public class EventContainer {

    /**
     * Required. An invite event. Note that events have a different format depending on the room version - check
     * the room version specification for precise event formats.
     */
    @Schema(
        description = "An invite event. Note that events have a different format depending on the room version - check"
            + " the room version specification for precise event formats.",
        required = true
    )
    private InviteEvent event;

    public InviteEvent getEvent() {
        return event;
    }

    public void setEvent(InviteEvent event) {
        this.event = event;
    }
}
