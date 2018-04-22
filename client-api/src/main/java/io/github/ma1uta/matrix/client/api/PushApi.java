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

package io.github.ma1uta.matrix.client.api;

import io.github.ma1uta.matrix.EmptyResponse;
import io.github.ma1uta.matrix.client.model.push.ActionsData;
import io.github.ma1uta.matrix.client.model.push.EnableData;
import io.github.ma1uta.matrix.client.model.push.NotificationResponse;
import io.github.ma1uta.matrix.client.model.push.PushRule;
import io.github.ma1uta.matrix.client.model.push.PushRulesResponse;
import io.github.ma1uta.matrix.client.model.push.PushUpdateRequest;
import io.github.ma1uta.matrix.client.model.push.PushersRequest;
import io.github.ma1uta.matrix.client.model.push.PushersResponse;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

@Path("/_matrix/client/r0")
public interface PushApi {

    interface Kind {
        String OVERRIDE = "override";
        String UNDERRIDE = "underride";
        String SENDER = "sender";
        String ROOM = "room";
        String CONTENT = "content";
    }

    @GET
    @Path("/pushers")
    PushersResponse showPushers();

    @POST
    @Path("/pushers/set")
    EmptyResponse setPushers(PushersRequest pushersRequest);

    @GET
    @Path("/notifications")
    NotificationResponse notifications(@QueryParam("from") String from, @QueryParam("only") String only, @QueryParam("limit") Long limit);

    @GET
    @Path("/pushrules")
    PushRulesResponse pushRules();

    @GET
    @Path("/pushrules/{scope}/{kind}/{ruleId}")
    PushRule pushRule(@PathParam("scope") String scope, @PathParam("kind") String kind, @PathParam("ruleId") String ruleId);

    @DELETE
    @Path("/pushrules/{scope}/{kind}/{ruleId}")
    EmptyResponse delete(@PathParam("scope") String scope, @PathParam("kind") String kind, @PathParam("ruleId") String ruleId);

    @PUT
    @Path("/pushrules/{scope}/{kind}/{ruleId}")
    EmptyResponse update(@PathParam("scope") String scope, @PathParam("kind") String kind, @PathParam("ruleId") String ruleId,
                         @QueryParam("before") String before, @QueryParam("after") String after, PushUpdateRequest pushUpdateRequest);

    @GET
    @Path("/pushrules/{scope}/{kind}/{ruleId}/enabled")
    EnableData getEnabled(@PathParam("scope") String scope, @PathParam("kind") String kind, @PathParam("ruleId") String ruleId);

    @PUT
    @Path("/pushrules/{scope}/{kind}/{ruleId}/enabled")
    EmptyResponse setEnabled(@PathParam("scope") String scope, @PathParam("kind") String kind, @PathParam("ruleId") String ruleId,
                             EnableData enableData);

    @GET
    @Path("/pushrules/{scope}/{kind}/{ruleId}/actions")
    ActionsData getActions(@PathParam("scope") String scope, @PathParam("kind") String kind, @PathParam("ruleId") String ruleId);

    @PUT
    @Path("/pushrules/{scope}/{kind}/{ruleId}/actions")
    EmptyResponse setActions(@PathParam("scope") String scope, @PathParam("kind") String kind, @PathParam("ruleId") String ruleId,
                             ActionsData actionsData);

}
