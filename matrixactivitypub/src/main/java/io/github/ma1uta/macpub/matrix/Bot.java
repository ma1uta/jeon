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

package io.github.ma1uta.macpub.matrix;

import io.dropwizard.hibernate.UnitOfWork;
import io.github.ma1uta.matrix.Event;
import io.github.ma1uta.matrix.client.model.account.RegisterRequest;
import io.github.ma1uta.matrix.client.model.filter.FilterData;
import io.github.ma1uta.matrix.client.model.filter.RoomEventFilter;
import io.github.ma1uta.matrix.client.model.filter.RoomFilter;
import io.github.ma1uta.matrix.client.model.room.RoomId;
import io.github.ma1uta.matrix.client.model.sync.JoinedRoom;
import io.github.ma1uta.matrix.client.model.sync.LeftRoom;
import io.github.ma1uta.matrix.client.model.sync.Rooms;
import io.github.ma1uta.matrix.client.model.sync.SyncResponse;
import io.github.ma1uta.matrix.sdk.MatrixClient;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Map;
import javax.ws.rs.client.Client;

/**
 * Matrix bot client.
 */
public class Bot implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(Bot.class);

    private static final long TIMEOUT = 10 * 1000;

    private final MatrixClient matrixClient;

    private BotConfig data;

    private final BotDao dao;

    public Bot(Client client, String homeserverUrl, String domain, String asToken, BotConfig data, BotDao dao) {
        this.matrixClient = new MatrixClient(homeserverUrl, domain, client, true, true, data.getTxnId());
        this.matrixClient.setAccessToken(asToken);
        this.matrixClient.setUserId(data.getUserId());
        this.matrixClient.setDeviceId(data.getDeviceId());
        this.data = data;
        this.dao = dao;
    }

    public BotConfig getData() {
        return data;
    }

    public void setData(BotConfig data) {
        this.data = data;
    }

    public BotDao getDao() {
        return dao;
    }

    /**
     * Register a new bot.
     * <p/>
     * After registration setup a filter to receive only message events.
     */
    @UnitOfWork
    public void register() {
        BotConfig data = getData();
        if (data.getFilterId() == null) {
            RegisterRequest registerRequest = new RegisterRequest();
            registerRequest.setUsername(data.getUserId());
            registerRequest.setInitialDeviceDisplayName(data.getDisplayName());
            registerRequest.setDeviceId(data.getDeviceId());
            matrixClient.register(registerRequest);

            RoomEventFilter roomEventFilter = new RoomEventFilter();
            roomEventFilter.setTypes(Collections.singletonList(Event.EventType.ROOM_MESSAGE));
            RoomFilter roomFilter = new RoomFilter();
            roomFilter.setTimeline(roomEventFilter);
            FilterData filter = new FilterData();
            filter.setRoom(roomFilter);
            data.setFilterId(matrixClient.filter().uploadFilter(filter).getFilterId());

            storeData();
        }
    }

    @Override
    public void run() {
        try {
            register();

            SyncResponse sync = matrixClient.sync(getData().getFilterId(), getData().getNextBatch(), false, null, null);
            while (true) {
                Rooms rooms = sync.getRooms();

                if (StringUtils.isBlank(getData().getRoomId())) {
                    if (rooms.getInvite().size() > 1) {
                        LOGGER.error("Too many rooms to join");
                        delete();
                        break;
                    } else {
                        rooms.getInvite().forEach((roomId, room) -> joinRoom(roomId));
                    }
                } else {
                    JoinedRoom joinedRoom = rooms.getJoin().get(getData().getRoomId());

                    if (joinedRoom != null) {
                        mainLoop(joinedRoom, sync.getNextBatch());
                    }

                    for (Map.Entry<String, LeftRoom> roomEntry : rooms.getLeave().entrySet()) {
                        matrixClient.room().leaveRoom(roomEntry.getKey());
                    }

                    if (matrixClient.room().joinedRooms().isEmpty()) {
                        delete();
                        break;
                    }
                }

                if (Thread.currentThread().isInterrupted()) {
                    break;
                }

                sync = matrixClient.sync(getData().getFilterId(), sync.getNextBatch(), false, null, TIMEOUT);
            }
        } catch (Throwable e) {
            LOGGER.error("Exception:", e);
            throw e;
        }
    }

    /**
     * Delete bot.
     */
    @UnitOfWork
    public void delete() {
        matrixClient.deactivate();
        getDao().delete(getData());
    }

    /**
     * Join to room.
     *
     * @param roomId room id.
     */
    @UnitOfWork
    public void joinRoom(String roomId) {
        RoomId response = matrixClient.room().joinRoomByIdOrAlias(roomId);
        if (StringUtils.isAllBlank(response.getErrcode(), response.getError())) {
            getData().setRoomId(roomId);
            storeData();
        } else {
            throw new RuntimeException(
                String.format("Failed join to room, errcode: ''%s'', error: ''%s''", response.getErrcode(), response.getError()));
        }
    }

    /**
     * Process commands.
     *
     * @param joinedRoom room's data.
     * @param nextBatch  next batch.
     */
    @UnitOfWork
    public void mainLoop(JoinedRoom joinedRoom, String nextBatch) {
        String lastEvent = null;
        long lastOriginTs = 0;
        for (Event event : joinedRoom.getTimeline().getEvents()) {
            if (Event.EventType.ROOM_MESSAGE.equals(event.getType()) && !matrixClient.getUserId().equals(event.getSender())) {
                Map<String, Object> content = event.getContent();
                // process only m.text events not n.notice or other
                if (Event.MessageType.TEXT.equals(content.get("msgtype"))) {
                    String action = (String) content.get("body");
                    try {
                        process(action);
                    } catch (Exception e) {
                        LOGGER.error(String.format("Cannot perform action ''%s''", action));
                    }
                }
            }

            if (event.getOriginServerTs() != null && event.getOriginServerTs() > lastOriginTs) {
                lastOriginTs = event.getOriginServerTs();
                lastEvent = event.getEventId();
            }
        }
        if (lastEvent != null) {
            matrixClient.sendReceipt(getData().getRoomId(), lastEvent);
        }
        getData().setNextBatch(nextBatch);
        storeData();
    }

    private void process(String content) {
        String[] arguments = content.split("\\s");
        if (arguments[0].startsWith("!")) {
            switch (arguments[0]) {
                case "!new_name":
                    if (arguments.length < 2) {
                        error("usage: !new_name <new name>");
                    } else {
                        newName(arguments[1]);
                    }
                    break;
                case "!ping":
                    pong();
                    break;
                default:
                    error("unknown command");
            }
        }
    }

    private void storeData() {
        getData().setTxnId(matrixClient.getTxn().get());
        setData(getDao().save(getData()));
    }

    private void error(String message) {
        matrixClient.sendNotice(getData().getRoomId(), "Error: " + message);
    }

    private void newName(String newName) {
        matrixClient.setDisplayName(newName);
        getData().setDisplayName(newName);
        storeData();
    }

    private void pong() {
        matrixClient.sendNotice(getData().getRoomId(), "pong");
    }
}
