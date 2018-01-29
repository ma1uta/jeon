package io.github.ma1uta.matrix.client.api;

import io.github.ma1uta.matrix.client.model.admin.AdminResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/_matrix/client/r0/admin")
public interface AdminApi {

    @GET
    @Path("/whois/{userId}")
    AdminResponse whois(@PathParam("userId") String userId);
}
