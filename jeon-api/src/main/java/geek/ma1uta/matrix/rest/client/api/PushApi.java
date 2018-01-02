package geek.ma1uta.matrix.rest.client.api;

import geek.ma1uta.matrix.rest.client.model.EmptyResponse;
import geek.ma1uta.matrix.rest.client.model.push.ActionsData;
import geek.ma1uta.matrix.rest.client.model.push.EnableData;
import geek.ma1uta.matrix.rest.client.model.push.NotificationResponse;
import geek.ma1uta.matrix.rest.client.model.push.PushRule;
import geek.ma1uta.matrix.rest.client.model.push.PushRulesResponse;
import geek.ma1uta.matrix.rest.client.model.push.PushUpdateRequest;
import geek.ma1uta.matrix.rest.client.model.push.PushersRequest;
import geek.ma1uta.matrix.rest.client.model.push.PushersResponse;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

@Path("/_matrix/client/r0")
@JsonRest
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
