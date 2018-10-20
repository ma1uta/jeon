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

import io.github.ma1uta.matrix.event.content.RoomEncryptedContent;

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
                    } else {
                        throw new RuntimeException("Cannot find the RoomEncrypted deserializer. Please put one to the classpath.");
                    }
                }
            }
        }
        return instance;
    }

    /**
     * Deserialize the room encrypted content.
     *
     * @param props property map.
     * @return the event instance.
     */
    public abstract RoomEncryptedContent deserialize(Map props);
}
