/*
 * Copyright sablintolya@gmail.com
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

import io.github.ma1uta.matrix.client.model.content.ContentUri;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

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
 * <p/>
 * <a href="https://matrix.org/docs/spec/client_server/r0.3.0.html#id307">Specification.</a>
 */
@Path("/_matrix/media/r0")
public interface ContentApi {

    /**
     * The desired resizing method.
     */
    final class Method {
        private Method() {
            //singleton
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
     * <b>Rate-limited</b>: Yes.
     * <b>Requires auth</b>: Yes.
     *
     * @param inputStream The file content.
     * @param filename    The name of the file being uploaded.
     * @param contentType The content type of the file being uploaded.
     * @return <b>Required</b>. The MXC URI to the uploaded content.
     *     Status code 200: The MXC URI for the uploaded content.
     *     Status code 429: This request was rate-limited.
     */
    @POST
    @Path("/upload")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    ContentUri upload(InputStream inputStream, @QueryParam("filename") String filename, @HeaderParam("Key-Type") String contentType);

    /**
     * Download content from the content repository.
     * <b>Rate-limited</b>: Yes.
     *
     * @param serverName Required. The server name from the mxc:// URI (the authoritory component)
     * @param mediaId    Required. The media ID from the mxc:// URI (the path component)
     * @return Response headers:
     * <table border="1">
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
     * <tr>
     * <td>Key-Disposition</td>
     * <td>string</td>
     * <td>The name of the file that was previously uploaded, if set.</td>
     * </tr>
     * </table>
     *     Status code 200: The content that was previously uploaded.
     *     Status code 429: This request was rate-limited.
     */
    @GET
    @Path("/download/{serverName}/{mediaId}")
    OutputStream download(@PathParam("serverName") String serverName, @PathParam("mediaId") String mediaId);

    /**
     * Download content from the content repository as a given filename.
     *
     * @param serverName Required. The server name from the mxc:// URI (the authoritory component)
     * @param mediaId    Required. The media ID from the mxc:// URI (the path component)
     * @param filename   Required. The filename to give in the Key- Disposition
     * @return Response headers:
     * <table border="1">
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
     * <tr>
     * <td>Key-Disposition</td>
     * <td>string</td>
     * <td>The name of the file that was previously uploaded, if set.</td>
     * </tr>
     * </table>
     *     Status code 200: The content that was previously uploaded.
     *     Status code 429: This request was rate-limited.
     */
    @GET
    @Path("/download/{serverName}/{mediaId}/{fileName}")
    OutputStream download(@PathParam("serverName") String serverName, @PathParam("mediaId") String mediaId,
                          @PathParam("fileName") String filename);

    /**
     * Download a thumbnail of the content from the content repository.
     *
     * @param serverName Required. The server name from the mxc:// URI (the authoritory component).
     * @param mediaId    Required. The media ID from the mxc:// URI (the path component)
     * @param width      The desired width of the thumbnail. The actual thumbnail may not match the size specified.
     * @param height     The desired height of the thumbnail. The actual thumbnail may not match the size specified.
     * @param method     The desired resizing method. One of: ["crop", "scale"]
     * @return Response headers:
     * <table border="1">
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
     *     Status code 200: The content that was previously uploaded.
     *     Status code 429: This request was rate-limited.
     */
    @GET
    @Path("/thumbnail/{serverName}/{mediaId}")
    OutputStream thumbnail(@PathParam("serverName") String serverName, @PathParam("mediaId") String mediaId,
                           @QueryParam("width") Long width, @QueryParam("height") Long height, @QueryParam("method") String method);

    /**
     * Get information about a URL for a client.
     *
     * @param url Required. The URL to get a preview of.
     * @param ts  The preferred point in time to return a preview for. The server may return a newer version if it does not have the
     *            requested version available.
     * @return Response headers:
     * <table border="1">
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
     * <td>An MXC URI to the image. Ommitted if there is no image.</td>
     * </tr>
     * </table>
     *     Status code 200: The content that was previously uploaded.
     *     Status code 429: This request was rate-limited.
     */
    @GET
    @Path("/preview_url")
    @Produces(MediaType.APPLICATION_JSON)
    Map<String, String> previewUrl(@QueryParam("url") String url, @QueryParam("ts") String ts);
}
