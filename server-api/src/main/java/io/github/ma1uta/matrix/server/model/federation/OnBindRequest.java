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

package io.github.ma1uta.matrix.server.model.federation;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * Bind request.
 */
@Schema(
    description = "Bind request."
)
public class OnBindRequest {

    /**
     * Required. The type of third party identifier. Currently only "email" is a possible value.
     */
    @Schema(
        description = "The type of third party identifier. Currently only \"email\" is a possible value.",
        required = true
    )
    private String medium;

    /**
     * Required. The third party identifier itself. For example, an email address.
     */
    @Schema(
        description = "The third party identifier itself. For example, an email address.",
        required = true
    )
    private String address;

    /**
     * Required. The user that is now bound to the third party identifier.
     */
    @Schema(
        description = "The user that is now bound to the third party identifier.",
        required = true
    )
    private String mxid;

    /**
     * Required. A list of pending invites that the third party identifier has received.
     */
    @Schema(
        description = "A list of pending invites that the third party identifier has received.",
        required = true
    )
    private List<Invite> invites;

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMxid() {
        return mxid;
    }

    public void setMxid(String mxid) {
        this.mxid = mxid;
    }

    public List<Invite> getInvites() {
        return invites;
    }

    public void setInvites(List<Invite> invites) {
        this.invites = invites;
    }
}
