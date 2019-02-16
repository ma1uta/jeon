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

import java.util.Objects;

/**
 * MXID with the one part.
 */
public class HostnamelessId extends Id {

    private String id;

    protected HostnamelessId(String id) {
        this.id = id;
    }

    @Override
    public char getSigil() {
        return id.charAt(0);
    }

    @Override
    public String toString() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HostnamelessId)) {
            return false;
        }
        HostnamelessId hostnamelessId = (HostnamelessId) o;
        return Objects.equals(id, hostnamelessId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
