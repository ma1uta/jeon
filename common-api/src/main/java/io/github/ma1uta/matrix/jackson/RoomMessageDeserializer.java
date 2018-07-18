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

package io.github.ma1uta.matrix.jackson;

import static io.github.ma1uta.matrix.Event.MessageType.AUDIO;
import static io.github.ma1uta.matrix.Event.MessageType.EMOTE;
import static io.github.ma1uta.matrix.Event.MessageType.FILE;
import static io.github.ma1uta.matrix.Event.MessageType.IMAGE;
import static io.github.ma1uta.matrix.Event.MessageType.LOCATION;
import static io.github.ma1uta.matrix.Event.MessageType.NOTICE;
import static io.github.ma1uta.matrix.Event.MessageType.TEXT;
import static io.github.ma1uta.matrix.Event.MessageType.VIDEO;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.exc.InvalidNullException;
import com.fasterxml.jackson.databind.exc.InvalidTypeIdException;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import io.github.ma1uta.matrix.events.RoomMessage;
import io.github.ma1uta.matrix.events.messages.Audio;
import io.github.ma1uta.matrix.events.messages.Emote;
import io.github.ma1uta.matrix.events.messages.File;
import io.github.ma1uta.matrix.events.messages.Image;
import io.github.ma1uta.matrix.events.messages.Location;
import io.github.ma1uta.matrix.events.messages.Notice;
import io.github.ma1uta.matrix.events.messages.Text;
import io.github.ma1uta.matrix.events.messages.Video;
import io.github.ma1uta.matrix.events.nested.AudioInfo;
import io.github.ma1uta.matrix.events.nested.FileInfo;
import io.github.ma1uta.matrix.events.nested.ImageInfo;
import io.github.ma1uta.matrix.events.nested.LocationInfo;
import io.github.ma1uta.matrix.events.nested.ThumbnailInfo;
import io.github.ma1uta.matrix.events.nested.VideoInfo;

import java.io.IOException;
import java.util.function.Function;

/**
 * Room message deserializer.
 */
public class RoomMessageDeserializer extends JsonDeserializer<RoomMessage> {

    @Override
    public RoomMessage deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        JsonNode node = parser.getCodec().readTree(parser);

        String msgtype = asString(node, "msgtype");
        if (msgtype == null) {
            throw InvalidNullException.from(context, "Missing required property \"msgtype\"");
        }

        switch (msgtype) {
            case AUDIO:
                return audio(node);
            case EMOTE:
                return emote(node);
            case FILE:
                return file(node);
            case IMAGE:
                return image(node);
            case LOCATION:
                return location(node);
            case NOTICE:
                return notice(node);
            case TEXT:
                return text(node);
            case VIDEO:
                return video(node);
            default:
                throw InvalidTypeIdException.from(context, "Unknown msgtype: " + msgtype);
        }
    }

    protected <T extends RoomMessage> T body(T roomMessage, JsonNode node) {
        roomMessage.setBody(asString(node, "body"));
        return roomMessage;
    }

    protected Long asLong(JsonNode node, String fieldName) {
        JsonNode valueNode = node.get(fieldName);
        return valueNode.isLong() ? valueNode.asLong() : null;
    }

    protected String asString(JsonNode node, String fieldName) {
        JsonNode valueNode = node.get(fieldName);
        return valueNode != null && valueNode.isTextual() ? valueNode.asText() : null;
    }

    protected Audio audio(JsonNode node) {
        Audio audio = body(new Audio(), node);
        audio.setUrl(asString(node, "url"));
        audio.setInfo(info(node.get("info"), this::audioInfo));
        return audio;
    }

    protected Emote emote(JsonNode node) {
        return body(new Emote(), node);
    }

    protected File file(JsonNode node) {
        File file = body(new File(), node);
        file.setFilename(asString(node, "filename"));
        file.setUrl(asString(node, "url"));
        file.setInfo(info(node.get("info"), this::fileInfo));
        return file;
    }

    protected Image image(JsonNode node) {
        Image image = body(new Image(), node);
        image.setUrl(asString(node, "url"));
        image.setInfo(info(node.get("info"), this::imageInfo));
        return image;
    }

    protected Location location(JsonNode node) {
        Location location = body(new Location(), node);
        location.setGeoUri(asString(node, "geo_uri"));
        location.setInfo(info(node.get("info"), this::locationInfo));
        return location;
    }

    protected Notice notice(JsonNode node) {
        return body(new Notice(), node);
    }

    protected Text text(JsonNode node) {
        return body(new Text(), node);
    }

    protected Video video(JsonNode node) {
        Video video = body(new Video(), node);
        video.setUrl(asString(node, "url"));
        video.setInfo(info(node.get("info"), this::videoInfo));
        return video;
    }

    protected AudioInfo audioInfo(JsonNode node) {
        AudioInfo info = new AudioInfo();
        info.setSize(asLong(node, "size"));
        info.setDuration(asLong(node, "duration"));
        info.setMimetype(asString(node, "mimetype"));
        return info;
    }

    protected FileInfo fileInfo(JsonNode node) {
        return fileInfo(new FileInfo(), node);
    }

    protected <T extends FileInfo> T fileInfo(T info, JsonNode node) {
        info.setMimetype(asString(node, "mimetype"));
        info.setSize(asLong(node, "size"));
        info.setThumbnailUrl(asString(node, "thumbnail_url"));
        info.setThumbnailInfo(info(node.get("thumbnail_info"), this::thumbnailInfo));
        return info;
    }

    protected ThumbnailInfo thumbnailInfo(JsonNode node) {
        ThumbnailInfo info = new ThumbnailInfo();
        info.setHeight(asLong(node, "h"));
        info.setWidth(asLong(node, "w"));
        info.setSize(asLong(node, "size"));
        info.setMimetype(asString(node, "mimetype"));
        return info;
    }

    protected ImageInfo imageInfo(JsonNode node) {
        ImageInfo info = fileInfo(new ImageInfo(), node);
        info.setHeight(asLong(node, "h"));
        info.setWidth(asLong(node, "w"));
        return info;
    }

    protected LocationInfo locationInfo(JsonNode node) {
        LocationInfo info = new LocationInfo();
        info.setThumbnailUrl(asString(node, "thumbnail_url"));
        info.setThumbnailInfo(info(node.get("thumbnail_info"), this::thumbnailInfo));
        return info;
    }

    protected VideoInfo videoInfo(JsonNode node) {
        VideoInfo info = fileInfo(new VideoInfo(), node);
        info.setHeight(asLong(node, "h"));
        info.setWidth(asLong(node, "w"));
        info.setDuration(asLong(node, "duration"));
        info.setSize(asLong(node, "size"));
        info.setMimetype(asString(node, "mimetype"));
        return info;
    }

    protected <T> T info(JsonNode node, Function<JsonNode, T> map) {
        return node != null && !JsonNodeType.NULL.equals(node.getNodeType()) ? map.apply(node) : null;
    }
}
