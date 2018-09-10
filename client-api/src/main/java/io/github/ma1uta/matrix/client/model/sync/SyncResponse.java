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

package io.github.ma1uta.matrix.client.model.sync;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Map;

/**
 * JSON body response for sync api.
 */
@ApiModel(description = "JSON body response for sync api.")
public class SyncResponse {

    /**
     * Required. The batch token to supply in the since param of the next /sync request.
     */
    @ApiModelProperty(
        name = "next_batch",
        value = "The batch token to supply in the since param of the next /sync request.",
        required = true
    )
    @JsonProperty("next_batch")
    private String nextBatch;

    /**
     * Updates to rooms.
     */
    @ApiModelProperty(
        value = "Updates to rooms."
    )
    private Rooms rooms;

    /**
     * The updates to the presence status of other users.
     */
    @ApiModelProperty(
        value = "The updates to the presence status of other users."
    )
    private Presence presence;

    /**
     * The global private data created by this user.
     */
    @ApiModelProperty(
        name = "account_data",
        value = "The global private data created by this user."
    )
    @JsonProperty("account_data")
    private AccountData accountData;

    /**
     * Information on the send-to-device messages for the client device, as defined in Send-to-Device messaging.
     */
    @ApiModelProperty(
        name = "to_device",
        value = "Information on the send-to-device messages for the client device, as defined in Send-to-Device messaging."
    )
    @JsonProperty("to_device")
    private ToDevice toDevice;

    /**
     * Information on end-to-end device updates, as specified in End-to-end encryption.
     */
    @ApiModelProperty(
        name = "device_lists",
        value = "Information on end-to-end device updates, as specified in End-to-end encryption."
    )
    @JsonProperty("device_lists")
    private DeviceLists deviceLists;

    /**
     * Information on end-to-end encryption keys, as specified in End-to-end encryption.
     */
    @ApiModelProperty(
        name = "device_one_time_keys_count",
        value = "Information on end-to-end encryption keys, as specified in End-to-end encryption."
    )
    @JsonProperty("device_one_time_keys_count")
    private Map<String, Long> deviceOneTimeKeysCount;

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

    public AccountData getAccountData() {
        return accountData;
    }

    public void setAccountData(AccountData accountData) {
        this.accountData = accountData;
    }

    public ToDevice getToDevice() {
        return toDevice;
    }

    public void setToDevice(ToDevice toDevice) {
        this.toDevice = toDevice;
    }

    public DeviceLists getDeviceLists() {
        return deviceLists;
    }

    public void setDeviceLists(DeviceLists deviceLists) {
        this.deviceLists = deviceLists;
    }

    public Map<String, Long> getDeviceOneTimeKeysCount() {
        return deviceOneTimeKeysCount;
    }

    public void setDeviceOneTimeKeysCount(Map<String, Long> deviceOneTimeKeysCount) {
        this.deviceOneTimeKeysCount = deviceOneTimeKeysCount;
    }
}
