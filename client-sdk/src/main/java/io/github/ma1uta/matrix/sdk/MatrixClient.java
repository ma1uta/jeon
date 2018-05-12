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

package io.github.ma1uta.matrix.sdk;

import io.github.ma1uta.matrix.EmptyResponse;
import io.github.ma1uta.matrix.Event;
import io.github.ma1uta.matrix.client.api.AuthApi;
import io.github.ma1uta.matrix.client.api.EventApi;
import io.github.ma1uta.matrix.client.api.FilterApi;
import io.github.ma1uta.matrix.client.api.ProfileApi;
import io.github.ma1uta.matrix.client.api.ReceiptApi;
import io.github.ma1uta.matrix.client.api.RoomApi;
import io.github.ma1uta.matrix.client.api.SyncApi;
import io.github.ma1uta.matrix.client.model.auth.AuthType;
import io.github.ma1uta.matrix.client.model.auth.LoginRequest;
import io.github.ma1uta.matrix.client.model.auth.LoginResponse;
import io.github.ma1uta.matrix.client.model.event.SendEventResponse;
import io.github.ma1uta.matrix.client.model.filter.FilterData;
import io.github.ma1uta.matrix.client.model.filter.FilterResponse;
import io.github.ma1uta.matrix.client.model.profile.DisplayName;
import io.github.ma1uta.matrix.client.model.room.JoinRequest;
import io.github.ma1uta.matrix.client.model.room.RoomId;
import io.github.ma1uta.matrix.client.model.sync.SyncResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicLong;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

/**
 * Matrix client.
 */
public class MatrixClient implements Closeable {

    private static final Logger LOGGER = LoggerFactory.getLogger(MatrixClient.class);

    private final String homeserverUrl;

    private final Client client;

    private String userId;

    private String accessToken;

    private String deviceId;

    private final AtomicLong txn;

    public MatrixClient(String homeserverUrl, Client client) {
        this.homeserverUrl = homeserverUrl;
        this.client = client;
        this.txn = new AtomicLong(0);
    }

    public String getHomeserverUrl() {
        return homeserverUrl;
    }

