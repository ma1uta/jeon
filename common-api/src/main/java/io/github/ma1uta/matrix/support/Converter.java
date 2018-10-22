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

import io.github.ma1uta.matrix.event.Event;
import io.github.ma1uta.matrix.event.content.RoomEncryptedContent;
import io.github.ma1uta.matrix.event.content.RoomMessageContent;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.Iterator;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * Converter (combined serializer and deserializer).
 */
public abstract class Converter {

    private static Converter instance;

    /**
     * Provides the initialized converter instance.
     *
     * @return converter.
     */
    public static Converter getInstance() {
        if (instance == null) {
            synchronized (Converter.class) {
                if (instance == null) {
                    ServiceLoader<Converter> serviceLoader = ServiceLoader.load(Converter.class);
                    Iterator<Converter> iterator = serviceLoader.iterator();
                    if (iterator.hasNext()) {
                        instance = iterator.next();
                        instance.init();
                    } else {
                        throw new RuntimeException("Failed find the object converter. Please put one to the classpath.");
                    }
                }
            }
        }
        return instance;
    }

    /**
     * Init the converter.
     */
    protected void init() {
    }

    /**
     * Deserialize a property map to the object.
     *
     * @param props  property map.
     * @param target class of the target object.
     * @param <T>    class qualifier of the target object.
     * @return the target object.
     * @throws IOException when deserialize was failed.
     */
    @SuppressWarnings("unchecked")
    public <T> T deserialize(Map props, Class<T> target) throws IOException {
        if (Event.class.isAssignableFrom(target)) {
            return (T) EventDeserializer.getInstance().deserialize(props);
        } else if (RoomMessageContent.class.isAssignableFrom(target)) {
            return (T) RoomMessageDeserializer.getInstance().deserialize(props);
        } else if (RoomEncryptedContent.class.isAssignableFrom(target)) {
            return (T) RoomEncryptedDeserializer.getInstance().deserialize(props);
        } else {
            Constructor<T> constructor;
            try {
                constructor = target.getConstructor(Map.class);
            } catch (NoSuchMethodException e) {
                throw new IOException("Cannot find the constructor via Map argument", e);
            }
            try {
                return AccessController.doPrivileged((PrivilegedExceptionAction<T>) () -> constructor.newInstance(props));
            } catch (PrivilegedActionException e) {
                throw new IOException("Cannot invoke the constructor", e);
            }
        }
    }

    /**
     * Deserialize a byte array to the object.
     *
     * @param source the byte array to deserialize.
     * @param target the class of the target object.
     * @param <T>    the qualifier of the target object.
     * @return the target object.
     * @throws IOException when deserialization was failed.
     */
    public <T> T deserialize(byte[] source, Class<T> target) throws IOException {
        return deserialize(bytesToMap(source), target);
    }

    /**
     * Deserialize a stream to the object.
     *
     * @param source the stream to deserialize.
     * @param target the class of the target object.
     * @param <T>    the qualifier of the target object.
     * @return the target object.
     * @throws IOException when deserialization was failed.
     */
    public <T> T deserialize(InputStream source, Class<T> target) throws IOException {
        return deserialize(streamToMap(source), target);
    }

    /**
     * Serialize an object to the byte array.
     *
     * @param object the source object.
     * @return the byte array of the serialized object.
     * @throws IOException when serialization was failed.
     */
    public abstract byte[] serialize(Object object) throws IOException;

    /**
     * Serialize an object to the stream.
     *
     * @param object the source object.
     * @param target the stream with the serialized object.
     * @throws IOException when serialization was failed.
     */
    public abstract void serialize(Object object, OutputStream target) throws IOException;

    /**
     * Convert a byte array to the property map.
     *
     * @param source the source byte array.
     * @return the property map.
     * @throws IOException when converting was failed.
     */
    protected abstract Map bytesToMap(byte[] source) throws IOException;

    /**
     * Convert a stream to the property map.
     *
     * @param source the source stream.
     * @return the property map.
     * @throws IOException when converting was failed.
     */
    protected abstract Map streamToMap(InputStream source) throws IOException;
}
