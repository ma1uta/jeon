/*
 * Copyright Anatoliy Sablin tolya@sablin.xyz
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.ma1uta.matrix.client.api;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import io.github.ma1uta.matrix.RateLimit;
import io.github.ma1uta.matrix.RateLimitedErrorResponse;
import io.github.ma1uta.matrix.Secured;
import io.github.ma1uta.matrix.client.model.content.ContentConfig;
import io.github.ma1uta.matrix.client.model.content.ContentUri;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

/**
 * This module allows users to upload content to their homeserver which is retrievable from other homeservers.
 * Its' purpose is to allow users to share attachments in a room. Key locations are represented as Matrix Key (MXC) URIs.
 * They look like:
 * <pre>
 * mxc://(server-name)/(media-id)
 *
 * (server-name) : The name of the homeserver where this content originated, e.g. matrix.org
 * (media-id) : An opaque ID which identifies the content.
 * </pre>
 * Uploads are POSTed to a resource on the user's local homeserver which returns a token which is used to GET the download.
 * Key is downloaded from the recipient's local homeserver, which must first transfer the content from the origin homeserver
 * using the same API (unless the origin and destination homeservers are the same).
 */
@Path("/_matrix/media/r0")
public interface ContentApi {

    /**
     * The desired resizing method.
     */
    class Method {

        protected Method() {
        }

        /**
         * Crop.
         */
        public static final String CROP = "crop";

        /**
         * Scale.
         */
        public static final String SCALE = "scale";
    }

