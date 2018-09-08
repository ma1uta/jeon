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

package io.github.ma1uta.matrix.events.messages;

import io.github.ma1uta.matrix.Event;
import io.github.ma1uta.matrix.events.RoomMessage;
import io.github.ma1uta.matrix.events.nested.AudioInfo;
import io.github.ma1uta.matrix.events.nested.EncryptedFile;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This message represents a single audio clip.
 */
@ApiModel(description = "This message represents a single audio clip.")
public class Audio extends RoomMessage {

    /**
     * Metadata for the audio clip referred to in url.
     */
    @ApiModelProperty(
        value = "Metadata for the audio clip referred to in url."
    )
    private AudioInfo info;

    /**
     * Required. The URL to the audio clip.
     */
    @ApiModelProperty(
        value = "The URL to the audio clip.",
        required = true
    )
    private String url;

    /**
     * Required if the file is encrypted. Information on the encrypted file, as specified in End-to-end encryption.
     */
    @ApiModelProperty(
        value = "Required if the file is encrypted. Information on the encrypted file, as specified in End-to-end encryption."
    )
    private EncryptedFile file;

    public AudioInfo getInfo() {
        return info;
    }

    public void setInfo(AudioInfo info) {
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
        return Event.MessageType.AUDIO;
    }
}
