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

package io.github.ma1uta.matrix.server.model.key;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;
import javax.json.bind.annotation.JsonbProperty;

/**
 * JSON body response for key api.
 */
@Schema(
    description = "JSON body response for key api."
)
public class KeyResponse {

    /**
     * The public keys that the server used to use and when it stopped using them.
     */
    @Schema(
        name = "old_verify_keys",
        description = "The public keys that the server used to use and when it stopped using them."
    )
    @JsonbProperty("old_verify_keys")
    private Map<String, OldVerifyKey> oldVerifyKeys;

    /**
     * DNS name of the homeserver.
     */
    @Schema(
        name = "server_name",
        description = "DNS name of the homeserver."
    )
    @JsonbProperty("server_name")
    private String serverName;

    /**
     * Digital signatures for this object signed using the verify_keys.
     */
    @Schema(
        description = "Digital signatures for this object signed using the verify_keys."
    )
    private Map<String, Map<String, String>> signatures;

    /**
     * POSIX timestamp when the list of valid keys should be refreshed.
     */
    @Schema(
        description = "POSIX timestamp when the list of valid keys should be refreshed. Keys used beyond this timestamp"
            + " are no longer valid."
    )
    @JsonbProperty("valid_until_ts")
    private Long validUntilTs;

    /**
     * Public keys of the homeserver for verifying digital signatures.
     */
    @Schema(
        description = "Public keys of the homeserver for verifying digital signatures."
    )
    @JsonbProperty("verify_keys")
    private Map<String, VerifyKey> verifyKeys;

    @JsonProperty("old_verify_keys")
    public Map<String, OldVerifyKey> getOldVerifyKeys() {
        return oldVerifyKeys;
    }

    public void setOldVerifyKeys(Map<String, OldVerifyKey> oldVerifyKeys) {
        this.oldVerifyKeys = oldVerifyKeys;
    }

    @JsonProperty("server_name")
    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public Map<String, Map<String, String>> getSignatures() {
        return signatures;
    }

    public void setSignatures(Map<String, Map<String, String>> signatures) {
        this.signatures = signatures;
    }

    @JsonbProperty("valid_until_ts")
    public Long getValidUntilTs() {
        return validUntilTs;
    }

    public void setValidUntilTs(Long validUntilTs) {
        this.validUntilTs = validUntilTs;
    }

    @JsonProperty("verify_keys")
    public Map<String, VerifyKey> getVerifyKeys() {
        return verifyKeys;
    }

    public void setVerifyKeys(Map<String, VerifyKey> verifyKeys) {
        this.verifyKeys = verifyKeys;
    }
}
