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

package io.github.ma1uta.matrix.client.model.room;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

/**
 * Third party signed.
 */
@Schema(
    description = "Third party signed."
)
public class ThirdPartySigned {

    /**
     * Required. The Matrix ID of the user who issued the invite.
     */
    @Schema(
        description = "The Matrix ID of the user who issued the invite."
    )
    private String sender;

    /**
     * Required. The Matrix ID of the invitee.
     */
    @Schema(
        description = "The Matrix ID of the invitee."
    )
    private String mxid;

    /**
     * Required. The state key of the m.third_party_invite event.
     */
    @Schema(
        description = "The state key of the m.third_party_invite event."
    )
    private String token;

    /**
     * Required. A signatures object containing a signature of the entire signed object.
     */
    @Schema(
        description = "A signatures object containing a signature of the entire signed object."
    )
    private Map<String, Map<String, String>> signatures;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMxid() {
        return mxid;
    }

    public void setMxid(String mxid) {
        this.mxid = mxid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Map<String, Map<String, String>> getSignatures() {
        return signatures;
    }

    public void setSignatures(Map<String, Map<String, String>> signatures) {
        this.signatures = signatures;
    }
}
