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

package io.github.ma1uta.matrix.client.model.voip;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * The TURN server credentials.
 */
@Schema(
    description = "The TURN server credentials."
)
public class VoipResponse {

    /**
     * Required. The username to use.
     */
    @Schema(
        description = "The username to use."
    )
    private String username;

    /**
     * Required. The password to use.
     */
    @Schema(
        description = "The password to use."
    )
    private char[] password;

    /**
     * Required. A list of TURN URIs.
     */
    @Schema(
        description = "A list of TURN URIs."
    )
    private List<String> uris;

    /**
     * Required. The time-to-live in seconds.
     */
    @Schema(
        description = "The time-to-live in seconds."
    )
    private Long ttl;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    public List<String> getUris() {
        return uris;
    }

    public void setUris(List<String> uris) {
        this.uris = uris;
    }

    public Long getTtl() {
        return ttl;
    }

    public void setTtl(Long ttl) {
        this.ttl = ttl;
    }
}
