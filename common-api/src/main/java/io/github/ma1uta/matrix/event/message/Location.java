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

package io.github.ma1uta.matrix.event.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ma1uta.matrix.event.content.RoomMessageContent;
import io.github.ma1uta.matrix.event.nested.LocationInfo;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;

/**
 * This message represents a real-world location.
 */
@Schema(
    description = "This message represents a real-world location."
)
public class Location extends RoomMessageContent {

    /**
     * This message represents a real-world location.
     */
    public static final String MSGTYPE = "m.location";

    /**
     * Information about the file referred to in url.
     */
    @Schema(
        description = "Information about the file referred to in url."
    )
    private LocationInfo info;

    /**
     * Required. A geo URI representing this location.
     */
    @Schema(
        description = "A geo URI representing this location.",
        required = true
    )
    @JsonbProperty("geo_uri")
    private String geoUri;

    public LocationInfo getInfo() {
        return info;
    }

    public void setInfo(LocationInfo info) {
        this.info = info;
    }

    @JsonProperty("geo_uri")
    public String getGeoUri() {
        return geoUri;
    }

    public void setGeoUri(String geoUri) {
        this.geoUri = geoUri;
    }

    @Override
    public String getMsgtype() {
        return MSGTYPE;
    }
}
