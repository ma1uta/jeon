package io.github.ma1uta.matrix.client.api;

import io.github.ma1uta.matrix.EmptyResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("/_matrix/client/r0/login/cas")
public interface CasApi {

    @GET
    @Path("/redirect")
    EmptyResponse redirect(@QueryParam("redirectUrl") String redirectUrl);

    @GET
    @Path("/ticket")
    EmptyResponse ticket(@QueryParam("redirectUrl") String redirectUrl, @QueryParam("ticket") String ticket);
}
