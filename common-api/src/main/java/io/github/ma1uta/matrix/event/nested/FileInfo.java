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
 * File info.
 */
@Schema(
    description = "File info."
)
public class FileInfo {

    /**
     * The mimetype of the image, e.g. image/jpeg.
     */
    @Schema(
        description = "The mimetype of the image, e.g. image/jpeg."
    )
    private String mimetype;

    /**
     * Size of the image in bytes.
     */
    @Schema(
        description = "Size of the image in bytes."
    )
    private Long size;

    /**
     * The URL to a thumbnail of the image.
     */
    @Schema(
        name = "thumbnail_url",
        description = "The URL to a thumbnail of the image."
    )
    @JsonbProperty("thumbnail_url")
    private String thumbnailUrl;

    /**
     * Information on the encrypted thumbnail file, as specified in End-to-end encryption. Only present if the thumbnail is encrypted.
     */
    @JsonbProperty("thumbnail_file")
    private EncryptedFile thumbnailFile;

    /**
     * Metadata about the image referred to in thumbnail_url.
     */
    @Schema(
        name = "thumbnail_info",
        description = "Metadata about the image referred to in thumbnail_url."
    )
    @JsonbProperty("thumbnail_info")
    private ThumbnailInfo thumbnailInfo;

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

    @JsonProperty("thumbnail_url")
    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    @JsonProperty("thumbnail_file")
    public EncryptedFile getThumbnailFile() {
        return thumbnailFile;
    }

    public void setThumbnailFile(EncryptedFile thumbnailFile) {
        this.thumbnailFile = thumbnailFile;
    }

    @JsonProperty("thumbnail_info")
    public ThumbnailInfo getThumbnailInfo() {
        return thumbnailInfo;
    }

    public void setThumbnailInfo(ThumbnailInfo thumbnailInfo) {
        this.thumbnailInfo = thumbnailInfo;
    }
}
