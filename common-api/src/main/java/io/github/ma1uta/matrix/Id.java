/*
 * Copyright Anatoliy Sablin tolya@sablin.xyz
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

import java.util.Optional;

/**
 * Matrix id (MXID) util class.
 */
public class Id {

    protected Id() {
        //singleton
    }

    /**
     * Sigils.
     */
    public static class Sigil {

        protected Sigil() {
            //singleton
        }

        /**
         * User sigil.
         */
        public static final char USER = '@';

        /**
         * Event sigil.
         */
        public static final char EVENT = '$';

        /**
         * Room sigil.
         */
        public static final char ROOM = '!';

        /**
         * Alias sigil.
         */
        public static final char ALIAS = '#';

        /**
         * Group sigil.
         */
        public static final char GROUP = '+';
    }

    /**
     * Get localpart of the specified MXID if exists.
     *
     * @param id MXID.
     * @return localpart.
     */
    public static Optional<String> localPart(String id) {
        int colon = id.indexOf(':');
        return colon == -1 ? Optional.empty() : Optional.of(id.substring(1, colon));
    }

    /**
     * Get server name of the specified MXID if exists.
     *
     * @param id MXID.
     * @return server name.
     */
    public static Optional<String> serverName(String id) {
        int colon = id.indexOf(':');
        return colon == -1 ? Optional.empty() : Optional.of(id.substring(colon + 1));
    }
}
