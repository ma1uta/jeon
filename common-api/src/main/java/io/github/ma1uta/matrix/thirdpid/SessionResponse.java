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

package io.github.ma1uta.matrix.thirdpid;

import io.github.ma1uta.matrix.support.DeserializerUtil;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

/**
 * The sid generated for this session to the caller, in a JSON object containing the sid key.
 */
@Schema(
    description = "The sid generated for this session to the caller, in a JSON object containing the sid key."
)
public class SessionResponse {

    /**
     * Required. The session ID. Session IDs are opaque strings generated by the identity server.
     * They must consist entirely of the characters [0-9a-zA-Z.=_-]. Their length must not exceed 255 characters and they must not be empty.
     */
    @Schema(
        description = "The session ID. Session IDs are opaque strings generated by the identity server. They must consist entirely of"
            + " the characters [0-9a-zA-Z.=_-]. Their length must not exceed 255 characters and they must not be empty.",
        required = true
    )
    private String sid;

    public SessionResponse() {
    }

    public SessionResponse(Map props) {
        this.sid = DeserializerUtil.toString(props, "sid");
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }
}
