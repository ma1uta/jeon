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

package io.github.ma1uta.matrix.server.model.federation;

/**
 * These events are broadcast from one homeserver to any others that have joined the same room (identified by Room ID).
 * They are persisted in long-term storage and record the history of messages and state for a room.
 * <br>
 * Like email, it is the responsibility of the originating server of a PDU to deliver that event to its recipient servers.
 * However PDUs are signed using the originating server's private key so that it is possible to deliver them through third-party servers.
 */
public interface PersistedDataUnit {
}
