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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Matrix id (MXID) util class.
 */
public class Id {

    private static final Logger LOGGER = LoggerFactory.getLogger(Id.class);

    /**
     * MXID pattern.
     */
    public static final Pattern PATTERN = Pattern.compile("^[@!$#+]([^:]+):(.+)$");

    /**
     * User localpart pattern.
     */
    public static final Pattern USER = Pattern.compile("[a-z0-9._=\\-/]+");

    /**
     * Domain pattern.
     */
    public static final Pattern DOMAIN = Pattern
        .compile("(\\d{3}.\\d{3}.\\d{3}.\\d{3}|\\[[0-9\\p{Alpha}:]{0,39}]|[0-9\\-.\\p{Alpha}]{1,255})(:[\\d]+)?");

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

    protected Id() {
        // singleton.
    }

    /**
     * Check the given string is MXID.
     *
     * @param id identifier.
     * @return {@code true} if is MXID, else {@code false}.
     */
    public static boolean isId(String id) {
        try {
            validate(id);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private static Matcher validate(String id) {
        if (id == null || id.trim().isEmpty()) {
            String message = "Empty id";
            LOGGER.error(message);
            throw new IllegalArgumentException(message);
        }

        Matcher matcher = PATTERN.matcher(id.trim());
        if (!matcher.matches()) {
            String message = String.format("Invalid id: '%s'", id);
            LOGGER.error(message);
            throw new IllegalArgumentException(message);
        }

        String localpart = matcher.group(1);
        String domain = matcher.group(2);
        LOGGER.trace("localpart: '%s', domain: '%s'", localpart, domain);

        Matcher domainMatcher = DOMAIN.matcher(domain);
        if (!domainMatcher.matches()) {
            String message = String.format("Invalid domain part: '%s'", domain);
            LOGGER.error(message);
            throw new IllegalArgumentException(message);
        }
        return matcher;
    }

    /**
     * Return sigil character of the MXID.
     *
     * @param id MXID.
     * @return sigil character.
     */
    public static char sigil(String id) {
        validate(id);
        return id.charAt(0);
    }

    /**
     * Return localpart of the MXID.
     *
     * @param id MXID.
     * @return localpart.
     */
    public static String localpart(String id) {
        return validate(id).group(1);
    }

    /**
     * Return domain part of the MXID.
     *
     * @param id MXID.
     * @return domain.
     */
    public static String domain(String id) {
        return validate(id).group(2);
    }

    /**
     * Check is this MXID is a user id.
     *
     * @param id MXID.
     * @return {@code true} if user id, else {@code false}.
     */
    public static boolean isUserId(String id) {
        try {
            Matcher matcher = validate(id);
            return id.charAt(0) == Sigil.USER && USER.matcher(matcher.group(1)).matches();
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * Check is this MXID is a event id.
     *
     * @param id MXID.
     * @return {@code true} if event id, else {@code false}.
     */
    public static boolean isEventId(String id) {
        try {
            return sigil(id) == Sigil.EVENT;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * Check is this MXID is a room id.
     *
     * @param id MXID.
     * @return {@code true} if room id, else {@code false}.
     */
    public static boolean isRoomId(String id) {
        try {
            return sigil(id) == Sigil.ROOM;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * Check is this MXID is a room alias id.
     *
     * @param id MXID.
     * @return {@code true} if room alias id, else {@code false}.
     */
    public static boolean isAliasId(String id) {
        try {
            return sigil(id) == Sigil.ALIAS;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * Check is this MXID is a group (community) id.
     *
     * @param id MXID.
     * @return {@code true} if group id, else {@code false}.
     */
    public static boolean isGroupId(String id) {
        try {
            return sigil(id) == Sigil.GROUP;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
