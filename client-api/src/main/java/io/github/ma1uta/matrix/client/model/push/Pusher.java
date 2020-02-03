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

package io.github.ma1uta.matrix.client.model.push;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ma1uta.matrix.PusherData;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;

/**
 * Pusher.
 * <br>
 * A pusher is a worker on the homeserver that manages the sending of HTTP notifications for a user.
 * A user can have multiple pushers: one per device.
 */
@Schema(
    description = "Pusher. A pusher is a worker on the homeserver that manages the sending of HTTP notifications for a user. "
        + "A user can have multiple pushers: one per device."
)
public class Pusher {

    /**
     * This is a unique identifier for this pusher. See /set for more detail. Max length, 512 bytes.
     */
    @Schema(
        description = "This is a unique identifier for this pusher. See /set for more detail. Max length, 512 bytes."
    )
    private String pushkey;

    /**
     * The kind of pusher. "http" is a pusher that sends HTTP pokes.
     */
    @Schema(
        description = "The kind of pusher. \"http\" is a pusher that sends HTTP pokes."
    )
    private String kind;

    /**
     * This is a reverse-DNS style identifier for the application. Max length, 64 chars.
     */
    @Schema(
        description = "This is a reverse-DNS style identifier for the application. Max length, 64 chars."
    )
    @JsonbProperty("app_id")
    private String appId;

    /**
     * A string that will allow the user to identify what application owns this pusher.
     */
    @Schema(
        description = "A string that will allow the user to identify what application owns this pusher."
    )
    @JsonbProperty("app_display_name")
    private String appDisplayName;

    /**
     * A string that will allow the user to identify what device owns this pusher.
     */
    @Schema(
        description = "A string that will allow the user to identify what device owns this pusher."
    )
    @JsonbProperty("device_display_name")
    private String deviceDisplayName;

    /**
     * This string determines which set of device specific rules this pusher executes.
     */
    @Schema(
        description = "This string determines which set of device specific rules this pusher executes."
    )
    @JsonbProperty("profile_tag")
    private String profileTag;

    /**
     * The preferred language for receiving notifications (e.g. 'en' or 'en-US')
     */
    @Schema(
        description = "The preferred language for receiving notifications (e.g. 'en' or 'en-US')"
    )
    private String lang;

    /**
     * A dictionary of information for the pusher implementation itself.
     */
    @Schema(
        description = "A dictionary of information for the pusher implementation itself."
    )
    private PusherData data;

    public String getPushkey() {
        return pushkey;
    }

    public void setPushkey(String pushkey) {
        this.pushkey = pushkey;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    @JsonProperty("app_id")
    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    @JsonProperty("app_display_name")
    public String getAppDisplayName() {
        return appDisplayName;
    }

    public void setAppDisplayName(String appDisplayName) {
        this.appDisplayName = appDisplayName;
    }

    @JsonProperty("device_display_name")
    public String getDeviceDisplayName() {
        return deviceDisplayName;
    }

    public void setDeviceDisplayName(String deviceDisplayName) {
        this.deviceDisplayName = deviceDisplayName;
    }

    @JsonProperty("profile_tag")
    public String getProfileTag() {
        return profileTag;
    }

    public void setProfileTag(String profileTag) {
        this.profileTag = profileTag;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public PusherData getData() {
        return data;
    }

    public void setData(PusherData data) {
        this.data = data;
    }
}
