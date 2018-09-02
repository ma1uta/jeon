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

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * JSON body request of the signing api.
 */
@ApiModel(description = "JSON body request of the signing api.")
public class SigningRequest {

    /**
     * Required. The Matrix user ID of the user accepting the invitation.
     */
    @ApiModelProperty(
        value = "The Matrix user ID of the user accepting the invitation.",
        required = true
    )
    private String mxid;

    /**
     * Required. The token from the call to store-invite.
     */
    @ApiModelProperty(
        value = "The token from the call to store-invite.",
        required = true
    )
    private String token;

    /**
     * Required. The private key, encoded as Unpadded base64.
     */
    @ApiModelProperty(
        name = "private_key",
        value = "The private key, encoded as Unpadded base64.",
        required = true
    )
    @JsonProperty("private_key")
    private String privateKey;

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

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }
}
