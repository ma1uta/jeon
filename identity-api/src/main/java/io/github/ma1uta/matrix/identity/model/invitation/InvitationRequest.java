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

package io.github.ma1uta.matrix.identity.model.invitation;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * JSON body request of the invitation api.
 */
@Schema(
    description = "JSON boy requesst of the invitation api."
)
public class InvitationRequest {

    /**
     * Required. The literal string email.
     */
    @Schema(
        description = "The literal string email",
        required = true
    )
    private String medium;

    /**
     * Required. The email address of the invited user.
     */
    @Schema(
        description = "The email address of the invited user.",
        required = true
    )
    private String address;

    /**
     * Required. The Matrix room ID to which the user is invited.
     */
    @Schema(
        description = "The Matrix room ID to which the user is invited.",
        required = true
    )
    private String roomId;

    /**
     * Required. The Matrix user ID of the inviting user.
     */
    @Schema(
        description = "The Matrix user ID of the inviting user.",
        required = true
    )
    private String sender;

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
