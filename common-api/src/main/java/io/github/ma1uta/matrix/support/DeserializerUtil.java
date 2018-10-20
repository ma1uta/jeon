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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Some useful methods for deserializers.
 */
public class DeserializerUtil {

    private DeserializerUtil() {
        // singleton
    }

    /**
     * Extract the string value from the map.
     *
     * @param props property map.
     * @param name  property name.
     * @return the string value or null.
     */
    public static String toString(Map props, String name) {
        if (props == null) {
            return null;
        }
        return (String) props.get(name);
    }

    /**
     * Extract the long value from the map.
     *
     * @param props property map.
     * @param name  property name.
     * @return the long value or null.
     */
    public static Long toLong(Map props, String name) {
        if (props == null) {
            return null;
        }
        return parseLong(props.get(name));
    }

    /**
     * Parse long value.
     *
     * @param value value.
     * @return the long value or null.
     */
    public static Long parseLong(Object value) {
        if (value == null) {
            return null;
        } else if (value instanceof Long) {
            return (Long) value;
        } else if (value instanceof Number) {
            return ((Number) value).longValue();
        } else if (value instanceof String) {
            return Long.parseLong((String) value);
        } else {
            throw new RuntimeException("Expecting the long value but given the " + value.getClass().getName());
        }
    }

    /**
     * Extract the byte value from the map.
     *
     * @param props property map.
     * @param name  property name.
     * @return the long value or null.
     */
    public static Byte toByte(Map props, String name) {
        if (props == null) {
            return null;
        }
        return parseByte(props.get(name));
    }

    /**
     * Extract the byte value from the map.
     *
     * @param props        property map.
     * @param name         property name.
     * @param defaultValue default value.
     * @return the long value or null.
     */
    public static Byte toByte(Map props, String name, Byte defaultValue) {
        if (props == null) {
            return null;
        }
        Byte value = parseByte(props.get(name));
        return value != null ? value : defaultValue;
    }

    /**
     * Parse byte value.
     *
     * @param value value.
     * @return the byte value or null.
     */
    public static Byte parseByte(Object value) {
        if (value == null) {
            return null;
        } else if (value instanceof Byte) {
            return (Byte) value;
        } else if (value instanceof Number) {
            return ((Number) value).byteValue();
        } else if (value instanceof String) {
            return Byte.parseByte((String) value);
        } else {
            throw new RuntimeException("Expecting the byte value but given the " + value.getClass().getName());
        }
    }

    /**
     * Extract the object from the map.
     *
     * @param props       property map.
     * @param name        property name.
     * @param constructor object constructor.
     * @param <T>         object class.
     * @return the object or null.
     */
    public static <T> T toObject(Map props, String name, Function<Map, T> constructor) {
        if (props == null) {
            return null;
        }
        Object value = props.get(name);
        if (value == null) {
            return null;
        }
        if (!(value instanceof Map)) {
            throw new RuntimeException("Expecting the json object but given the " + value.getClass().getName());
        }
        return constructor.apply((Map) value);
    }

    /**
     * Extract the map.
     *
     * @param props       property map.
     * @param name        property name.
     * @param keyMapper   key mapper.
     * @param valueMapper value mapper.
     * @param <K>         key class.
     * @param <V>         value class.
     * @return the map or null.
     */
    @SuppressWarnings("unchecked")
    public static <K, V> Map<K, V> toMap(Map props, String name, Function<Map.Entry, K> keyMapper, Function<Map.Entry, V> valueMapper) {
        if (props == null) {
            return null;
        }
        Object value = props.get(name);
        if (value == null) {
            return null;
        }
        if (!(value instanceof Map)) {
            throw new RuntimeException("expecting the map but given the " + value.getClass().getName());
        }
        return (Map<K, V>) ((Map) value).entrySet().parallelStream().collect(Collectors.toMap(keyMapper, valueMapper));
    }

    /**
     * Extract the object list.
     *
     * @param props       property map.
     * @param name        property name.
     * @param constructor list item constructor.
     * @param <I>         list item source.
     * @param <T>         list item class.
     * @return the object list or null.
     */
    @SuppressWarnings("unchecked")
    public static <I, T> List<T> toList(Map props, String name, Function<I, T> constructor) {
        if (props == null) {
            return null;
        }
        Object value = props.get(name);
        if (value == null) {
            return null;
        }
        if (!(value instanceof Iterable)) {
            throw new RuntimeException("Expecting the list but given the " + value.getClass().getName());
        }
        Iterator iterator = ((Iterable) value).iterator();
        List<T> list = new ArrayList<>();
        while (iterator.hasNext()) {
            list.add(constructor.apply((I) iterator.next()));
        }
        return list;
    }

    /**
     * Extract the boolean.
     *
     * @param props property map.
     * @param name  property name.
     * @return Boolean object or null.
     */
    public static Boolean toBoolean(Map props, String name) {
        if (props == null) {
            return null;
        }
        Object value = props.get(name);
        if (value == null) {
            return null;
        } else if (value instanceof Boolean) {
            return (Boolean) value;
        } else if (value instanceof String) {
            return Boolean.parseBoolean((String) value);
        } else if (value instanceof Number) {
            return ((Number) value).intValue() > 0;
        } else {
            throw new RuntimeException("Expecting the boolean value but given the " + value.getClass().getName());
        }
    }
}
