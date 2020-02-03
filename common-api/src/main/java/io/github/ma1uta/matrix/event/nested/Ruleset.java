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

import java.util.List;

/**
 * Ruleset.
 */
@Schema(
    description = "Ruleset."
)
public class Ruleset {

    /**
     * Content rules.
     */
    @Schema(
        description = "Content rules."
    )
    private List<PushRule> content;

    /**
     * Override rules.
     */
    @Schema(
        description = "Override rules."
    )
    private List<PushRule> override;

    /**
     * Room rules.
     */
    @Schema(
        description = "Room rules."
    )
    private List<PushRule> room;

    /**
     * Sender rules.
     */
    @Schema(
        description = "Sender rules."
    )
    private List<PushRule> sender;

    /**
     * Underride rules.
     */
    @Schema(
        description = "Underride rules."
    )
    private List<PushRule> underride;

    public List<PushRule> getContent() {
        return content;
    }

    public void setContent(List<PushRule> content) {
        this.content = content;
    }

    public List<PushRule> getOverride() {
        return override;
    }

    public void setOverride(List<PushRule> override) {
        this.override = override;
    }

    public List<PushRule> getRoom() {
        return room;
    }

    public void setRoom(List<PushRule> room) {
        this.room = room;
    }

    public List<PushRule> getSender() {
        return sender;
    }

    public void setSender(List<PushRule> sender) {
        this.sender = sender;
    }

    public List<PushRule> getUnderride() {
        return underride;
    }

    public void setUnderride(List<PushRule> underride) {
        this.underride = underride;
    }
}
