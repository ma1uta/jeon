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

package io.github.ma1uta.matrix.server.model.bind;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ma1uta.matrix.Signed;

/**
 * Invitation.
 * <br>
 * Sends when the association between mxid and pair medium-address is validated.
 */
public class Invite {

    /**
     * 'email' or 'msisdn'.
     */
    private String medium;
    /**
     * email or phone number.
     */
    private String address;
    /**
     * Owner of the 3pid.
     */
    private String mxid;
    /**
     * The room which receive this invite.
     */
    @JsonProperty("room_id")
    private String roomId;
    /**
     * Who sends this invite.
     */
    private String sender;
    /**
     * Signed part.
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

    public String getMxid() {
        return mxid;
    }

    public void setMxid(String mxid) {
        this.mxid = mxid;
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

    public Signed getSigned() {
        return signed;
    }

    public void setSigned(Signed signed) {
        this.signed = signed;
    }
}
