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

package io.github.ma1uta.matrix.server.model.federation;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * These events are pushed between pairs of homeservers. They are not persisted and are not part of the history of a room, nor does the
 * receiving homeserver have to reply to them.
 */
public class EphemeralDataUnit {

    /**
     * The type of the ephemeral message.
     */
    @JsonProperty("edu_type")
    private String eduType;

    /**
     * Origin homeserver.
     */
    private String origin;

    /**
     * Destination homeserver.
     */
    private String destination;

    /**
     * Actual nested content.
     */
    private Map<String, Object> content;

    public String getEduType() {
        return eduType;
    }

    public void setEduType(String eduType) {
        this.eduType = eduType;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Map<String, Object> getContent() {
        return content;
    }

    public void setContent(Map<String, Object> content) {
        this.content = content;
    }
}
