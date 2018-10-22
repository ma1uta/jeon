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

import static io.github.ma1uta.matrix.event.Event.Encryption.MEGOLM;
import static io.github.ma1uta.matrix.event.Event.Encryption.OLM;

import io.github.ma1uta.matrix.event.content.RoomEncryptedContent;
import io.github.ma1uta.matrix.event.encrypted.MegolmEncryptedContent;
import io.github.ma1uta.matrix.event.encrypted.OlmEncryptedContent;
import io.github.ma1uta.matrix.event.encrypted.RawEncryptedContent;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * The Room encrypted deserializer.
 */
public abstract class RoomEncryptedDeserializer {

    private static RoomEncryptedDeserializer instance;

    /**
     * Get the deserializer instance.
     *
     * @return the deserializer instance.
     */
    public static RoomEncryptedDeserializer getInstance() {
        if (instance == null) {
            synchronized (RoomEncryptedDeserializer.class) {
                if (instance == null) {
                    ServiceLoader<RoomEncryptedDeserializer> serviceLoader = ServiceLoader.load(RoomEncryptedDeserializer.class);
                    Iterator<RoomEncryptedDeserializer> iterator = serviceLoader.iterator();
                    if (iterator.hasNext()) {
                        instance = iterator.next();
                        instance.init();
                    } else {
                        throw new RuntimeException("Cannot find the RoomEncrypted deserializer. Please put one to the classpath.");
                    }
                }
            }
        }
        return instance;
    }

    protected void init() {
    }

    /**
     * Deserialize a room encrypted event.
     *
     * @param props the room encrypted event properties.
     * @return the room encrypted event instance.
     */
    public RoomEncryptedContent deserialize(Map props) {
        if (props == null) {
            return null;
        }

        String algorithm = (String) props.get("algorithm");

        if (algorithm == null) {
            throw new RuntimeException("Missing the algorithm.");
        }

        switch (algorithm) {
            case OLM:
                return new OlmEncryptedContent(props);
            case MEGOLM:
                return new MegolmEncryptedContent(props);
            default:
                return parse(props);
        }
    }

    /**
     * Deserialize a room encrypted event.
     *
     * @param bytes the room encrypted event.
     * @return the room encrypted event instance.
     * @throws IOException when conversion failed.
     */
    public RoomEncryptedContent deserialize(byte[] bytes) throws IOException {
        return deserialize(bytesToMap(bytes));
    }

    /**
     * Deserialize a room encrypted event.
     *
     * @param inputStream the room encrypted event.
     * @return the room encrypted event instance.
     * @throws IOException when conversion failed.
     */
    public RoomEncryptedContent deserialize(InputStream inputStream) throws IOException {
        return deserialize(streamToMap(inputStream));
    }

    protected RoomEncryptedContent parse(Map props) {
        return new RawEncryptedContent(props);
    }

    /**
     * Convert a byte array to the property map.
     *
     * @param bytes byte array.
     * @return the property map.
     * @throws IOException when conversion failed.
     */
    protected abstract Map bytesToMap(byte[] bytes) throws IOException;

    /**
     * Convert a input stream to the property map.
     *
     * @param inputStream the input stream.
     * @return the property map.
     * @throws IOException when conversion failed.
     */
    protected abstract Map streamToMap(InputStream inputStream) throws IOException;
}
