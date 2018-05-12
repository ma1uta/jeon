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

package io.github.ma1uta.matrix.push.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ma1uta.matrix.PusherData;

import java.util.Map;

/**
 * Device.
 */
public class Device {

    /**
     * The app_id given when the pusher was created.
     */
    @JsonProperty("app_id")
    private String appId;

    /**
     * The pushkey given when the pusher was created.
     */
    @JsonProperty("pushkey")
    private String pushKey;

    /**
     * The unix timestamp (in seconds) when the pushkey was last updated.
     */
    @JsonProperty("pushkey_ts")
    private Long pushKeyTs;

    /**
     * A dictionary of additional pusher-specific data. For 'http' pushers, this is the data dictionary passed in at pusher creation
     * minus the url key.
     */
    private PusherData data;

    /**
     * A dictionary of customisations made to the way this notification is to be presented. These are added by push rules.
     */
    private Map<String, String> tweaks;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getPushKey() {
        return pushKey;
    }

    public void setPushKey(String pushKey) {
        this.pushKey = pushKey;
    }

    public Long getPushKeyTs() {
        return pushKeyTs;
    }

    public void setPushKeyTs(Long pushKeyTs) {
        this.pushKeyTs = pushKeyTs;
    }

    public PusherData getData() {
        return data;
    }

    public void setData(PusherData data) {
        this.data = data;
    }

    public Map<String, String> getTweaks() {
        return tweaks;
    }

    public void setTweaks(Map<String, String> tweaks) {
        this.tweaks = tweaks;
    }
}
