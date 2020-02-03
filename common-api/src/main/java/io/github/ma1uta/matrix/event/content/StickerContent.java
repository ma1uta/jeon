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

package io.github.ma1uta.matrix.event.content;

import io.github.ma1uta.matrix.event.nested.ImageInfo;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * This message represents a single sticker image.
 */
@Schema(
    description = "This message represents a single sticker image."
)
public class StickerContent implements EventContent {

    /**
     * Required. A textual representation or associated description of the sticker image. This could be the alt text of the original
     * image, or a message to accompany and further describe the sticker.
     */
    @Schema(
        description = "A textual representation or associated description of the sticker image. This could be the alt text"
            + " of the original image, or a message to accompany and further describe the sticker.",
        required = true
    )
    private String body;

    /**
     * Required. Metadata about the image referred to in url including a thumbnail representation.
     */
    @Schema(
        description = "Metadata about the image referred to in url including a thumbnail representation.",
        required = true
    )
    private ImageInfo info;

    /**
     * Required. The URL to the sticker image. This must be a valid mxc:// URI.
     */
    @Schema(
        description = "The URL to the sticker image. This must be a valid mxc:// URI.",
        required = true
    )
    private String url;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public ImageInfo getInfo() {
        return info;
    }

    public void setInfo(ImageInfo info) {
        this.info = info;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
