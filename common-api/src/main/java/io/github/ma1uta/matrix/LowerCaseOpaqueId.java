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
 * Id with opaque localpart in lowercase.
 */
public abstract class LowerCaseOpaqueId extends Id {

    protected LowerCaseOpaqueId(String localpart, String serverName) {
        super(localpart, serverName);
    }

    @Override
    protected String localpart(String localpart) {
        String lowerCase = localpart.toLowerCase();
        for (int i = 0; i < lowerCase.length(); i++) {
            char ch = localpart.charAt(i);
            if (!((ch >= 'a' && ch <= 'z') || (ch >= '0' && ch <= '9') || ch == '.' || ch == '_' || ch == '-' || ch == '=')) {
                addError(new Id.IdParseException("Char " + ch + " should be from the set: [a-z0-9\\._-=]"));
            }
        }
        return lowerCase;
    }
}
