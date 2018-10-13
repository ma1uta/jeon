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

package io.github.ma1uta.matrix;

import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.Iterator;
import java.util.ServiceLoader;
import java.util.regex.Matcher;

/**
 * Matrix id (MXID) util class.
 */
public abstract class Id {

    /**
     * Sigil chars.
     */
    public static class Sigil {

        protected Sigil() {
            //singleton
        }

        /**
         * Event.
         */
        public static final char EVENT = '$';
        /**
         * User.
         */
        public static final char USER = '@';
        /**
         * Room.
         */
        public static final char ROOM = '!';
        /**
         * Room alias.
         */
        public static final char ALIAS = '#';
        /**
         * Group (community).
         */
        public static final char GROUP = '+';
    }

    private static Id instance;

    private static final String DEFAULT_PROVIDER = "io.github.ma1uta.matrix.MatrixId";

    /**
     * Provides the ID implementation.
     *
     * @return The ID implementation.
     */
    public static Id getInstance() {
        if (instance == null) {
            synchronized (DEFAULT_PROVIDER) {
                if (instance == null) {
                    ServiceLoader<Id> serviceLoader = ServiceLoader.load(Id.class);

                    Iterator<Id> iterator = serviceLoader.iterator();

                    if (iterator.hasNext()) {
                        instance = iterator.next();
                    } else {
                        try {
                            instance = AccessController
                                .doPrivileged((PrivilegedExceptionAction<Id>) () -> (Id) Class.forName(DEFAULT_PROVIDER).newInstance());
                        } catch (PrivilegedActionException e) {
                            throw new RuntimeException(
                                "Failed load the default implementation. Please check that the common-sdk jar available", e);
                        }
                    }
                }
            }
        }

        return instance;
    }

    protected Id() {
        // singleton.
    }

    /**
     * Check the given string is MXID.
     *
     * @param id identifier.
     * @return {@code true} if is MXID, else {@code false}.
     */
    public boolean isId(String id) {
        try {
            validate(id);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    protected abstract Matcher validate(String id);

    /**
     * Return sigil character of the MXID.
     *
     * @param id MXID.
     * @return sigil character.
     */
    public char sigil(String id) {
        validate(id);
        return id.charAt(0);
    }

    /**
     * Return localpart of the MXID.
     *
     * @param id MXID.
     * @return localpart.
     */
    public String localpart(String id) {
        return validate(id).group(1);
    }

    /**
     * Return domain part of the MXID.
     *
     * @param id MXID.
     * @return domain.
     */
    public String domain(String id) {
        return validate(id).group(2);
    }

    /**
     * Check is this MXID is a user id.
     *
     * @param id MXID.
     * @return {@code true} if user id, else {@code false}.
     */
    public boolean isUserId(String id) {
        try {
            Matcher matcher = validate(id);
            return id.charAt(0) == Sigil.USER && userMatchers(matcher);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    protected abstract boolean userMatchers(Matcher matcher);

    /**
     * Check is this MXID is a event id.
     *
     * @param id MXID.
     * @return {@code true} if event id, else {@code false}.
     */
    public boolean isEventId(String id) {
        try {
            Matcher matcher = validate(id);
            return id.charAt(0) == Sigil.EVENT && eventMatchers(matcher);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    protected abstract boolean eventMatchers(Matcher matcher);

    /**
     * Check is this MXID is a room id.
     *
     * @param id MXID.
     * @return {@code true} if room id, else {@code false}.
     */
    public boolean isRoomId(String id) {
        try {
            Matcher matcher = validate(id);
            return id.charAt(0) == Sigil.ROOM && roomMatchers(matcher);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    protected abstract boolean roomMatchers(Matcher matcher);

    /**
     * Check is this MXID is a room alias id.
     *
     * @param id MXID.
     * @return {@code true} if room alias id, else {@code false}.
     */
    public boolean isAliasId(String id) {
        try {
            Matcher matcher = validate(id);
            return id.charAt(0) == Sigil.ALIAS && aliasMatchers(matcher);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    protected abstract boolean aliasMatchers(Matcher matcher);


    /**
     * Check is this MXID is a group (community) id.
     *
     * @param id MXID.
     * @return {@code true} if group id, else {@code false}.
     */
    public boolean isGroupId(String id) {
        try {
            Matcher matcher = validate(id);
            return id.charAt(0) == Sigil.GROUP && groupMatchers(matcher);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    protected abstract boolean groupMatchers(Matcher matcher);
}
