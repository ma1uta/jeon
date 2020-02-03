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

import io.github.ma1uta.matrix.event.content.RoomServerAclContent;
import io.swagger.v3.oas.annotations.media.Schema;

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
public class RoomServerAcl extends StateEvent<RoomServerAclContent> {

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
    public static final String TYPE = "m.room.server_acl";

    @Override
    public String getType() {
        return TYPE;
    }
}
