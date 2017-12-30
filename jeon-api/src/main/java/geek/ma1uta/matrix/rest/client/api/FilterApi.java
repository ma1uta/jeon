package geek.ma1uta.matrix.rest.client.api;

import geek.ma1uta.matrix.UserId;
import geek.ma1uta.matrix.rest.client.model.FilterData;
import geek.ma1uta.matrix.rest.client.model.FilterResponse;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/_matrix/client/r0/user")
@JsonRest
public interface FilterApi {

    @POST
    @Path("/{userId}/filter")
    FilterResponse uploadFilter(@PathParam("userId") UserId userId, FilterData filterData);

    @GET
    @Path("/{userId}/filter/{filterId}")
    FilterData getFilter(@PathParam("userId") UserId userId, @PathParam("filterId") String filterId);
}
