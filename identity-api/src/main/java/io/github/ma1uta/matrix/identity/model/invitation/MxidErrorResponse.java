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

package io.github.ma1uta.matrix.identity.model.invitation;

import io.github.ma1uta.matrix.ErrorResponse;
import io.github.ma1uta.matrix.Id;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * The Matrix error response with the MXID.
 */
@Schema(
    description = "The Matrix error response with the MXID."
)
public class MxidErrorResponse extends ErrorResponse {

    /**
     * The MXID.
     */
    @Schema(
        description = "The MXID."
    )
    private Id mxid;

    public MxidErrorResponse() {
    }

    public MxidErrorResponse(String errcode, String error) {
        super(errcode, error);
    }

    public MxidErrorResponse(String errcode, String error, Id mxid) {
        super(errcode, error);
        this.mxid = mxid;
    }

    public Id getMxid() {
        return mxid;
    }

    public void setMxid(Id mxid) {
        this.mxid = mxid;
    }
}
