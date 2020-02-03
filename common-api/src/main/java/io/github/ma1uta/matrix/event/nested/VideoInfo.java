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

package io.github.ma1uta.matrix.event.nested;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;

/**
 * Video info.
 */
@Schema(
    description = "Video info."
)
public class VideoInfo extends FileInfo {

    /**
     * The intended display height of the image in pixels. This may differ from the intrinsic dimensions of the image file.
     */
    @Schema(
        name = "h",
        description = "The intended display height of the image in pixels. This may differ from the intrinsic dimensions of the image file."
    )
    @JsonbProperty("h")
    private Long height;

    /**
     * The intended display width of the image in pixels. This may differ from the intrinsic dimensions of the image file.
     */
    @Schema(
        name = "w",
        description = "The intended display width of the image in pixels. This may differ from the intrinsic dimensions of the image file."
    )
    @JsonbProperty("w")
    private Long width;

    /**
     * The duration of the video in milliseconds.
     */
    @Schema(
        description = "The duration of the video in milliseconds."
    )
    private Long duration;

    @JsonProperty("h")
    public Long getHeight() {
        return height;
    }

    public void setHeight(Long height) {
        this.height = height;
    }

    @JsonProperty("w")
    public Long getWidth() {
        return width;
    }

    public void setWidth(Long width) {
        this.width = width;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }
}
