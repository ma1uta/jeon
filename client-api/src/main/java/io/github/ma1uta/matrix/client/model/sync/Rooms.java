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

import java.util.Map;

/**
 * Rooms.
 */
@Schema(
    description = "Rooms."
)
public class Rooms {

    /**
     * The rooms that the user has joined.
     */
    @Schema(
        description = "The rooms that the user has joined."
    )
    private Map<String, JoinedRoom> join;

    /**
     * The rooms that the user has been invited to.
     */
    @Schema(
        description = "The rooms that the user has been invited to."
    )
    private Map<String, InvitedRoom> invite;

    /**
     * The rooms that the user has left or been banned from.
     */
    @Schema(
        description = "The rooms that the user has left or been banned from."
    )
    private Map<String, LeftRoom> leave;

    public Map<String, JoinedRoom> getJoin() {
        return join;
    }

    public void setJoin(Map<String, JoinedRoom> join) {
        this.join = join;
    }

    public Map<String, InvitedRoom> getInvite() {
        return invite;
    }

    public void setInvite(Map<String, InvitedRoom> invite) {
        this.invite = invite;
    }

    public Map<String, LeftRoom> getLeave() {
        return leave;
    }

    public void setLeave(Map<String, LeftRoom> leave) {
        this.leave = leave;
    }
}