    /**
     * Upload some content to the content repository.
     * <br>
     * <b>Rate-limited</b>: Yes.
     * <br>
     * <b>Requires auth</b>: Yes.
     * <br>
     * Return: {@link ContentUri}.
     * <b>Required</b>. The MXC URI to the uploaded content.
     * <p>Status code 200: The MXC URI for the uploaded content.</p>
     * <p>Status code 429: This request was rate-limited.</p>
     *
     * @param inputStream     The file content.
     * @param filename        The name of the file being uploaded.
     * @param contentType     The content type of the file being uploaded.
     * @param uriInfo         Request Information.
     * @param httpHeaders     Http headers.
     * @param asyncResponse   Asynchronous response.
     * @param securityContext Security context.
     */
    @Operation(
        summary = "Upload some content to the content repository.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The MXC URI for the uploaded content.",
                content = @Content(
                    schema = @Schema(
                        implementation = ContentUri.class
                    )
                )
            ),
            @ApiResponse(
                responseCode = "429",
                description = "This request was rate-limited.",
                content = @Content(
                    schema = @Schema(
                        implementation = RateLimitedErrorResponse.class
                    )
                )
            )
        },
        security = {
            @SecurityRequirement(
                name = "accessToken"
            )
        },
        tags = {
            "Media"
        }
    )
    @POST
    @RateLimit
    @Secured
    @Path("/upload")
    @Produces(MediaType.APPLICATION_JSON)
    void upload(
        @RequestBody(
            description = "The file content"
        ) InputStream inputStream,
        @Parameter(
            description = "The name of the file being uploaded."
        ) @QueryParam("filename") String filename,
        @Parameter(
            description = "The content type of the file being uploaded"
        ) @HeaderParam("Content-Type") String contentType,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse,
        @Context SecurityContext securityContext
    );

    /**
     * Download content from the content repository.
     * <br>
     * <b>Rate-limited</b>: Yes.
     * <br>
     * Return: {@link OutputStream}.
     * <p>Response headers:</p>
     * <table summary="Response header">
     * <tr>
     * <th>Parameter</th>
     * <th>Type</th>
     * <th>Description</th>
     * </tr>
     * <tr>
     * <td>Content-Type</td>
     * <td>string</td>
     * <td>The content type of the file that was previously uploaded.</td>
     * </tr>
     * <tr>
     * <td>Content-Disposition</td>
     * <td>string</td>
     * <td>The name of the file that was previously uploaded, if set.</td>
     * </tr>
     * </table>
     * <p>Status code 200: The content that was previously uploaded.</p>
     * <p>Status code 429: This request was rate-limited.</p>
     *
     * @param serverName    Required. The server name from the mxc:// URI (the authoritory component).
     * @param mediaId       Required. The media ID from the mxc:// URI (the path component).
     * @param allowRemote   Indicates to the server that it should not attempt to fetch the media if it is deemed remote.
     *                      This is to prevent routing loops where the server contacts itself. Defaults to true if not provided.
     * @param uriInfo       Request Information.
     * @param httpHeaders   Http headers.
     * @param asyncResponse Asynchronous response.
     */
    @Operation(
        summary = "Download content from the content repository.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The content that was previously uploaded."
            ),
            @ApiResponse(
                responseCode = "429",
                description = "This request was rate-limited.",
                content = @Content(
                    schema = @Schema(
                        implementation = RateLimitedErrorResponse.class
                    )
                )
            )
        },
        tags = {
            "Media"
        }
    )
    @GET
    @RateLimit
    @Path("/download/{serverName}/{mediaId}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    void download(
        @Parameter(
            description = "The server name from the mxc:// URI (the authoritory component).",
            required = true
        ) @PathParam("serverName") String serverName,
        @Parameter(
            description = "The media ID from the mxc:// URI (the path component).",
            required = true
        ) @PathParam("mediaId") String mediaId,
        @Parameter(
            description = "Indicates to the server that it should not attempt to fetch the media if it is deemed remote. "
                + "This is to prevent routing loops where the server contacts itself. Defaults to true if not provided."
        ) @QueryParam("allow_remote") Boolean allowRemote,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse
    );

    /**
     * Download content from the content repository as a given filename.
     * <br>
     * <b>Rate-limited</b>: Yes.
     * <br>
     * Return: {@link OutputStream}.
     * <p>Response headers:</p>
     * <table summary="Response headers">
     * <tr>
     * <th>Parameter</th>
     * <th>Type</th>
     * <th>Description</th>
     * </tr>
     * <tr>
     * <td>Content-Type</td>
     * <td>string</td>
     * <td>The content type of the file that was previously uploaded.</td>
     * </tr>
     * <tr>
     * <td>Content-Disposition</td>
     * <td>string</td>
     * <td>The name of the file that was previously uploaded, if set.</td>
     * </tr>
     * </table>
     * <p>Status code 200: The content that was previously uploaded.</p>
     * <p>Status code 429: This request was rate-limited.</p>
     *
     * @param serverName    Required. The server name from the mxc:// URI (the authoritory component).
     * @param mediaId       Required. The media ID from the mxc:// URI (the path component).
     * @param filename      Required. The filename to give in the Content-Disposition.
     * @param allowRemote   Indicates to the server that it should not attempt to fetch the media if it is deemed remote.
     *                      This is to prevent routing loops where the server contacts itself. Defaults to true if not provided.
     * @param uriInfo       Request Information.
     * @param httpHeaders   Http headers.
     * @param asyncResponse Asynchronous response.
     */
    @Operation(
        summary = "Download content from the content repository as a given filename.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The content that was previously uploaded."
            ),
            @ApiResponse(
                responseCode = "429",
                description = "This request was rate-limited.",
                content = @Content(
                    schema = @Schema(
                        implementation = RateLimitedErrorResponse.class
                    )
                )
            )
        },
        tags = {
            "Media"
        }
    )
    @GET
    @RateLimit
    @Path("/download/{serverName}/{mediaId}/{fileName}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    void downloadFile(
        @Parameter(
            description = "The server name from the mxc:// URI (the authoritory component).",
            required = true
        ) @PathParam("serverName") String serverName,
        @Parameter(
            description = "The media ID from the mxc:// URI (the path component).",
            required = true
        ) @PathParam("mediaId") String mediaId,
        @Parameter(
            description = "The filename to give in the Content-Disposition.",
            required = true
        ) @PathParam("fileName") String filename,
        @Parameter(
            description = "Indicates to the server that it should not attempt to fetch the media if it is deemed remote. "
                + "This is to prevent routing loops where the server contacts itself. Defaults to true if not provided."
        ) @QueryParam("allow_remote") Boolean allowRemote,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse
    );

    /**
     * Download a thumbnail of the content from the content repository.
     * <br>
     * <b>Rate-limited</b>: Yes.
     * <br>
     * Return: {@link OutputStream}.
     * <p>Response headers:</p>
     * <table summary="Response headers">
     * <tr>
     * <th>Parameter</th>
     * <th>Type</th>
     * <th>Description</th>
     * </tr>
     * <tr>
     * <td>Key-Type</td>
     * <td>string</td>
     * <td>The content type of the file that was previously uploaded.</td>
     * </tr>
     * </table>
     * <p>Status code 200: The content that was previously uploaded.</p>
     * <p>Status code 429: This request was rate-limited.</p>
     *
     * @param serverName    Required. The server name from the mxc:// URI (the authoritory component).
     * @param mediaId       Required. The media ID from the mxc:// URI (the path component)
     * @param width         The desired width of the thumbnail. The actual thumbnail may not match the size specified.
     * @param height        The desired height of the thumbnail. The actual thumbnail may not match the size specified.
     * @param method        The desired resizing method. One of: ["crop", "scale"].
     * @param allowRemote   Indicates to the server that it should not attempt to fetch the media if it is deemed remote.
     *                      This is to prevent routing loops where the server contacts itself. Defaults to true if not provided.
     * @param uriInfo       Request Information.
     * @param httpHeaders   Http headers.
     * @param asyncResponse Asynchronous response.
     */
    @Operation(
        summary = "Download a thumbnail of the content from the content repository.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The content that was previously uploaded."
            ),
            @ApiResponse(
                responseCode = "429",
                description = "This request was rate-limited.",
                content = @Content(
                    schema = @Schema(
                        implementation = RateLimitedErrorResponse.class
                    )
                )
            )
        },
        tags = {
            "Media"
        }
    )
    @GET
    @RateLimit
    @Path("/thumbnail/{serverName}/{mediaId}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    void thumbnail(
        @Parameter(
            description = "The server name from the mxc:// URI (the authoritory component).",
            required = true
        ) @PathParam("serverName") String serverName,
        @Parameter(
            description = "The media ID from the mxc:// URI (the path component)",
            required = true
        ) @PathParam("mediaId") String mediaId,
        @Parameter(
            description = "The desired width of the thumbnail. The actual thumbnail may not match the size specified."
        ) @QueryParam("width") Long width,
        @Parameter(
            description = "The desired height of the thumbnail. The actual thumbnail may not match the size specified."
        ) @QueryParam("height") Long height,
        @Parameter(
            description = "The desired resizing method.",
            schema = @Schema(
                allowableValues = {
                    "crop",
                    "scale"
                }
            )
        ) @QueryParam("method") String method,
        @Parameter(
            description = "Indicates to the server that it should not attempt to fetch the media if it is deemed remote. "
                + "This is to prevent routing loops where the server contacts itself. Defaults to true if not provided."
        ) @QueryParam("allow_remote") Boolean allowRemote,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse
    );

    /**
     * Get information about a PATH for a client.
     * <br>
     * <b>Rate-limited</b>: Yes.
     * <br>
     * <b>Requires auth</b>: Yes.
     * <br>
     * Return: {@link Map}.
     * <p>Response headers:</p>
     * <table summary="Response headers">
     * <tr>
     * <th>Parameter</th>
     * <th>Type</th>
     * <th>Description</th>
     * </tr>
     * <tr>
     * <td>matrix:image:size</td>
     * <td>number</td>
     * <td>The byte-size of the image. Omitted if there is no image attached.</td>
     * </tr>
     * <tr>
     * <td>og:image</td>
     * <td>string</td>
     * <td>An MXC URI to the image. Omitted if there is no image.</td>
     * </tr>
     * </table>
     * <p>Status code 200: The content that was previously uploaded.</p>
     * <p>Status code 429: This request was rate-limited.</p>
     *
     * @param url             Required. The PATH to get a preview of.
     * @param ts              The preferred point in time to return a preview for. The server may return a newer version if it does not
     *                        have the requested version available.
     * @param uriInfo         Request Information.
     * @param httpHeaders     Http headers.
     * @param asyncResponse   Asynchronous response.
     * @param securityContext Security context.
     */
    @Operation(
        summary = "Get information about a PATH for a client.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The content that was previously uploaded.",
                content = @Content(
                    schema = @Schema(
                        implementation = Map.class
                    )
                )
            ),
            @ApiResponse(
                responseCode = "429",
                description = "This request was rate-limited.",
                content = @Content(
                    schema = @Schema(
                        implementation = RateLimitedErrorResponse.class
                    )
                )
            )
        },
        security = {
            @SecurityRequirement(
                name = "accessToken"
            )
        },
        tags = {
            "Media"
        }
    )
    @GET
    @RateLimit
    @Secured
    @Path("/preview_url")
    @Produces(MediaType.APPLICATION_JSON)
    void previewUrl(
        @Parameter(
            description = "The PATH to get a preview of.",
            required = true
        ) @QueryParam("url") String url,
        @Parameter(
            description = "The preferred point in time to return a preview for. The server may return a newer "
                + "version if it does not have the requested version available."
        ) @QueryParam("ts") String ts,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse,
        @Context SecurityContext securityContext
    );

    /**
     * This endpoint allows clients to retrieve the configuration of the content repository, such as upload limitations.
     * Clients SHOULD use this as a guide when using content repository endpoints. All values are intentionally left optional.
     * Clients SHOULD follow the advice given in the field description when the field is not available.
     * <br>
     * NOTE: Both clients and server administrators should be aware that proxies between the client and the server may affect
     * the apparent behaviour of content repository APIs, for example, proxies may enforce a lower upload size limit than is
     * advertised by the server on this endpoint.
     * <br>
     * <b>Rate-limited</b>: Yes.
     * <b>Requires auth</b>: Yes.
     * <br>
     * Return: {@link ContentConfig}.
     * <p>Status code 200: The public content repository configuration for the matrix server.</p>
     * <p>Status code 429: This request was rate-limited.</p>
     *
     * @param uriInfo         Request Information.
     * @param httpHeaders     Http headers.
     * @param asyncResponse   Asynchronous response.
     * @param securityContext Security context.
     */
    @Operation(
        summary = "his endpoint allows clients to retrieve the configuration of the content repository, such"
            + "as upload limitations.",
        description = "Clients SHOULD use this as a guide when using content repository endpoints. All values are intentionally left"
            + " optional. Clients SHOULD follow the advice given in the field description when the field is not available."
            + " NOTE: Both clients and server administrators should be aware that proxies between the client and the server may affect"
            + " the apparent behaviour of content repository APIs, for example, proxies may enforce a lower upload size limit than is"
            + " advertised by the server on this endpoint.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The public content repository configuration for the matrix server.",
                content = @Content(
                    schema = @Schema(
                        implementation = ContentConfig.class
                    )
                )
            ),
            @ApiResponse(
                responseCode = "429",
                description = "This request was rate-limited.",
                content = @Content(
                    schema = @Schema(
                        implementation = RateLimitedErrorResponse.class
                    )
                )
            )
        },
        security = {
            @SecurityRequirement(
                name = "accessToken"
            )
        },
        tags = {
            "Media"
        }
    )
    @GET
    @RateLimit
    @Secured
    @Path("/config")
    @Produces(APPLICATION_JSON)
    void config(
        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse,
        @Context SecurityContext securityContext
    );
}
