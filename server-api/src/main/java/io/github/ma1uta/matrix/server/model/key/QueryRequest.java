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

package io.github.ma1uta.matrix.server.model.key;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * JSON body request for bulk query api.
 */
public class QueryRequest {

    /**
     * Server keys.
     * <p/>
     * Maps: server_name -> key_id -> {@link KeyTs}.
     */
    @JsonProperty("server_keys")
    private Map<String, Map<String, KeyTs>> serverKeys;

    public Map<String, Map<String, KeyTs>> getServerKeys() {
        return serverKeys;
    }

    public void setServerKeys(
        Map<String, Map<String, KeyTs>> serverKeys) {
        this.serverKeys = serverKeys;
    }
}