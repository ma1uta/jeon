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

package io.github.ma1uta.matrix.application.model;

import io.github.ma1uta.matrix.event.Event;
import io.github.ma1uta.matrix.support.DeserializerUtil;
import io.github.ma1uta.matrix.support.EventDeserializer;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import java.util.Map;

/**
 * JSON body request of the transaction api.
 */
@Schema(
    description = "JSON body request of the transaction api."
)
public class TransactionRequest {

    /**
     * Required. A list of events, formatted as per the Client-Server API.
     */
    @Schema(
        description = "A list of events, formatted as per the Client-Server API.",
        required = true
    )
    private List<Event> events;

    public TransactionRequest() {
    }

    public TransactionRequest(Map props) {
        this.events = DeserializerUtil.toList(props, "events", map -> EventDeserializer.getInstance().deserialize((Map) map));
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
