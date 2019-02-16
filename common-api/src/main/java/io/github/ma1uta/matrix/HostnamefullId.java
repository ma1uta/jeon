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
 * Matrix id (MXID) util class.
 */
public abstract class HostnamefullId extends Id {

    private String localpart;

    private String hostname;

    private int port;

    private String fullId;

    protected HostnamefullId(String localpart, String hostname, int port) {
        this.localpart = localpart;
        this.hostname = hostname;
        this.port = port;
    }

    public String getLocalpart() {
        return localpart;
    }

    public String getHostname() {
        return hostname;
    }

    public int getPort() {
        return port;
    }

    @Override
    public String toString() {
        if (fullId == null) {
            StringBuilder builder = new StringBuilder();
            builder.append(getSigil()).append(getLocalpart()).append(":").append(getHostname());
            if (getPort() != -1) {
                builder.append(":").append(getPort());
            }
            this.fullId = builder.toString();
        }
        return fullId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HostnamefullId)) {
            return false;
        }
        HostnamefullId id = (HostnamefullId) o;
        return getSigil() == id.getSigil()
            && port == id.port
            && localpart.equals(id.localpart)
            && hostname.equals(id.hostname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSigil(), localpart, hostname, port);
    }
}
