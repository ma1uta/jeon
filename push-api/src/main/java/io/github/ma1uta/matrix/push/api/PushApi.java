/*
 * Copyright Anatoliy Sablin tolya@sablin.xyz
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

package io.github.ma1uta.matrix.push.api;

import io.github.ma1uta.matrix.push.model.Notification;
import io.github.ma1uta.matrix.push.model.RejectedPushKey;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

/**
 * This describes the format used by "HTTP" pushers to send notifications of events to Push Gateways. If the endpoint returns an HTTP
 * error code, the homeserver SHOULD retry for a reasonable amount of time using exponential backoff.
 */
@Path("/_matrix/push/v1")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface PushApi {

    /**
     * This endpoint is invoked by HTTP pushers to notify a push gateway about an event or update the number of unread notifications
     * a user has. In the former case it will contain selected information about the event. In either case it may contain numeric
     * counts of the number of unread events of different types the user has. The counts may be sent along with a notification about
     * an event or by themselves.
     * <br>
     * Notifications about a particular event will normally cause the user to be alerted in some way. It is therefore necessary to
     * perform duplicate suppression for such notifications using the event_id field to avoid retries of this HTTP API causing
     * duplicate alerts. The operation of updating counts of unread notifications should be idempotent and therefore do not require
     * duplicate suppression.
     * <br>
     * Notifications are sent to the URL configured when the pusher is created. This means that the HTTP path may be different
     * depending on the push gateway.
     * <br>
     * Return: {@link RejectedPushKey}.
     * <p>Status code 200: A list of rejected push keys.</p>
     *
     * @param notification  Information about the push notification.
     * @param uriInfo       Information about the request.
     * @param httpHeaders   Http headers.
     * @param asyncResponse Asynchronous response.
     */
    @Operation(
        summary = "This endpoint is invoked by HTTP pushers to notify a push gateway about an event or update the number"
            + " of unread notifications a user has.",
        description = "In the former case it will contain selected information about the event. In either case it may contain numeric"
            + " counts of the number of unread events of different types the user has. The counts may be sent along with a notification"
            + " about an event or by themselves.\nNotifications about a particular event will normally cause the user to be alerted"
            + " in some way.It is therefore necessary to perform duplicate suppression for such notifications using the event_id field"
            + " to avoid retries of this HTTP API causing duplicate alerts.The operation of updating counts of unread notifications"
            + " should be idempotent and therefore do not require duplicate suppression.\nNotifications are sent to the URL configured"
            + " when the pusher is created.This means that the HTTP path may be different depending on the push gateway.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "A list of rejected push keys.",
                content = @Content(
                    schema = @Schema(
                        implementation = RejectedPushKey.class
                    )
                )
            )
        }
    )
    @POST
    @Path("/notify")
    void pushNotify(
        @RequestBody(
            description = "Information about the push notification.",
            required = true
        ) Notification notification,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse
    );
}
