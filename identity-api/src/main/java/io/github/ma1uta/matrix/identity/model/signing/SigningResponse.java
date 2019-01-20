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

package io.github.ma1uta.matrix.identity.model.signing;

import io.github.ma1uta.matrix.Id;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

/**
 * JSON body response of the signing api.
 */
@Schema(
    description = "JSON body response of the signing api."
)
public class SigningResponse {

    /**
     * Required. The Matrix user ID of the user accepting the invitation.
     */
    @Schema(
        description = "The Matrix user ID of the user accepting the invitation.",
        required = true
    )
    private Id mxid;

    /**
     * Required. The Matrix user ID of the user who sent the invitation.
     */
    @Schema(
        description = "The Matrix user ID of the user who sent the invitation.",
        required = true
    )
    private Id sender;

    /**
     * Required. The signature of the mxid, sender, and token.
     */
    @Schema(
        description = "The signature of the mxid, sender, and token.",
        required = true
    )
    private Map<Id, Map<Id, String>> signatures;

    /**
     * Required. The token for the invitation.
     */
    @Schema(
        description = "The token for the invitation.",
        required = true
    )
    private String token;

    public Id getMxid() {
        return mxid;
    }

    public void setMxid(Id mxid) {
        this.mxid = mxid;
    }

    public Id getSender() {
        return sender;
    }

    public void setSender(Id sender) {
        this.sender = sender;
    }

    public Map<Id, Map<Id, String>> getSignatures() {
        return signatures;
    }

    public void setSignatures(Map<Id, Map<Id, String>> signatures) {
        this.signatures = signatures;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
