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

package io.github.ma1uta.matrix.client.model.content;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;

/**
 * Content config.
 */
@Schema(
    description = "Content config."
)
public class ContentConfig {

    /**
     * The maximum size an upload can be in bytes. Clients SHOULD use this as a guide when uploading content.
     * If not listed or null, the size limit should be treated as unknown.
     */
    @Schema(
        name = "m.upload.size",
        description = "The maximum size an upload can be in bytes. Clients SHOULD use this as a"
            + "guide when uploading content. If not listed or null, the size limit should be treated as unknown."
    )
    @JsonbProperty("m.upload.size")
    private long uploadSize;

    @JsonProperty("m.upload.size")
    public long getUploadSize() {
        return uploadSize;
    }

    public void setUploadSize(long uploadSize) {
        this.uploadSize = uploadSize;
    }
}
