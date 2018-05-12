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

package io.github.ma1uta.matrixactivitypub;

import io.github.ma1uta.matrix.Event;
import io.github.ma1uta.matrix.client.model.filter.FilterData;
import io.github.ma1uta.matrix.client.model.filter.RoomEventFilter;
import io.github.ma1uta.matrix.client.model.filter.RoomFilter;
import io.github.ma1uta.matrix.client.model.sync.JoinedRoom;
import io.github.ma1uta.matrix.client.model.sync.Rooms;
import io.github.ma1uta.matrix.client.model.sync.SyncResponse;
import io.github.ma1uta.matrix.sdk.MatrixClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.ws.rs.client.Client;

/**
 * Matrix bot client.
 */
public class MatrixBot implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(MatrixBot.class);

    private static final long TIMEOUT = 10 * 1000;

    private final MatrixClient matrixClient;

    private final String username;

    private final String password;

    private final String displayName;

    private AtomicBoolean stop = new AtomicBoolean(false);

    public MatrixBot(Client client, String homeserverUrl, String username, String password, String displayName) {
        this.matrixClient = new MatrixClient(homeserverUrl, client);
        this.username = username;
        this.password = password;
        this.displayName = displayName;
    }

    /**
     * Logout.
     */
    public void stop() {
        stop.set(true);
        matrixClient.logout();
    }

    @Override
    public void run() {
        matrixClient.login(username, password);

        matrixClient.setDisplayName(displayName);

        RoomEventFilter roomEventFilter = new RoomEventFilter();
        roomEventFilter.setTypes(Collections.singletonList(Event.EventType.ROOM_MESSAGE));
        RoomFilter roomFilter = new RoomFilter();
        roomFilter.setTimeline(roomEventFilter);
        FilterData filter = new FilterData();
        filter.setRoom(roomFilter);
        String filterId = matrixClient.uploadFilter(filter).getFilterId();

        SyncResponse sync = matrixClient.sync(filterId, null, false, null, null);
        while (true) {
            Rooms rooms = sync.getRooms();

            rooms.getInvite().forEach((key, value) -> matrixClient.joinRoomByIdOrAlias(key));

            rooms.getLeave().forEach((key, value) -> matrixClient.leaveRoom(key));

            for (Map.Entry<String, JoinedRoom> roomEntry : rooms.getJoin().entrySet()) {
                String lastEvent = null;
                long lastOriginTs = 0;
                for (Event event : roomEntry.getValue().getTimeline().getEvents()) {
                    if (Event.EventType.ROOM_MESSAGE.equals(event.getType()) && !matrixClient.getUserId().equals(event.getSender())) {
                        Map<String, Object> content = event.getContent();
                        if (Event.MessageType.TEXT.equals(content.get("msgtype"))) {
                            process(matrixClient, roomEntry.getKey(), (String) content.get("body"));
                        }
                    }

                    if (event.getOriginServerTs() != null && event.getOriginServerTs() > lastOriginTs) {
                        lastOriginTs = event.getOriginServerTs();
                        lastEvent = event.getEventId();
                    }
                }
                if (lastEvent != null) {
                    matrixClient.sendReceipt(roomEntry.getKey(), lastEvent);
                }
            }

            if (stop.get()) {
                break;
            }
            sync = matrixClient.sync(filterId, sync.getNextBatch(), false, null, TIMEOUT);
        }
    }

    private void process(MatrixClient client, String roomId, String content) {
        String[] arguments = content.split("\\s");
        if (arguments[0].startsWith("!")) {
            switch (arguments[0]) {
                case "!leave":
                    leave(client, roomId);
                    break;
                case "!ping":
                    pong(client, roomId);
                    break;
                default:
                    client.sendNotice(roomId, "unknown command");
            }
        }
    }

    private void leave(MatrixClient client, String roomId) {
        client.leaveRoom(roomId);
    }

    private void pong(MatrixClient client, String roomId) {
        client.sendNotice(roomId, "pong");
    }
}
