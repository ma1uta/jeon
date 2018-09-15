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

package io.github.ma1uta.matrix.push.api;

import io.github.ma1uta.matrix.push.model.Notification;
import io.github.ma1uta.matrix.push.model.RejectedPushKey;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * This describes the format used by "HTTP" pushers to send notifications of events to Push Gateways. If the endpoint returns an HTTP
 * error code, the homeserver SHOULD retry for a reasonable amount of time using exponential backoff.
 */
@Api(
    value = "Push",
    description = "This describes the format used by \"HTTP\" pushers to send notifications of events to Push Gateways. If the endpoint"
        + " returns an HTTP error code, the homeserver SHOULD retry for a reasonable amount of time using exponential backoff."
)
@Path("/_matrix/push/r0")
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
     * Return:
     * <p>Status code 200: A list of rejected push keys.</p>
     *
     * @param notification   Information about the push notification.
     * @param servletRequest Servlet request.
     * @param response       Async response.
     */
    @ApiOperation(
        value = "This endpoint is invoked by HTTP pushers to notify a push gateway about an event or update the number"
            + " of unread notifications a user has.",
        notes = "In the former case it will contain selected information about the event. In either case it may contain numeric"
            + " counts of the number of unread events of different types the user has. The counts may be sent along with a notification"
            + " about an event or by themselves.\nNotifications about a particular event will normally cause the user to be alerted"
            + " in some way.It is therefore necessary to perform duplicate suppression for such notifications using the event_id field"
            + " to avoid retries of this HTTP API causing duplicate alerts.The operation of updating counts of unread notifications"
            + " should be idempotent and therefore do not require duplicate suppression.\nNotifications are sent to the URL configured"
            + " when the pusher is created.This means that the HTTP path may be different depending on the push gateway.",
        response = RejectedPushKey.class
    )
    @ApiResponses( {
        @ApiResponse(code = 200, message = "A list of rejected push keys.")
    })
    @POST
    @Path("/notify")
    void pushNotify(
        @ApiParam(
            value = "Information about the push notification.",
            required = true
        ) Notification notification,

        @Context HttpServletRequest servletRequest,
        @Suspended AsyncResponse response
    );
}
