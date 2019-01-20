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

/**
 * Unknown MXID.
 */
public class UnknownId extends Id {

    private char sigil;

    public UnknownId(char sigil, String localpart, String serverName) {
        super(localpart, serverName);
    }

    @Override
    public char getSigil() {
        return sigil;
    }

    @Override
    protected String localpart(String localpart) {
        return localpart;
    }

    @Override
    protected String hostname(String hostname) {
        return hostname;
    }
}
