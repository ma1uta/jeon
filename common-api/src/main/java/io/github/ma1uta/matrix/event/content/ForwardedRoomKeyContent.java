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

package io.github.ma1uta.matrix.event.content;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import javax.json.bind.annotation.JsonbProperty;

/**
 * This event type is used to forward keys for end-to-end encryption. Typically it is encrypted as an m.room.encrypted event,
 * then sent as a to-device event.
 */
@Schema(
    description = "This event type is used to forward keys for end-to-end encryption."
        + " Typically it is encrypted as an m.room.encrypted event, then sent as a to-device event."
)
public class ForwardedRoomKeyContent implements EventContent {

    /**
     * Required. The encryption algorithm the key in this event is to be used with.
     */
    @Schema(
        description = "Required. The encryption algorithm the key in this event is to be used with.",
        required = true
    )
    private String algorithm;

    /**
     * Required. The room where the key is used.
     */
    @Schema(
        name = "room_id",
        description = "The room where the key is used.",
        required = true
    )
    @JsonbProperty("room_id")
    private String roomId;

    /**
     * Required. The Curve25519 key of the device which initiated the session originally.
     */
    @Schema(
        name = "sender_key",
        description = "Required. The Curve25519 key of the device which initiated the session originally.",
        required = true
    )
    @JsonbProperty("sender_key")
    private String senderKey;

    /**
     * Required. The ID of the session that the key is for.
     */
    @Schema(
        name = "session_id",
        description = "The ID of the session that the key is for.",
        required = true
    )
    @JsonbProperty("session_id")
    private String sessionId;

    /**
     * Required. The key to be exchanged.
     */
    @Schema(
        name = "session_key",
        description = "The key to be exchanged.",
        required = true
    )
    @JsonbProperty("session_key")
    private String sessionKey;

    /**
     * Required. The Ed25519 key of the device which initiated the session originally. It is 'claimed' because the receiving device
     * has no way to tell that the original room_key actually came from a device which owns the private part of this key unless they
     * have done device verification.
     */
    @Schema(
        name = "sender_claimed_ed25519_key",
        description = "The Ed25519 key of the device which initiated the session originally. It is 'claimed' because"
            + " the receiving device has no way to tell that the original room_key actually came from a device which owns the private part"
            + "of this key unless they have done device verification.",
        required = true
    )
    @JsonbProperty("sender_claimed_ed25519_key")
    private String senderClaimedEd25519Key;

    /**
     * Required. Chain of Curve25519 keys. It starts out empty, but each time the key is forwarded to another device,
     * the previous sender in the chain is added to the end of the list. For example, if the key is forwarded from A to B to C,
     * this field is empty between A and B, and contains A's Curve25519 key between B and C.
     */
    @Schema(
        name = "forwarding_curve25519_key_chain",
        description = "Chain of Curve25519 keys. It starts out empty, but each time the key is forwarded to another device,"
            + " the previous sender in the chain is added to the end of the list. For example, if the key is forwarded from A to B to C,"
            + " this field is empty between A and B, and contains A's Curve25519 key between B and C.",
        required = true
    )
    @JsonbProperty("forwarding_curve25519_key_chain")
    private List<String> forwardingCurve25519KeyChain;

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    @JsonProperty("room_id")
    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    @JsonProperty("sender_key")
    public String getSenderKey() {
        return senderKey;
    }

    public void setSenderKey(String senderKey) {
        this.senderKey = senderKey;
    }

    @JsonProperty("session_id")
    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @JsonProperty("session_key")
    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    @JsonProperty("sender_claimed_ed25219_key")
    public String getSenderClaimedEd25519Key() {
        return senderClaimedEd25519Key;
    }

    public void setSenderClaimedEd25519Key(String senderClaimedEd25519Key) {
        this.senderClaimedEd25519Key = senderClaimedEd25519Key;
    }

    @JsonProperty("forwarding_curve_25519_key_chain")
    public List<String> getForwardingCurve25519KeyChain() {
        return forwardingCurve25519KeyChain;
    }

    public void setForwardingCurve25519KeyChain(List<String> forwardingCurve25519KeyChain) {
        this.forwardingCurve25519KeyChain = forwardingCurve25519KeyChain;
    }
}
