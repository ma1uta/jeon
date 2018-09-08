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

package io.github.ma1uta.matrix.client.model.room;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Join request.
 */
@ApiModel(description = "Join request.")
public class JoinRequest {

    /**
     * A signature of an m.third_party_invite token to prove that this user owns a third party identity which has been invited to the room.
     */
    @ApiModelProperty(
        name = "third_party_signed",
        value = "A signature of an m.third_party_invite token to prove that this user owns "
            + "a third party identity which has been invited to the room."
    )
    @JsonProperty("third_party_signed")
    private ThirdPartySigned thirdPartySigned;

    public ThirdPartySigned getThirdPartySigned() {
        return thirdPartySigned;
    }

    public void setThirdPartySigned(ThirdPartySigned thirdPartySigned) {
        this.thirdPartySigned = thirdPartySigned;
    }
}
