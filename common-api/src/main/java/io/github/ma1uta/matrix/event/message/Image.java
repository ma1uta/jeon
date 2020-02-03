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

import io.github.ma1uta.matrix.event.content.RoomMessageContent;
import io.github.ma1uta.matrix.event.nested.EncryptedFile;
import io.github.ma1uta.matrix.event.nested.ImageInfo;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * This message represents a single image and an optional thumbnail.
 */
@Schema(
    description = "This message represents a single image and an optional thumbnail."
)
public class Image extends RoomMessageContent {

    /**
     * This message represents a single image and an optional thumbnail.
     */
    public static final String MSGTYPE = "m.image";

    /**
     * Information about the file referred to in url.
     */
    @Schema(
        description = "Information about the file referred to in url."
    )
    private ImageInfo info;

    /**
     * Required. The URL to the file.
     */
    @Schema(
        description = "The URL to the file.",
        required = true
    )
    private String url;

    /**
     * Required if the file is encrypted. Information on the encrypted file, as specified in End-to-end encryption.
     */
    @Schema(
        description = "Required if the file is encrypted. Information on the encrypted file, as specified in End-to-end encryption."
    )
    private EncryptedFile file;

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

    public EncryptedFile getFile() {
        return file;
    }

    public void setFile(EncryptedFile file) {
        this.file = file;
    }

    @Override
    public String getMsgtype() {
        return MSGTYPE;
    }
}
