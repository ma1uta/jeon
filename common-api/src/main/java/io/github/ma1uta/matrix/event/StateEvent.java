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

package io.github.ma1uta.matrix.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ma1uta.matrix.event.content.EventContent;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;

/**
 * Event.
 *
 * @param <C> type of the event content.
 */
@Schema(
    description = "Event.",
    subTypes = {
        PolicyRuleRoom.class,
        PolicyRuleServer.class,
        PolicyRuleUser.class,
        RoomAliases.class,
        RoomAvatar.class,
        RoomCanonicalAlias.class,
        RoomCreate.class,
        RoomEncryption.class,
        RoomGuestAccess.class,
        RoomHistoryVisibility.class,
        RoomJoinRules.class,
        RoomMember.class,
        RoomName.class,
        RoomPinned.class,
        RoomPowerLevels.class,
        RoomServerAcl.class,
        RoomThirdPartyInvite.class,
        RoomTombstone.class,
        RoomTopic.class
    }
)
public abstract class StateEvent<C extends EventContent> extends RoomEvent<C> {

    /**
     * Optional. The previous content for this event. If there is no previous content, this key will be missing.
     */
    @Schema(
        name = "prev_content",
        description = "The previous content for this event. If there is no previous content, this key will be missing."
    )
    @JsonbProperty("prev_content")
    private C prevContent;

    /**
     * Required. A unique key which defines the overwriting semantics for this piece of room state. This value is often a
     * zero-length string. The presence of this key makes this event a State Event. The key MUST NOT start with '_'.
     */
    @Schema(
        name = "state_key",
        description = " A unique key which defines the overwriting semantics for this piece of room "
            + "state. This value is often a zero-length string. The presence of this key makes this event a State Event. The key MUST "
            + "NOT start with '_'.",
        required = true
    )
    @JsonbProperty("state_key")
    private String stateKey;

    @JsonProperty("state_key")
    public String getStateKey() {
        return stateKey;
    }

    public void setStateKey(String stateKey) {
        this.stateKey = stateKey;
    }

    @JsonProperty("prev_content")
    public C getPrevContent() {
        return prevContent;
    }

    public void setPrevContent(C prevContent) {
        this.prevContent = prevContent;
    }
}
