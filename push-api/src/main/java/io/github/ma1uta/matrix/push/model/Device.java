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

package io.github.ma1uta.matrix.push.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ma1uta.matrix.PusherData;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;
import javax.json.bind.annotation.JsonbProperty;

/**
 * Device.
 */
@Schema(
    description = "Device."
)
public class Device {

    /**
     * Required. The app_id given when the pusher was created.
     */
    @Schema(
        name = "app_id",
        description = "The app_id given when the pusher was created.",
        required = true
    )
    @JsonbProperty("app_id")
    private String appId;

    /**
     * Required. The pushkey given when the pusher was created.
     */
    @Schema(
        name = "pushkey",
        description = "The pushkey given when the pusher was created.",
        required = true
    )
    @JsonbProperty("pushkey")
    private String pushKey;

    /**
     * The unix timestamp (in seconds) when the pushkey was last updated.
     */
    @Schema(
        name = "pushkey_ts",
        description = "The unix timestamp (in seconds) when the pushkey was last updated."
    )
    @JsonbProperty("pushkey_ts")
    private Long pushKeyTs;

    /**
     * A dictionary of additional pusher-specific data. For 'http' pushers, this is the data dictionary passed in at pusher creation
     * minus the url key.
     */
    @Schema(
        description = "A dictionary of additional pusher-specific data. For 'http' pushers, this is the data dictionary passed in at"
            + " pusher creation minus the url key."
    )
    private PusherData data;

    /**
     * A dictionary of customisations made to the way this notification is to be presented. These are added by push rules.
     */
    @Schema(
        description = "A dictionary of customisations made to the way this notification is to be presented. These are added by push rules."
    )
    private Map<String, String> tweaks;

    @JsonProperty("app_id")
    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    @JsonProperty("pushkey")
    public String getPushKey() {
        return pushKey;
    }

    public void setPushKey(String pushKey) {
        this.pushKey = pushKey;
    }

    @JsonProperty("pushkey_ts")
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
