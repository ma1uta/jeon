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

package io.github.ma1uta.matrix.client.model.push;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ma1uta.matrix.PusherData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Pusher.
 * <br>
 * A pusher is a worker on the homeserver that manages the sending of HTTP notifications for a user.
 * A user can have multiple pushers: one per device.
 */
@ApiModel(description = "Pusher. A pusher is a worker on the homeserver that manages the sending of HTTP notifications for a user. "
    + "A user can have multiple pushers: one per device.")
public class Pusher {

    /**
     * This is a unique identifier for this pusher. See /set for more detail. Max length, 512 bytes.
     */
    @ApiModelProperty("This is a unique identifier for this pusher. See /set for more detail. Max length, 512 bytes.")
    private String pushkey;

    /**
     * The kind of pusher. "http" is a pusher that sends HTTP pokes.
     */
    @ApiModelProperty("The kind of pusher. \"http\" is a pusher that sends HTTP pokes.")
    private String kind;

    /**
     * This is a reverse-DNS style identifier for the application. Max length, 64 chars.
     */
    @ApiModelProperty(name = "app_id", value = "This is a reverse-DNS style identifier for the application. Max length, 64 chars.")
    @JsonProperty("app_id")
    private String appId;

    /**
     * A string that will allow the user to identify what application owns this pusher.
     */
    @ApiModelProperty(name = "app_display_name", value = "A string that will allow the user to identify what application owns this pusher.")
    @JsonProperty("app_display_name")
    private String appDisplayName;

    /**
     * A string that will allow the user to identify what device owns this pusher.
     */
    @ApiModelProperty(name = "device_display_name", value = "A string that will allow the user to identify what device owns this pusher.")
    @JsonProperty("device_display_name")
    private String deviceDisplayName;

    /**
     * This string determines which set of device specific rules this pusher executes.
     */
    @ApiModelProperty(name = "profile_tag", value = "This string determines which set of device specific rules this pusher executes.")
    @JsonProperty("profile_tag")
    private String profileTag;

    /**
     * The preferred language for receiving notifications (e.g. 'en' or 'en-US')
     */
    @ApiModelProperty("The preferred language for receiving notifications (e.g. 'en' or 'en-US')")
    private String lang;

    /**
     * A dictionary of information for the pusher implementation itself.
     */
    @ApiModelProperty("A dictionary of information for the pusher implementation itself.")
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

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppDisplayName() {
        return appDisplayName;
    }

    public void setAppDisplayName(String appDisplayName) {
        this.appDisplayName = appDisplayName;
    }

    public String getDeviceDisplayName() {
        return deviceDisplayName;
    }

    public void setDeviceDisplayName(String deviceDisplayName) {
        this.deviceDisplayName = deviceDisplayName;
    }

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
