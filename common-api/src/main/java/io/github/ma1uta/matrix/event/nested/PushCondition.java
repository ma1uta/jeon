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

package io.github.ma1uta.matrix.event.nested;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Push condition.
 */
@Schema(
    description = "Push condition."
)
public class PushCondition {

    /**
     * Required. One of: ["event_match", "contains_display_name", "room_member_count"].
     */
    @Schema(
        required = true,
        allowableValues = "event_match, contains_display_name, room_member_count]"
    )
    private String kind;

    /**
     * Required for event_match conditions. The dot- separated field of the event to match.
     */
    @Schema(
        description = "Required for event_match conditions. The dot- separated field of the event to match."
    )
    private String key;

    /**
     * Required for event_match conditions. The glob- style pattern to match against. Patterns with no special glob characters should
     * be treated as having asterisks prepended and appended when testing the condition.
     */
    @Schema(
        description = "Required for event_match conditions. The glob- style pattern to match against. Patterns with no special glob "
            + "characters should be treated as having asterisks prepended and appended when testing the condition."
    )
    private String pattern;

    /**
     * Required for room_member_count conditions. A decimal integer optionally prefixed by one of, ==, &lt;, &gt;, &gt;= or &lt;=.
     * A prefix of &lt; matches rooms where the member count is strictly less than the given number and so forth.
     * If no prefix is present, this parameter defaults to ==.
     */
    @Schema(
        description = "Required for room_member_count conditions. A decimal integer optionally prefixed by one of, ==, <, >, >= or <=. "
            + "A prefix of < matches rooms where the member count is strictly less than the given number and so forth. "
            + "If no prefix is present, this parameter defaults to==."
    )
    private String is;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getIs() {
        return is;
    }

    public void setIs(String is) {
        this.is = is;
    }
}
