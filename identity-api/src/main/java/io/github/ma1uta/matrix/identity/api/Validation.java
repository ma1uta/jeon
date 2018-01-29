package io.github.ma1uta.matrix.identity.api;

import io.github.ma1uta.matrix.identity.model.validation.PublishResponse;
import io.github.ma1uta.matrix.identity.model.validation.ValidationResponse;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * Checking non-published 3pid ownership
 *
 * @author ma1uta.
 */
@Path("/_matrix/identity/api/v1/3pid")
public interface Validation {

    /**
     * Check whether ownership of a 3pid was validated.
     *
     * @param sid          session id.
     * @param clientSecret client secret from the requestToken call.
     * @return validation data.
     */
    @GET
    @Path("/getValidated3pid")
    @Produces(MediaType.APPLICATION_JSON)
    ValidationResponse validate(@QueryParam("sid") String sid, @QueryParam("client_secret") String clientSecret);

    /**
     * Publishing a validated association.
     *
     * @param sid          session id.
     * @param clientSecret client secret from the requestToken call.
     * @param mxid         matrix id.
     * @return publish result.
     */
    @POST
    @Path("/bind")
    @Produces(MediaType.APPLICATION_JSON)
    PublishResponse publish(@FormParam("sid") String sid, @FormParam("client_secret") String clientSecret, @FormParam("mxid") String mxid);
}
