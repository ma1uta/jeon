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

import io.github.ma1uta.matrix.Event;
import io.github.ma1uta.matrix.Id;
import io.github.ma1uta.matrix.client.model.account.RegisterRequest;
import io.github.ma1uta.matrix.client.model.filter.FilterData;
import io.github.ma1uta.matrix.client.model.filter.RoomEventFilter;
import io.github.ma1uta.matrix.client.model.filter.RoomFilter;
import io.github.ma1uta.matrix.client.model.room.RoomId;
import io.github.ma1uta.matrix.client.model.sync.InvitedRoom;
import io.github.ma1uta.matrix.client.model.sync.JoinedRoom;
import io.github.ma1uta.matrix.client.model.sync.LeftRoom;
import io.github.ma1uta.matrix.client.model.sync.Rooms;
import io.github.ma1uta.matrix.client.model.sync.SyncResponse;
import io.github.ma1uta.matrix.sdk.MatrixClient;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import javax.ws.rs.client.Client;

/**
 * Matrix bot client.
 */
public class Bot implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(Bot.class);

    private final MatrixClient matrixClient;

    private BotConfig data;

    private final Service<BotDao> service;

    private final Map<String, Command> commands;

    private String help;

    public Bot(Client client, String homeserverUrl, String domain, String asToken, BotConfig data, Service<BotDao> service,
               List<Class<? extends Command>> commandsClasses) {
        this.service = service;
        this.matrixClient = new MatrixClient(homeserverUrl, domain, client, true, true, data.getTxnId());
        this.matrixClient.setAccessToken(asToken);
        this.matrixClient.setUserId(data.getUserId());
        this.matrixClient.setDeviceId(data.getDeviceId());
        this.data = data;
        this.commands = new HashMap<>(commandsClasses.size());
        StringBuilder sb = new StringBuilder("!help - show help.\n");
        commandsClasses.forEach(cl -> {
            try {
                Command command = cl.newInstance();
                this.commands.put(command.name(), command);
                sb.append(command.usage()).append(" - ").append(command.help()).append("\n");
            } catch (InstantiationException | IllegalAccessException e) {
                LOGGER.error("Cannot create new instance of the command: " + cl.getCanonicalName(), e);
            }
        });
        this.help = sb.toString();
    }

    public BotConfig getData() {
        return data;
    }

    public void setData(BotConfig data) {
        this.data = data;
    }

    public Service<BotDao> getService() {
        return service;
    }

    public Map<String, Command> getCommands() {
        return commands;
    }

    public String getHelp() {
        return help;
    }

    @Override
    public void run() {
        try {
            LoopState state = LoopState.RUN;
            while (!LoopState.EXIT.equals(state)) {
                switch (getData().getState()) {
                    case NEW:
                        state = newState();
                        break;
                    case REGISTERED:
                        state = registeredState();
                        break;
                    case JOINED:
                        state = joinedState();
                        break;
                    default:
                        LOGGER.error("Unknown state: " + getData().getState());
                }
            }

        } catch (Throwable e) {
            LOGGER.error("Exception:", e);
            throw e;
        }
    }

    /**
     * Save bot's config.
     *
     * @param dao DAO.
     */
    protected void saveData(BotDao dao) {
        setData(dao.save(getData()));
    }

    /**
     * Main loop.
     *
     * @param loopAction state action.
     * @return next loop state.
     */
    protected LoopState loop(Function<SyncResponse, LoopState> loopAction) {
        SyncResponse sync = matrixClient.sync(getData().getFilterId(), getData().getNextBatch(), false, null, null);
        while (true) {
            LoopState nextState = loopAction.apply(sync);

            String nextBatch = sync.getNextBatch();
            getService().invoke(dao -> {
                getData().setNextBatch(nextBatch);
                saveData(dao);
            });

            if (LoopState.NEXT_STATE.equals(nextState)) {
                return LoopState.NEXT_STATE;
            }

            if (Thread.currentThread().isInterrupted()) {
                return LoopState.EXIT;
            }

            sync = matrixClient.sync(getData().getFilterId(), nextBatch, false, null, getData().getTimeout());
        }
    }

    /**
     * Register a new bot.
     * <p/>
     * After registration setup a filter to receive only message events.
     *
     * @return {@link LoopState#NEXT_STATE} always. Move to the next state.
     */
    public LoopState newState() {
        BotConfig data = getData();
        getService().invoke((dao) -> {
            RegisterRequest registerRequest = new RegisterRequest();
            registerRequest.setUsername(Id.localpart(data.getUserId()));
            registerRequest.setInitialDeviceDisplayName(data.getDisplayName());
            registerRequest.setDeviceId(data.getDeviceId());
            matrixClient.register(registerRequest);
            matrixClient.setDisplayName(data.getDisplayName());

            RoomEventFilter roomEventFilter = new RoomEventFilter();
            roomEventFilter.setTypes(Collections.singletonList(Event.EventType.ROOM_MESSAGE));
            RoomFilter roomFilter = new RoomFilter();
            roomFilter.setTimeline(roomEventFilter);
            FilterData filter = new FilterData();
            filter.setRoom(roomFilter);
            getData().setFilterId(matrixClient.filter().uploadFilter(filter).getFilterId());

            getData().setState(BotState.REGISTERED);

            saveData(dao);
        });
        return LoopState.NEXT_STATE;
    }

    /**
     * Waiting to join.
     *
     * @return next loop state.
     */
    protected LoopState registeredState() {
        return loop(sync -> {
            Rooms rooms = sync.getRooms();

            if (rooms.getInvite().size() > 1) {
                LOGGER.error("Too many rooms to join");
                delete();
                return LoopState.EXIT;
            }

            if (!rooms.getInvite().isEmpty()) {
                rooms.getInvite().forEach(this::joinRoom);
                return LoopState.NEXT_STATE;
            }

            return LoopState.RUN;
        });
    }

    /**
     * Logic of the joined state.
     *
     * @return next loop state.
     */
    protected LoopState joinedState() {
        return loop(sync -> {
            Rooms rooms = sync.getRooms();

            JoinedRoom joinedRoom = rooms.getJoin().get(getData().getRoomId());

            if (joinedRoom != null) {
                processJoinedRoom(joinedRoom);
            }

            for (Map.Entry<String, LeftRoom> roomEntry : rooms.getLeave().entrySet()) {
                matrixClient.room().leaveRoom(roomEntry.getKey());
            }

            if (matrixClient.room().joinedRooms().isEmpty()) {
                delete();
                return LoopState.EXIT;
            }

            return LoopState.RUN;
        });
    }

    /**
     * Join to room.
     *
     * @param roomId room id.
     * @param room   room to join.
     */
    public void joinRoom(String roomId, InvitedRoom room) {
        getService().invoke(dao -> {
            RoomId response = matrixClient.room().joinRoomByIdOrAlias(roomId);
            if (StringUtils.isAllBlank(response.getErrcode(), response.getError())) {
                getData().setRoomId(roomId);
                getData().setState(BotState.JOINED);

                room.getInviteState().getEvents().stream().filter(e -> {
                    Object membership = e.getContent().get("membership");
                    return membership instanceof String
                        && Event.MembershipState.INVITE.equals(membership)
                        && Event.EventType.ROOM_MEMBER.equals(e.getType());
                }).map(Event::getSender).findFirst().ifPresent(s -> getData().setOwner(s));

                saveData(dao);
            } else {
                throw new RuntimeException(
                    String.format("Failed join to room, errcode: ''%s'', error: ''%s''", response.getErrcode(), response.getError()));
            }
        });
    }

    /**
     * Delete bot.
     */
    public void delete() {
        getService().invoke(dao -> {
            matrixClient.deactivate();
            dao.delete(getData());
        });
    }

    /**
     * Process commands.
     *
     * @param joinedRoom room's data.
     */
    public void processJoinedRoom(JoinedRoom joinedRoom) {
        String lastEvent = null;
        long lastOriginTs = 0;
        for (Event event : joinedRoom.getTimeline().getEvents()) {
            Map<String, Object> content = event.getContent();
            if (Event.EventType.ROOM_MESSAGE.equals(event.getType())
                && !matrixClient.getUserId().equals(event.getSender())
                && Event.MessageType.TEXT.equals(content.get("msgtype"))
                && permit(event)) {
                String action = (String) content.get("body");
                try {
                    getService().invoke((dao) -> {
                        processAction(event, action);
                        getData().setTxnId(matrixClient.getTxn().get());
                        saveData(dao);
                    });
                } catch (Exception e) {
                    LOGGER.error(String.format("Cannot perform action ''%s''", action));
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
    }

    /**
     * Permission check.
     *
     * @param event event.
     * @return {@code true}, if process event, else {@code false}.
     */
    protected boolean permit(Event event) {
        return getData().getPolicy() == null
            || AccessPolicy.ALL.equals(getData().getPolicy())
            || getData().getOwner().equals(event.getSender());
    }

    /**
     * Process action.
     *
     * @param event   event.
     * @param content command.
     */
    protected void processAction(Event event, String content) {
        String[] arguments = content.trim().split("\\s");
        if (arguments[0].startsWith("!")) {
            Command command = getCommands().get(arguments[0].substring(1));
            if (command != null) {
                command.invoke(matrixClient, getData(), event, content);
            } else {
                matrixClient.sendNotice(getData().getRoomId(), getHelp());
            }
        }

    }
}
