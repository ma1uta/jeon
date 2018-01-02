package geek.ma1uta.matrix.client.api;

import geek.ma1uta.matrix.client.model.encryption.ChangesRequest;
import geek.ma1uta.matrix.client.model.encryption.ChangesResponse;
import geek.ma1uta.matrix.client.model.encryption.ClaimRequest;
import geek.ma1uta.matrix.client.model.encryption.ClaimResponse;
import geek.ma1uta.matrix.client.model.encryption.QueryRequest;
import geek.ma1uta.matrix.client.model.encryption.QueryResponse;
import geek.ma1uta.matrix.client.model.encryption.UploadRequest;
import geek.ma1uta.matrix.client.model.encryption.UploadResponse;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/_matrix/client/r0/keys")
@JsonRest
public interface EncryptionApi {

    @POST
    @Path("/upload")
    UploadResponse upload(UploadRequest uploadRequest);

    @POST
    @Path("/query")
    QueryResponse query(QueryRequest queryRequest);

    @POST
    @Path("/claim")
    ClaimResponse claim(ClaimRequest claimRequest);

    @GET
    @Path("/changes")
    ChangesResponse changes(ChangesRequest changesRequest);
}
