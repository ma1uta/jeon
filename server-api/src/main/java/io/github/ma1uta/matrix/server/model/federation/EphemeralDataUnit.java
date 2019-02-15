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

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ma1uta.matrix.event.content.EventContent;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;

/**
 * These events are pushed between pairs of homeservers. They are not persisted and are not part of the history of a room, nor does the
 * receiving homeserver have to reply to them.
 */
@Schema(
    description = "Ephemeral data unit."
)
public class EphemeralDataUnit {

    /**
     * Required. The type of the ephemeral message.
     */
    @Schema(
        name = "edu_type",
        description = "The type of ephemeral message.",
        required = true
    )
    @JsonbProperty("edu_type")
    private String eduType;

    /**
     * Actual nested content.
     */
    private EventContent content;

    @JsonProperty("edu_type")
    public String getEduType() {
        return eduType;
    }

    public void setEduType(String eduType) {
        this.eduType = eduType;
    }

    public EventContent getContent() {
        return content;
    }

    public void setContent(EventContent content) {
        this.content = content;
    }
}