    public String getUserId() {
        return userId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public Client getClient() {
        return client;
    }

    /**
     * Login.
     *
     * @param login    username.
     * @param password password.
     */
    public void login(String login, String password) {
        LOGGER.debug("Login with username: ''{}'' and password: ''<redacted>''", login);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setType(AuthType.PASSWORD);
        loginRequest.setUser(login);
        loginRequest.setPassword(password);

        login(loginRequest);
    }

    /**
     * Login.
     *
     * @param loginRequest request.
     */
    public void login(LoginRequest loginRequest) {
        LoginResponse loginResponse = post(AuthApi.class, "login", null, null, loginRequest, LoginResponse.class, null);

        this.userId = loginResponse.getUserId();
        this.accessToken = loginResponse.getAccessToken();
        this.deviceId = loginResponse.getDeviceId();
    }

    /**
     * Logout.
     */
    public void logout() {
        LOGGER.debug("Logout");
        post(AuthApi.class, "logout", null, null, "", EmptyResponse.class, null);
        this.accessToken = null;
        this.deviceId = null;
    }

    public boolean isAuthenticated() {
        return getAccessToken() != null;
    }

    /**
     * Set new display name.
     *
     * @param displayName display name.
     */
    public void setDisplayName(String displayName) {
        Map<String, String> params = new HashMap<>();
        params.put("userId", getUserId());
        DisplayName request = new DisplayName();
        request.setDisplayname(displayName);
        put(ProfileApi.class, "setDisplayName", params, null, request, EmptyResponse.class);
    }

    /**
     * Sync events.
     *
     * @param filter    filter name.
     * @param since     next batch token.
     * @param fullState full state or not.
     * @param presence  offline presence or not.
     * @param timeout   timeout
     * @return sync data.
     */
    public SyncResponse sync(String filter, String since, boolean fullState, String presence, Long timeout) {
        Map<String, String> queryParams = new HashMap<>();
        if (filter != null) {
            queryParams.put("filter", filter);
        }
        if (since != null) {
            queryParams.put("since", since);
        }
        queryParams.put("fullState", Boolean.toString(fullState));
        if (presence != null) {
            queryParams.put("presence", presence);
        }
        if (timeout != null) {
            queryParams.put("timeout", Long.toString(timeout));
        }
        return get(SyncApi.class, "sync", null, queryParams, SyncResponse.class);
    }

    /**
     * Join to the room.
     *
     * @param idOrAlias room id or alias.
     * @return room id.
     */
    public RoomId joinRoomByIdOrAlias(String idOrAlias) {
        Map<String, String> pathParams = new HashMap<>();
        pathParams.put("roomIdOrAlias", idOrAlias);
        return post(RoomApi.class, "joinByIdOrAlias", pathParams, null, new JoinRequest(), RoomId.class);
    }

    /**
     * Leave room.
     *
     * @param roomId room id.
     */
    public void leaveRoom(String roomId) {
        Map<String, String> pathParams = new HashMap<>();
        pathParams.put("roomId", roomId);
        post(RoomApi.class, "leave", pathParams, null, "", EmptyResponse.class);
    }

    /**
     * Send notice.
     *
     * @param roomId room id.
     * @param text   message.
     * @return sent event id.
     */
    public SendEventResponse sendNotice(String roomId, String text) {
        Map<String, String> pathParams = new HashMap<>();
        pathParams.put("roomId", roomId);
        pathParams.put("eventType", Event.EventType.ROOM_MESSAGE);
        pathParams.put("txnId", Long.toString(txn.getAndIncrement()));

        Map<String, String> payload = new HashMap<>();
        payload.put("msgtype", Event.MessageType.NOTICE);
        payload.put("body", text);
        return put(EventApi.class, "sendEvent", pathParams, null, payload, SendEventResponse.class);
    }

    /**
     * Send receipt to specified event.
     *
     * @param roomId  room id.
     * @param eventId event id.
     */
    public void sendReceipt(String roomId, String eventId) {
        Map<String, String> pathParams = new HashMap<>();
        pathParams.put("roomId", roomId);
        pathParams.put("eventId", eventId);
        pathParams.put("receiptType", "m.read");
        post(ReceiptApi.class, "receipt", pathParams, null, "", EmptyResponse.class);
    }

    /**
     * Upload new filter.
     *
     * @param filter new filter.
     * @return filter id.
     */
    public FilterResponse uploadFilter(FilterData filter) {
        Map<String, String> pathParams = new HashMap<>();
        pathParams.put("userId", getUserId());
        return post(FilterApi.class, "uploadFilter", pathParams, null, filter, FilterResponse.class);
    }

    /**
     * Get specified filter.
     *
     * @param filterId filter id.
     * @return filter.
     */
    public FilterData getFilter(String filterId) {
        Map<String, String> pathParams = new HashMap<>();
        pathParams.put("userId", getUserId());
        pathParams.put("filterId", filterId);
        return get(FilterApi.class, "getFilter", pathParams, null, FilterData.class);
    }

    protected Invocation.Builder prepare(Class<?> apiClass, String apiMethod, Map<String, String> pathParams,
                                         Map<String, String> queryParams, String requestType) {
        UriBuilder builder = UriBuilder.fromResource(apiClass).path(apiClass, apiMethod);
        Map<String, String> encoded = new HashMap<>();
        if (pathParams != null) {
            for (Map.Entry<String, String> entry : pathParams.entrySet()) {
                try {
                    encoded.put(entry.getKey(), URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8.name()));
                } catch (UnsupportedEncodingException e) {
                    String msg = "Unsupported encoding";
                    LOGGER.error(msg, e);
                    throw new RuntimeException(e);
                }
            }
        }
        URI uri = builder.buildFromEncodedMap(encoded);

        WebTarget path;
        path = getClient().target(getHomeserverUrl()).path(uri.toString());
        if (queryParams != null) {
            for (Map.Entry<String, String> entry : queryParams.entrySet()) {
                path = path.queryParam(entry.getKey(), entry.getValue());
            }
        }
        Invocation.Builder request = path.request(requestType);
        if (getAccessToken() != null) {
            request = request.header("Authorization", "Bearer " + getAccessToken());
        }
        return request;
    }

    protected <T, R> R post(Class<?> apiClass, String apiMethod, Map<String, String> pathParams, Map<String, String> queryParams, T payload,
                            Class<R> responseClass) {
        return post(apiClass, apiMethod, pathParams, queryParams, payload, responseClass, MediaType.APPLICATION_JSON);
    }

    protected <T, R> R post(Class<?> apiClass, String apiMethod, Map<String, String> pathParams, Map<String, String> queryParams, T payload,
                            Class<R> responseClass, String requestType) {
        return prepare(apiClass, apiMethod, pathParams, queryParams, requestType).post(Entity.json(payload), responseClass);
    }

    protected <R> R get(Class<?> apiClass, String apiMethod, Map<String, String> pathParams, Map<String, String> queryParams,
                        Class<R> responseClass) {
        try {
            return prepare(apiClass, apiMethod, pathParams, queryParams, MediaType.APPLICATION_JSON).async().get(responseClass).get();
        } catch (InterruptedException | ExecutionException e) {
            String msg = "Failed invoke get request";
            LOGGER.error(msg, e);
            throw new RuntimeException(msg, e);
        }
    }

    protected <T, R> R put(Class<?> apiClass, String apiMethod, Map<String, String> pathParams, Map<String, String> queryParams, T payload,
                           Class<R> responseClass) {
        return prepare(apiClass, apiMethod, pathParams, queryParams, MediaType.APPLICATION_JSON)
            .put(Entity.json(payload), responseClass);
    }

    @Override
    public void close() {
        logout();
    }
}
