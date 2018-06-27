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

package io.github.ma1uta.matrix.client.model.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * JSON body request for filter api (create filter).
 */
@ApiModel(description = "JSON body request for filter api.")
public class FilterData {

    /**
     * Event formats.
     */
    public static class EventFormat {

        protected EventFormat() {
        }

        /**
         * Client.
         */
        public static final String CLIENT = "client";

        /**
         * Server or federation.
         */
        public static final String FEDERATION = "federation";
    }

    /**
     * List of event fields to include. If this list is absent then all fields are included. The entries may include '.' charaters
     * to indicate sub-fields. So ['content.body'] will include the 'body' field of the 'content' object. A literal '.' character
     * in a field name may be escaped using a '\'. A server may include more fields than were requested.
     */
    @ApiModelProperty(name = "event_fields", value = "List of event fields to include. If this list is absent then all fields are "
        + "included. The entries may include '.' charaters to indicate sub-fields. So ['content.body'] will include the 'body' "
        + "field of the 'content' object. A literal '.' character in a field name may be escaped using a '\'. A server may include "
        + "more fields than were requested.")
    @JsonProperty("event_fields")
    private List<String> eventFields;

    /**
     * The format to use for events. 'client' will return the events in a format suitable for clients. 'federation' will return the
     * raw event as receieved over federation. The default is 'client'. One of: ["client", "federation"]
     */
    @ApiModelProperty(name = "event_format", value = "The format to use for events. 'client' will return the events in a format "
        + "suitable for clients. 'federation' will return the raw event as receieved over federation. The default is 'client'.",
        allowableValues = "['client', 'federation']")
    @JsonProperty("event_format")
    private String eventFormat;

    /**
     * The presence updates to include.
     */
    @ApiModelProperty("The presence updates to include.")
    private Filter presence;

    /**
     * The user account data that isn't associated with rooms to include.
     */
    @ApiModelProperty(name = "account_data", value = "The user account data that isn't associated with rooms to include.")
    @JsonProperty("account_data")
    private Filter accountData;

    /**
     * Filters to be applied to room data.
     */
    @ApiModelProperty("Filters to be applied to room data.")
    private RoomFilter room;

    public List<String> getEventFields() {
        return eventFields;
    }

    public void setEventFields(List<String> eventFields) {
        this.eventFields = eventFields;
    }

    public String getEventFormat() {
        return eventFormat;
    }

    public void setEventFormat(String eventFormat) {
        this.eventFormat = eventFormat;
    }

    public Filter getPresence() {
        return presence;
    }

    public void setPresence(Filter presence) {
        this.presence = presence;
    }

    public Filter getAccountData() {
        return accountData;
    }

    public void setAccountData(Filter accountData) {
        this.accountData = accountData;
    }

    public RoomFilter getRoom() {
        return room;
    }

    public void setRoom(RoomFilter room) {
        this.room = room;
    }
}
