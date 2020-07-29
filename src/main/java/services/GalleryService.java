package services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.web.bind.annotation.CrossOrigin;

import dao.ArtworkDAO;
import dao.GalleryDAO;
import objects.Artwork;
import objects.Gallery;
import utilities.ImageUtility;
import utilities.WebUtility;

@Path("galleries")
public class GalleryService {	
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response getGallery(@PathParam("id") String id) {
		long gallery_id = Long.parseLong(id);
		Gallery a = null;
		try {
			a = GalleryDAO.getInstance().get(gallery_id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Response.status(200).entity(a.toJSON().toString()).build();
	}
	
	@GET
	@Path("/thumbnails/{id}")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	@CrossOrigin
	public Response getThumbnails(@PathParam("id") String id) {
		long art_id = Long.parseLong(id);
		
		String path = ImageUtility.getImagePath(art_id);
		
		try {
			return Response.status(200).entity(WebUtility.getFileStream(path)).build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return Response.status(500).entity("Failure").build();
		}
	}
}