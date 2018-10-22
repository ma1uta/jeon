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

package io.github.ma1uta.matrix.support;

import static io.github.ma1uta.matrix.event.Event.MessageType.AUDIO;
import static io.github.ma1uta.matrix.event.Event.MessageType.EMOTE;
import static io.github.ma1uta.matrix.event.Event.MessageType.FILE;
import static io.github.ma1uta.matrix.event.Event.MessageType.IMAGE;
import static io.github.ma1uta.matrix.event.Event.MessageType.LOCATION;
import static io.github.ma1uta.matrix.event.Event.MessageType.NOTICE;
import static io.github.ma1uta.matrix.event.Event.MessageType.TEXT;
import static io.github.ma1uta.matrix.event.Event.MessageType.VIDEO;

import io.github.ma1uta.matrix.event.content.RoomMessageContent;
import io.github.ma1uta.matrix.event.message.Audio;
import io.github.ma1uta.matrix.event.message.Emote;
import io.github.ma1uta.matrix.event.message.File;
import io.github.ma1uta.matrix.event.message.Image;
import io.github.ma1uta.matrix.event.message.Location;
import io.github.ma1uta.matrix.event.message.Notice;
import io.github.ma1uta.matrix.event.message.RawMessageContent;
import io.github.ma1uta.matrix.event.message.Text;
import io.github.ma1uta.matrix.event.message.Video;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * Message room deserializer.
 */
public abstract class RoomMessageDeserializer {

    private static RoomMessageDeserializer instance;

    /**
     * Get the room message deserializer.
     *
     * @return the room message deserializer.
     */
    public static RoomMessageDeserializer getInstance() {
        if (instance == null) {
            synchronized (RoomMessageDeserializer.class) {
                if (instance == null) {
                    ServiceLoader<RoomMessageDeserializer> serviceLoader = ServiceLoader.load(RoomMessageDeserializer.class);
                    Iterator<RoomMessageDeserializer> iterator = serviceLoader.iterator();
                    if (iterator.hasNext()) {
                        instance = iterator.next();
                        instance.init();
                    } else {
                        throw new RuntimeException("Cannot find the room message deserializer. Please put one to the classpath.");
                    }
                }
            }
        }
        return instance;
    }

    protected void init() {
    }

    /**
     * Deserialize a room message.
     *
     * @param props the room message properties.
     * @return the room message instance.
     */
    public RoomMessageContent deserialize(Map props) {
        if (props == null) {
            return null;
        }

        String type = (String) props.get("msgtype");

        if (type == null) {
            throw new RuntimeException("Missing the msgtype.");
        }

        switch (type) {
            case TEXT:
                return new Text(props);
            case EMOTE:
                return new Emote(props);
            case NOTICE:
                return new Notice(props);
            case IMAGE:
                return new Image(props);
            case FILE:
                return new File(props);
            case LOCATION:
                return new Location(props);
            case VIDEO:
                return new Video(props);
            case AUDIO:
                return new Audio(props);
            default:
                return parse(props, type);
        }
    }

    /**
     * Deserialize a room message.
     *
     * @param bytes the room message.
     * @return the room message instance.
     * @throws IOException when conversion failed.
     */
    public RoomMessageContent deserialize(byte[] bytes) throws IOException {
        return deserialize(bytesToMap(bytes));
    }

    /**
     * Deserialize a room message.
     *
     * @param inputStream the room message.
     * @return the room message instance.
     * @throws IOException when conversion failed.
     */
    public RoomMessageContent deserialize(InputStream inputStream) throws IOException {
        return deserialize(streamToMap(inputStream));
    }

    protected RoomMessageContent parse(Map props, String type) {
        return new RawMessageContent(props);
    }

    /**
     * Convert a byte array to the property map.
     *
     * @param bytes byte array.
     * @return the property map.
     */
    protected abstract Map bytesToMap(byte[] bytes) throws IOException;

    /**
     * Convert a input stream to the property map.
     *
     * @param inputStream the input stream.
     * @return the property map.
     */
    protected abstract Map streamToMap(InputStream inputStream) throws IOException;
}
