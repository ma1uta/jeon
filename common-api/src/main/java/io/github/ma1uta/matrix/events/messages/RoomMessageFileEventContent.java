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
import io.github.ma1uta.matrix.events.RoomMessageEventContent;
import io.github.ma1uta.matrix.events.nested.FileInfo;

/**
 * This message represents a generic file.
 */
public class RoomMessageFileEventContent extends RoomMessageEventContent {

    /**
     * Required. The original filename of the uploaded file.
     */
    private String filename;

    /**
     * Information about the file referred to in url.
     */
    private FileInfo info;

    /**
     * Required. The URL to the file.
     */
    private String url;

    @Override
    public String getMsgtype() {
        return Event.MessageType.FILE;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public FileInfo getInfo() {
        return info;
    }

    public void setInfo(FileInfo info) {
        this.info = info;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
