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

import java.util.List;
import java.util.Map;

/**
 * JSON body response for key api.
 */
public class KeyResponse {

    /**
     * the public keys that the server used to use and when it stopped using them.
     */
    @JsonProperty("old_verify_keys")
    private Map<String, Map<String, String>> oldVerifyKeys;

    /**
     * DNS name of the homeserver.
     */
    @JsonProperty("server_name")
    private String serverName;

    /**
     * Digital signatures for this object signed using the verify_keys.
     */
    private Map<String, Map<String, String>> signatures;

    /**
     * Hashes of X.509 TLS certificates used by this this server encoded as Unpadded Base64.
     */
    @JsonProperty("tls_fingerprints")
    private List<Map<String, String>> tlsFingerprints;

    /**
     * POSIX timestamp when the list of valid keys should be refreshed.
     */
    @JsonProperty("valid_until_ts")
    private Long validUntilTs;

    /**
     * Public keys of the homeserver for verifying digital signatures.
     */
    @JsonProperty("verify_keys")
    private Map<String, Map<String, String>> verifyKeys;

    public Map<String, Map<String, String>> getOldVerifyKeys() {
        return oldVerifyKeys;
    }

    public void setOldVerifyKeys(Map<String, Map<String, String>> oldVerifyKeys) {
        this.oldVerifyKeys = oldVerifyKeys;
    }

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

    public List<Map<String, String>> getTlsFingerprints() {
        return tlsFingerprints;
    }

    public void setTlsFingerprints(List<Map<String, String>> tlsFingerprints) {
        this.tlsFingerprints = tlsFingerprints;
    }

    public Long getValidUntilTs() {
        return validUntilTs;
    }

    public void setValidUntilTs(Long validUntilTs) {
        this.validUntilTs = validUntilTs;
    }

    public Map<String, Map<String, String>> getVerifyKeys() {
        return verifyKeys;
    }

    public void setVerifyKeys(Map<String, Map<String, String>> verifyKeys) {
        this.verifyKeys = verifyKeys;
    }
}
