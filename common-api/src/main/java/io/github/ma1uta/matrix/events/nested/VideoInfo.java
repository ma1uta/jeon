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

package io.github.ma1uta.matrix.events.nested;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Video info.
 */
public class VideoInfo extends FileInfo {

    /**
     * The intended display height of the image in pixels. This may differ from the intrinsic dimensions of the image file.
     */
    @JsonProperty("h")
    private Long height;

    /**
     * The intended display width of the image in pixels. This may differ from the intrinsic dimensions of the image file.
     */
    @JsonProperty("w")
    private Long width;

    /**
     * The duration of the video in milliseconds.
     */
    private Long duration;

    /**
     * The mimetype of the video e.g. video/mp4.
     */
    private String mimetype;

    /**
     * The size of the video in bytes.
     */
    private Long size;

    public Long getHeight() {
        return height;
    }

    public void setHeight(Long height) {
        this.height = height;
    }

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

    @Override
    public String getMimetype() {
        return mimetype;
    }

    @Override
    public void setMimetype(String mimetype) {
        this.mimetype = mimetype;
    }

    @Override
    public Long getSize() {
        return size;
    }

    @Override
    public void setSize(Long size) {
        this.size = size;
    }
}
