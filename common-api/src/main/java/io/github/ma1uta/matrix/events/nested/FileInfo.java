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
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * File info.
 */
@ApiModel(description = "File info.")
public class FileInfo {

    /**
     * The mimetype of the image, e.g. image/jpeg.
     */
    @ApiModelProperty("The mimetype of the image, e.g. image/jpeg.")
    private String mimetype;

    /**
     * Size of the image in bytes.
     */
    @ApiModelProperty("Size of the image in bytes.")
    private Long size;

    /**
     * The URL to a thumbnail of the image.
     */
    @ApiModelProperty(name = "thumbnail_url", value = "The URL to a thumbnail of the image.")
    @JsonProperty("thumbnail_url")
    private String thumbnailUrl;

    /**
     * Metadata about the image referred to in thumbnail_url.
     */
    @ApiModelProperty(name = "thumbnail_info", value = "Metadata about the image referred to in thumbnail_url.")
    @JsonProperty("thumbnail_info")
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

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public ThumbnailInfo getThumbnailInfo() {
        return thumbnailInfo;
    }

    public void setThumbnailInfo(ThumbnailInfo thumbnailInfo) {
        this.thumbnailInfo = thumbnailInfo;
    }
}
