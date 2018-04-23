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

package io.github.ma1uta.matrix.client.model.admin;

/**
 * Connection info.
 */
public class ConnectionInfo {

    /**
     * Most recently seen IP address of the session.
     */
    private String ip;

    /**
     * Unix timestamp that the session was last active.
     */
    private Long lastSeen;

    /**
     * User agent string last seen in the session.
     */
    private String userAgent;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Long getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(Long lastSeen) {
        this.lastSeen = lastSeen;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }
}
