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
import io.github.ma1uta.matrix.events.nested.VideoInfo;

/**
 * This message represents a single video clip.
 */
public class RoomMessageVideoEventContent extends RoomMessageEventContent {

    /**
     * Metadata about the video clip referred to in url.
     */
    private VideoInfo info;

    /**
     * Required. The URL to the video clip.
     */
    private String url;

    @Override
    public String getMsgtype() {
        return Event.MessageType.VIDEO;
    }

    public VideoInfo getInfo() {
        return info;
    }

    public void setInfo(VideoInfo info) {
        this.info = info;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
