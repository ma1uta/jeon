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

package io.github.ma1uta.matrix.client.model.voip;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * The TURN server credentials.
 */
@ApiModel(description = "The TURN server credentials.")
public class VoipResponse {

    /**
     * Required. The username to use.
     */
    @ApiModelProperty(
        value = "The username to use.",
        required = true
    )
    private String username;

    /**
     * Required. The password to use.
     */
    @ApiModelProperty(
        value = "The password to use.",
        required = true
    )
    private String password;

    /**
     * Required. A list of TURN URIs.
     */
    @ApiModelProperty(
        value = "A list of TURN URIs.",
        required = true
    )
    private List<String> uris;

    /**
     * Required. The time-to-live in seconds.
     */
    @ApiModelProperty(
        value = "The time-to-live in seconds.",
        required = true
    )
    private Long ttl;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
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
