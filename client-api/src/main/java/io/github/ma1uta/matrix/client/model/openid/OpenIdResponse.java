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

package io.github.ma1uta.matrix.client.model.openid;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * JSON body response of the OpenID API.
 */
@ApiModel(description = "JSON body response of the OpenID API.")
public class OpenIdResponse {

    /**
     * Required. An access token the consumer may use to verify the identity of the person who generated the token.
     * This is given to the federation API GET /openid/userinfo.
     */
    @ApiModelProperty(
        name = "access_token",
        value = "An access token the consumer may use to verify the identity of the person who generated the token."
            + " This is given to the federation API GET /openid/userinfo.",
        required = true
    )
    @JsonProperty("access_token")
    private String accessToken;

    /**
     * Required. The string Bearer.
     */
    @ApiModelProperty(
        name = "token_type",
        value = "The string Bearer.",
        required = true
    )
    @JsonProperty("token_type")
    private String tokenType;

    /**
     * Required. The homeserver domain the consumer should use when attempting to verify the user's identity.
     */
    @ApiModelProperty(
        name = "matrix_server_name",
        value = "The homeserver domain the consumer should use when attempting to verify the user's identity.",
        required = true
    )
    @JsonProperty("matrix_server_name")
    private String matrixServerName;

    /**
     * Required. The number of seconds before this token expires and a new one must be generated.
     */
    @ApiModelProperty(
        name = "expires_in",
        value = "The number of seconds before this token expires and a new one must be generated.",
        required = true
    )
    @JsonProperty("expires_in")
    private Long expiresIn;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getMatrixServerName() {
        return matrixServerName;
    }

    public void setMatrixServerName(String matrixServerName) {
        this.matrixServerName = matrixServerName;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }
}
