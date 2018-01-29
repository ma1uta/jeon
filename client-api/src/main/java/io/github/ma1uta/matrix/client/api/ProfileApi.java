package io.github.ma1uta.matrix.client.api;

import io.github.ma1uta.matrix.client.model.EmptyResponse;
import io.github.ma1uta.matrix.client.model.profile.AvatarUrl;
import io.github.ma1uta.matrix.client.model.profile.DisplayName;
import io.github.ma1uta.matrix.client.model.profile.Profile;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/_matrix/client/r0/profile")
@JsonRest
public interface ProfileApi {

    @PUT
    @Path("/{userId}/displayname")
    EmptyResponse setDisplayName(@PathParam("userId") String userId, DisplayName displayName);

    @GET
    @Path("/{userId}/displayname")
    DisplayName showDisplayName(@PathParam("userId") String userId);

    @PUT
    @Path("/{userId}/avatar_url")
    EmptyResponse setAvatarUrl(@PathParam("userId") String userId, AvatarUrl avatarUrl);

    @GET
    @Path("/{userId}/avatar_url")
    AvatarUrl showAvatarUrl(@PathParam("userId") String userId);

    @GET
    @Path("/{userId}")
    Profile profile(@PathParam("userId") String userId);
}
