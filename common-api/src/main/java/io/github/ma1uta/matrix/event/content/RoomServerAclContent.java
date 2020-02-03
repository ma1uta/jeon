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

package io.github.ma1uta.matrix.event.content;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import javax.json.bind.annotation.JsonbProperty;

/**
 * An event to indicate which servers are permitted to participate in the room. Server ACLs may allow or deny groups of hosts.
 * All servers participating in the room, including those that are denied, are expected to uphold the server ACL.
 * Servers that do not uphold the ACLs MUST be added to the denied hosts list in order for the ACLs to remain effective.
 * <br>
 * The allow and deny lists are lists of globs supporting ? and * as wildcards. When comparing against the server ACLs,
 * the suspect server's port number must not be considered. Therefore evil.com, evil.com:8448, and evil.com:1234 would
 * all match rules that apply to evil.com, for example.
 * <br>
 * The ACLs are applied to servers when they make requests, and are applied in the following order:
 * <ol>
 * <li>If there is no m.room.server_acl event in the room state, allow.</li>
 * <li>If the server name is an IP address (v4 or v6) literal, and allow_ip_literals is present and false, deny.</li>
 * <li>If the server name matches an entry in the deny list, deny.</li>
 * <li>If the server name matches an entry in the allow list, allow.</li>
 * <li>Otherwise, deny.</li>
 * </ol>
 */
@Schema(
    description = "An event to indicate which servers are permitted to participate in the room. Server ACLs may allow or deny"
        + " groups of hosts."
)
public class RoomServerAclContent implements EventContent {

    /**
     * True to allow server names that are IP address literals. False to deny. Defaults to true if missing or otherwise not a boolean.
     * <br>
     * This is strongly recommended to be set to false as servers running with IP literal names are strongly discouraged in order
     * to require legitimate homeservers to be backed by a valid registered domain name.
     */
    @Schema(
        name = "allow_ip_literals",
        description = "True to allow server names that are IP address literals. False to deny. Defaults to true if missing or otherwise"
            + " not a boolean. This is strongly recommended to be set to false as servers running with IP literal names are strongly"
            + " discouraged in order to require legitimate homeservers to be backed by a valid registered domain name."
    )
    @JsonbProperty("allow_ip_literals")
    private Boolean allowIpLiterals;

    /**
     * The server names to allow in the room, excluding any port information. Wildcards may be used to cover a wider range of hosts,
     * where * matches zero or more characters and ? matches exactly one character.
     * <br>
     * This defaults to an empty list when not provided, effectively disallowing every server.
     */
    @Schema(
        description = "The server names to allow in the room, excluding any port information. Wildcards may be used to cover a wider range"
            + " of hosts, where * matches zero or more characters and ? matches exactly one character."
            + " This defaults to an empty list when not provided, effectively disallowing every server."
    )
    private List<String> allow;

    /**
     * The server names to disallow in the room, excluding any port information. Wildcards may be used to cover a wider range of hosts,
     * where * matches zero or more characters and ? matches exactly one character.
     * <br>
     * This defaults to an empty list when not provided.
     */
    @Schema(
        description = "The server names to disallow in the room, excluding any port information. Wildcards may be used to cover a wider"
            + " range of hosts, where * matches zero or more characters and ? matches exactly one character."
            + " This defaults to an empty list when not provided."
    )
    private List<String> deny;

    @JsonProperty("allow_ip_literals")
    public Boolean getAllowIpLiterals() {
        return allowIpLiterals;
    }

    public void setAllowIpLiterals(Boolean allowIpLiterals) {
        this.allowIpLiterals = allowIpLiterals;
    }

    public List<String> getAllow() {
        return allow;
    }

    public void setAllow(List<String> allow) {
        this.allow = allow;
    }

    public List<String> getDeny() {
        return deny;
    }

    public void setDeny(List<String> deny) {
        this.deny = deny;
    }
}
