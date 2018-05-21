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

import java.util.List;

/**
 * JSON body response for federation public room api.
 */
public class PublicRoomResponse {

    /**
     * Room aliases.
     */
    private List<String> aliases;

    /**
     * Can guest join to this room or not.
     */
    @JsonProperty("guest_can_join")
    private Boolean guestCanJoin;

    /**
     * Room name.
     */
    private String name;

    /**
     * Number of the joined members.
     */
    @JsonProperty("num_joined_members")
    private Long numJoinedMembers;

    /**
     * Room odentifier.
     */
    @JsonProperty("room_id")
    private String roomId;

    /**
     * Can everyone read room history or not (?).
     */
    @JsonProperty("world_readable")
    private Boolean worldReadable;

    public List<String> getAliases() {
        return aliases;
    }

    public void setAliases(List<String> aliases) {
        this.aliases = aliases;
    }

    public Boolean getGuestCanJoin() {
        return guestCanJoin;
    }

    public void setGuestCanJoin(Boolean guestCanJoin) {
        this.guestCanJoin = guestCanJoin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getNumJoinedMembers() {
        return numJoinedMembers;
    }

    public void setNumJoinedMembers(Long numJoinedMembers) {
        this.numJoinedMembers = numJoinedMembers;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public Boolean getWorldReadable() {
        return worldReadable;
    }

    public void setWorldReadable(Boolean worldReadable) {
        this.worldReadable = worldReadable;
    }
}
