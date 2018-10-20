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

package io.github.ma1uta.matrix.event.nested;

import io.github.ma1uta.matrix.support.DeserializerUtil;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

/**
 * Audio type.
 */
@Schema(
    description = "Audio type."
)
public class AudioInfo {

    /**
     * The duration of the audio in milliseconds.
     */
    @Schema(
        description = "The duration of the audio in milliseconds."
    )
    private Long duration;

    /**
     * The mimetype of the audio e.g. audio/aac.
     */
    @Schema(
        description = "The mimetype of the audio e.g. audio/aac."
    )
    private String mimetype;

    /**
     * The size of the audio clip in bytes.
     */
    @Schema(
        description = "The size of the audio clip in bytes."
    )
    private Long size;

    public AudioInfo() {
    }

    public AudioInfo(Map props) {
        this.duration = DeserializerUtil.toLong(props, "duration");
        this.mimetype = DeserializerUtil.toString(props, "mimetype");
        this.size = DeserializerUtil.toLong(props, "size");
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public String getMimetype() {
        return mimetype;
    }

    public void setMimetype(String mimetype) {
        this.mimetype = mimetype;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }
}
