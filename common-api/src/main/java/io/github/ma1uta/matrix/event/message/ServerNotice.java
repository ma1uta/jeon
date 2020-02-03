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

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ma1uta.matrix.event.content.RoomMessageContent;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;

/**
 * Represents a server notice for a user.
 */
@Schema(
    description = "Represents a server notice for a user."
)
public class ServerNotice extends RoomMessageContent {

    /**
     * Required. The type of notice being represented.
     */
    @Schema(
        name = "server_notice_type",
        description = "The type of notice being represented.",
        required = true
    )
    @JsonbProperty("server_notice_type")
    private String serverNoticeType;

    /**
     * A URI giving a contact method for the server administrator. Required if the notice type is m.server_notice.usage_limit_reached.
     */
    @Schema(
        name = "admin_contact",
        description = "A URI giving a contact method for the server administrator."
            + " Required if the notice type is m.server_notice.usage_limit_reached."
    )
    @JsonbProperty("admin_contact")
    private String adminContact;

    /**
     * The kind of usage limit the server has exceeded. Required if the notice type is m.server_notice.usage_limit_reached.
     */
    @Schema(
        name = "limit_type",
        description = "The kind of usage limit the server has exceeded. Required if the notice type is m.server_notice.usage_limit_reached."
    )
    @JsonbProperty("limit_type")
    private String limitType;

    /**
     * Message type.
     */
    public static final String MSGTYPE = "m.server_notice";

    @Override
    public String getMsgtype() {
        return MSGTYPE;
    }

    @JsonProperty("server_notice_type")
    public String getServerNoticeType() {
        return serverNoticeType;
    }

    public void setServerNoticeType(String serverNoticeType) {
        this.serverNoticeType = serverNoticeType;
    }

    @JsonProperty("admin_contact")
    public String getAdminContact() {
        return adminContact;
    }

    public void setAdminContact(String adminContact) {
        this.adminContact = adminContact;
    }

    @JsonProperty("limit_type")
    public String getLimitType() {
        return limitType;
    }

    public void setLimitType(String limitType) {
        this.limitType = limitType;
    }
}
