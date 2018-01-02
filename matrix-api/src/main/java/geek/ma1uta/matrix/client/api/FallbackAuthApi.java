package geek.ma1uta.matrix.client.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/_matrix")
@Produces(MediaType.TEXT_HTML)
public interface FallbackAuthApi {

    @GET
    @Path("/static/client/login")
    String login();

    @GET
    @Path("/client/r0/auth/{auth}/fallback/web")
    String auth(@PathParam("auth") String auth, @QueryParam("session") String session);
}
