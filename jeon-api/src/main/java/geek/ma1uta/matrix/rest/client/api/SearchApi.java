package geek.ma1uta.matrix.rest.client.api;

import geek.ma1uta.matrix.rest.client.model.search.SearchRequest;
import geek.ma1uta.matrix.rest.client.model.search.SearchResponse;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("/_matrix/client/r0")
public interface SearchApi {

    @POST
    @Path("/search")
    SearchResponse search(@QueryParam("next_batch") String nextBatch, SearchRequest searchRequest);
}
