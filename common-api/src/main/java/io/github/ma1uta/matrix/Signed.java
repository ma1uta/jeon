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

package io.github.ma1uta.matrix;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

/**
 * Signed part of the invitation request.
 */
@Schema(
    description = "Signed part of the invitation request."
)
public class Signed {

    /**
     * Required. The invited matrix user ID. Must be equal to the user_id property of the event.
     */
    @Schema(
        description = "The invited matrix user ID. Must be equal to the user_id property of the event.",
        required = true
    )
    private String mxid;

    /**
     * Required. The token property of the containing third_party_invite object.
     */
    @Schema(
        description = "The token property of the containing third_party_invite object.",
        required = true)
    private String token;

    /**
     * Required. A single signature from the verifying server, in the format specified by the Signing Events section of the server-server
     * API.
     */
    @Schema(
        description = "A single signature from the verifying server, in the format specified by the Signing Events section"
            + " of the server-server API.",
        required = true
    )
    private Map<String, Map<String, String>> signatures;

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
