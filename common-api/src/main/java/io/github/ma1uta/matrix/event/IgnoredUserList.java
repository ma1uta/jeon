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

package io.github.ma1uta.matrix.event;

import io.github.ma1uta.matrix.event.content.IgnoredUserListContent;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * A map of users which are considered ignored is kept in account_data in an event type of m.ignored_user_list.
 */
@Schema(
    description = "A map of users which are considered ignored is kept in account_data in an event type of m.ignored_user_list."
)
public class IgnoredUserList extends Event<IgnoredUserListContent> {

    /**
     * A map of users which are considered ignored is kept in account_data in an event type of m.ignored_user_list.
     */
    public static final String TYPE = "m.ignored_user_list";

    @Override
    public String getType() {
        return TYPE;
    }
}
