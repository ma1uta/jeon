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

package io.github.ma1uta.matrix.client.model.typing;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * TypingContent body request.
 */
@Schema(
    description = "TypingContent body request."
)
public class TypingRequest {

    /**
     * Required. Whether the user is typing or not. If false, the timeout key can be omitted.
     */
    @Schema(
        description = "Whether the user is typing or not. If false, the timeout key can be omitted."
    )
    private Boolean typing;

    /**
     * The length of time in milliseconds to mark this user as typing.
     */
    @Schema(
        description = "The length of time in milliseconds to mark this user as typing."
    )
    private Long timeout;

    public Boolean getTyping() {
        return typing;
    }

    public void setTyping(Boolean typing) {
        this.typing = typing;
    }

    public Long getTimeout() {
        return timeout;
    }

    public void setTimeout(Long timeout) {
        this.timeout = timeout;
    }
}
