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

package io.github.ma1uta.matrix.client.model.deprecatedsync;

import io.github.ma1uta.matrix.event.Event;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.core.SecurityContext;

/**
 * JSON body response of the deprecated initial sync.
 *
 * @deprecated in favor of {@link io.github.ma1uta.matrix.client.api.SyncApi#sync(String, String, Boolean, String, Long,
 * javax.ws.rs.core.UriInfo, javax.ws.rs.core.HttpHeaders, AsyncResponse, SecurityContext)}.
 */
@Deprecated
@Schema(
    description = "JSON body response of the deprecated initial sync."
)
public class DeprecatedInitialSyncResponse {

    /**
     * Required. A token which correlates to the last value in chunk. This token should be used with the /events API to listen
     * for new events.
     */
    @Schema(
        description = "A token which correlates to the last value in chunk. This token should be used with the /events API to listen"
            + " for new events."
    )
    private String end;

    /**
     * Required. A list of presence events.
     */
    @Schema(
        description = "A list of presence events."
    )
    private List<Event> presence;

    /**
     * Required. Rooms info.
     */
    @Schema(
        description = "Rooms info."
    )
    private List<DeprecatedRoomInfo> rooms;

    /**
     * The global private data created by this user.
     */
    @Schema(
        description = "The global private data create by this user."
    )
    private List<Event> accountData;

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public List<Event> getPresence() {
        return presence;
    }

    public void setPresence(List<Event> presence) {
        this.presence = presence;
    }

    public List<DeprecatedRoomInfo> getRooms() {
        return rooms;
    }

    public void setRooms(List<DeprecatedRoomInfo> rooms) {
        this.rooms = rooms;
    }

    public List<Event> getAccountData() {
        return accountData;
    }

    public void setAccountData(List<Event> accountData) {
        this.accountData = accountData;
    }
}
