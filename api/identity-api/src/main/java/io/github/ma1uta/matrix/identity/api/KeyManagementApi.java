package io.github.ma1uta.matrix.identity.api;

import io.github.ma1uta.matrix.identity.model.key.KeyValidationResponse;
import io.github.ma1uta.matrix.identity.model.key.PublicKeyResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * An identity service has some long-term public-private keypairs. These are named in a scheme algorithm:identifier, e.g. ed25519:0.
 * When signing an association, the Matrix standard JSON signing format is used, as specified in the server-server
 * API specification under the heading "Signing Events".
 * <p/>
 * In the event of key compromise, the identity service may revoke any of its keys. An HTTP API is offered to get public keys,
 * and check whether a particular key is valid.
 * <p/>
 * The identity server may also keep track of some short-term public-private keypairs, which may have different usage
 * and lifetime characteristics than the service's long-term keys.
 *
 * @author ma1uta
 */
@Path("/_matrix/identity/api/v1/pubkey")
public interface KeyManagementApi extends IdentityApi {

    /**
     * Get the public key for the passed key ID.
     *
     * @param keyId Required. The ID of the key. This should take the form algorithm:identifier where algorithm
     *              identifies the signing algorithm, and the identifier is an opaque string.
     * @return The public key exists.
     */
    @GET
    @Path("/{keyId}")
    @Produces(MediaType.APPLICATION_JSON)
    PublicKeyResponse get(@PathParam("keyId") String keyId);

    /**
     * Check whether a long-term public key is valid.
     *
     * @param publicKey Required. The unpadded base64-encoded public key to check.
     * @return Whether the public key is recognised and is currently valid.
     */
    @GET
    @Path("/isvalid")
    @Produces(MediaType.APPLICATION_JSON)
    KeyValidationResponse valid(@QueryParam("public_key") String publicKey);

    /**
     * Check whether a short-term public key is valid.
     *
     * @param publicKey Required. The unpadded base64-encoded public key to check.
     * @return Whether the public key is recognised and is currently valid.
     */
    @GET
    @Path("/ephemeral/isvalid")
    @Produces(MediaType.APPLICATION_JSON)
    KeyValidationResponse ephemeralValid(@QueryParam("public_key") String publicKey);
}
