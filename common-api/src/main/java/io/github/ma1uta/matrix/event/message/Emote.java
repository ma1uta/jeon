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

package io.github.ma1uta.matrix.event.message;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * This message is similar to m.text except that the sender is 'performing' the action contained in the body key, similar to /me in IRC.
 * This message should be prefixed by the name of the sender. This message could also be represented in a different colour to distinguish
 * it from regular m.text messages.
 */
@Schema(
    description = "This message is similar to m.text except that the sender is 'performing' the action contained in the body key,"
        + " similar to /me in IRC. This message should be prefixed by the name of the sender. This message could also be represented in"
        + " a different colour to distinguish it from regular m.text messages."
)
public class Emote extends FormattedBody {

    /**
     * This message is similar to m.text except that the sender is 'performing' the action contained in the body key,
     * similar to /me in IRC. This message should be prefixed by the name of the sender. This message could also be
     * represented in a different colour to distinguish it from regular m.text messages.
     */
    public static final String MSGTYPE = "m.emote";

    @Override
    public String getMsgtype() {
        return MSGTYPE;
    }
}
