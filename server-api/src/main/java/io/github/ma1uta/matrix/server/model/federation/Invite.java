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
import io.github.ma1uta.matrix.Id;
import io.github.ma1uta.matrix.Signed;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;

/**
 * Invitation.
 * <br>
 * Sends when the association between mxid and pair medium-address is validated.
 */
@Schema(
    description = "Invitation."
)
public class Invite {

    /**
     * Required. The type of third party invite issues. Currently only "email" is used.
     */
    @Schema(
        description = "The type of third party invite issues. Currently only \"email\" is used.",
        required = true
    )
    private String medium;

    /**
     * Required. The third party identifier that received the invite.
     */
    @Schema(
        description = "The third party identifier that received the invite.",
        required = true
    )
    private String address;

    /**
     * Required. The now-bound user ID that received the invite.
     */
    @Schema(
        description = "The now-bound user ID that received the invite."
    )
    private Id mxid;

    /**
     * Required. The room ID the invite is valid for.
     */
    @Schema(
        name = "room_id",
        description = "The room ID the invite is valid for.",
        required = true
    )
    @JsonbProperty("room_id")
    private Id roomId;

    /**
     * Required. The user ID that sent the invite.
     */
    @Schema(
        description = "The user ID that sent the invite.",
        required = true
    )
    private Id sender;

    /**
     * Required. Signature from the identity server using a long-term private key.
     */
    private Signed signed;

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

    public Id getMxid() {
        return mxid;
    }

    public void setMxid(Id mxid) {
        this.mxid = mxid;
    }

    @JsonProperty("room_id")
    public Id getRoomId() {
        return roomId;
    }

    public void setRoomId(Id roomId) {
        this.roomId = roomId;
    }

    public Id getSender() {
        return sender;
    }

    public void setSender(Id sender) {
        this.sender = sender;
    }

    public Signed getSigned() {
        return signed;
    }

    public void setSigned(Signed signed) {
        this.signed = signed;
    }
}
