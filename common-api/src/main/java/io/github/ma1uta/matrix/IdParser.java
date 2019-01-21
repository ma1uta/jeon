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

/**
 * MXID parser.
 */
public abstract class IdParser {

    private static IdParser instance;

    private static final String DEFAULT_PROVIDER = "io.github.ma1uta.matrix.impl.MatrixIdParser";

    /**
     * Provides the parser implementation.
     *
     * @return The parser implementation.
     */
    public static IdParser getInstance() {
        if (instance == null) {
            synchronized (DEFAULT_PROVIDER) {
                if (instance == null) {
                    ServiceLoader<IdParser> serviceLoader = ServiceLoader.load(IdParser.class);

                    Iterator<IdParser> iterator = serviceLoader.iterator();

                    if (iterator.hasNext()) {
                        instance = iterator.next();
                    } else {
                        try {
                            instance = AccessController
                                .doPrivileged(
                                    (PrivilegedExceptionAction<IdParser>) () -> (IdParser) Class.forName(DEFAULT_PROVIDER)
                                        .getDeclaredConstructor().newInstance());
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

    protected IdParser() {
        // singleton.
    }

    /**
     * Parse input string and return an id instance.
     *
     * @param mxid string representation.
     * @return mxid instance.
     */
    public abstract Id parse(String mxid);
}
