package geek.ma1uta.matrix.rest.client.api;

import geek.ma1uta.matrix.rest.client.model.content.ContentUri;

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

@Path("/_matrix/media/r0")
public interface ContentApi {

    interface Method {
        String CROP = "crop";
        String SCALE = "scale";
    }

    @POST
    @Path("/upload")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    ContentUri upload(InputStream inputStream, @QueryParam("filename") String filename, @HeaderParam("Content-Type") String contentType);

    @GET
    @Path("/download/{serverName}/{mediaId}")
    OutputStream download(@PathParam("serverName") String serverName, @PathParam("mediaId") String mediaId);

    @GET
    @Path("/download/{serverName}/{mediaId}/{fileName}")
    OutputStream download(@PathParam("serverName") String serverName, @PathParam("mediaId") String mediaId,
                          @PathParam("fileName") String filename);

    @GET
    @Path("/thumbnail/{serverName}/{mediaId}")
    OutputStream thumbnail(@PathParam("serverName") String serverName, @PathParam("mediaId") String mediaId,
                           @QueryParam("width") Long width, @QueryParam("height") Long height, @QueryParam("method") String method);

    @GET
    @Path("/preview_url")
    @Produces(MediaType.APPLICATION_JSON)
    Map<String, String> previewUrl(@QueryParam("url") String url, @QueryParam("ts") String ts);
}
