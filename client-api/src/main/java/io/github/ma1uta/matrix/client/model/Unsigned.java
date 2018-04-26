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

package io.github.ma1uta.matrix.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * UnsignedData.
 */
public class Unsigned {

    /**
     * The time in milliseconds that has elapsed since the event was sent. This field is generated by the local homeserver, and may
     * be incorrect if the local time on at least one of the two servers is out of sync, which can cause the age to either be negative
     * or greater than it actually is.
     */
    private Long age;

    /**
     * Optional. The event that redacted this event, if any.
     */
    @JsonProperty("redacted_because")
    private Event redactedBecause;

    /**
     * The client-supplied transaction ID, if the client being given the event is the same one which sent it.
     */
    @JsonProperty("transaction_id")
    private String transactionId;

    private Map<String, Object> prevContent;

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public Event getRedactedBecause() {
        return redactedBecause;
    }

    public void setRedactedBecause(Event redactedBecause) {
        this.redactedBecause = redactedBecause;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Map<String, Object> getPrevContent() {
        return prevContent;
    }

    public void setPrevContent(Map<String, Object> prevContent) {
        this.prevContent = prevContent;
    }
}
