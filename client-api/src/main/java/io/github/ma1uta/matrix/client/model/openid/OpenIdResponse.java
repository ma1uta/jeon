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

package io.github.ma1uta.matrix.client.model.openid;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;

/**
 * JSON body response of the OpenID API.
 */
@Schema(
    description = "JSON body response of the OpenID API."
)
public class OpenIdResponse {

    /**
     * Required. An access token the consumer may use to verify the identity of the person who generated the token.
     * This is given to the federation API GET /openid/userinfo.
     */
    @Schema(
        description = "An access token the consumer may use to verify the identity of the person who generated the token."
            + " This is given to the federation API GET /openid/userinfo."
    )
    @JsonbProperty("access_token")
    private String accessToken;

    /**
     * Required. The string Bearer.
     */
    @Schema(
        description = "The string Bearer."
    )
    @JsonbProperty("token_type")
    private String tokenType;

    /**
     * Required. The homeserver domain the consumer should use when attempting to verify the user's identity.
     */
    @Schema(
        description = "The homeserver domain the consumer should use when attempting to verify the user's identity."
    )
    @JsonbProperty("matrix_server_name")
    private String matrixServerName;

    /**
     * Required. The number of seconds before this token expires and a new one must be generated.
     */
    @Schema(
        description = "The number of seconds before this token expires and a new one must be generated."
    )
    @JsonbProperty("expires_in")
    private Long expiresIn;

    @JsonProperty("access_token")
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @JsonProperty("token_type")
    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    @JsonProperty("matrix_server_name")
    public String getMatrixServerName() {
        return matrixServerName;
    }

    public void setMatrixServerName(String matrixServerName) {
        this.matrixServerName = matrixServerName;
    }

    @JsonProperty("expires_in")
    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }
}
