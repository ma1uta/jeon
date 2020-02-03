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

package io.github.ma1uta.matrix.client.model.sync;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;
import javax.json.bind.annotation.JsonbProperty;

/**
 * JSON body response for sync api.
 */
@Schema(
    description = "JSON body response for sync api."
)
public class SyncResponse {

    /**
     * Required. The batch token to supply in the since param of the next /sync request.
     */
    @Schema(
        description = "The batch token to supply in the since param of the next /sync request."
    )
    @JsonbProperty("next_batch")
    private String nextBatch;

    /**
     * Updates to rooms.
     */
    @Schema(
        description = "Updates to rooms."
    )
    private Rooms rooms;

    /**
     * The updates to the presence status of other users.
     */
    @Schema(
        description = "The updates to the presence status of other users."
    )
    private Presence presence;

    /**
     * The global private data created by this user.
     */
    @Schema(
        description = "The global private data created by this user."
    )
    @JsonbProperty("account_data")
    private AccountData accountData;

    /**
     * Information on the send-to-device messages for the client device, as defined in Send-to-Device messaging.
     */
    @Schema(
        description = "Information on the send-to-device messages for the client device, as defined in Send-to-Device messaging."
    )
    @JsonbProperty("to_device")
    private ToDevice toDevice;

    /**
     * Information on end-to-end device updates, as specified in End-to-end encryption.
     */
    @Schema(
        description = "Information on end-to-end device updates, as specified in End-to-end encryption."
    )
    @JsonbProperty("device_lists")
    private DeviceLists deviceLists;

    /**
     * Information on end-to-end encryption keys, as specified in End-to-end encryption.
     */
    @Schema(
        description = "Information on end-to-end encryption keys, as specified in End-to-end encryption."
    )
    @JsonbProperty("device_one_time_keys_count")
    private Map<String, Long> deviceOneTimeKeysCount;

    @JsonProperty("next_batch")
    public String getNextBatch() {
        return nextBatch;
    }

    public void setNextBatch(String nextBatch) {
        this.nextBatch = nextBatch;
    }

    public Rooms getRooms() {
        return rooms;
    }

    public void setRooms(Rooms rooms) {
        this.rooms = rooms;
    }

    public Presence getPresence() {
        return presence;
    }

    public void setPresence(Presence presence) {
        this.presence = presence;
    }

    @JsonProperty("account_data")
    public AccountData getAccountData() {
        return accountData;
    }

    public void setAccountData(AccountData accountData) {
        this.accountData = accountData;
    }

    @JsonProperty("to_device")
    public ToDevice getToDevice() {
        return toDevice;
    }

    public void setToDevice(ToDevice toDevice) {
        this.toDevice = toDevice;
    }

    @JsonProperty("device_lists")
    public DeviceLists getDeviceLists() {
        return deviceLists;
    }

    public void setDeviceLists(DeviceLists deviceLists) {
        this.deviceLists = deviceLists;
    }

    @JsonProperty("device_one_time_keys_count")
    public Map<String, Long> getDeviceOneTimeKeysCount() {
        return deviceOneTimeKeysCount;
    }

    public void setDeviceOneTimeKeysCount(Map<String, Long> deviceOneTimeKeysCount) {
        this.deviceOneTimeKeysCount = deviceOneTimeKeysCount;
    }
}
