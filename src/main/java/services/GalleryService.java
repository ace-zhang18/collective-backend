package services;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
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

		return Response.status(200).entity(a.toJSONObject().toString()).build();
	}
	
	@POST
	@Path("/search/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response searchGallery(String query, @PathParam("id") String id) {
		System.out.println(query + ", " + id);
		long gallery_id = Long.parseLong(id);
		Gallery g = null;
		try {
			g = GalleryDAO.getInstance().get(gallery_id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] criteria = query.split(" ");
		long[] art = g.getCollection();
		for(int i = 0; i < art.length; i++) {
			for(String s: criteria) {
				long tag = Long.parseLong(s);
				
			}
		}

		return Response.status(200).entity(query).build();
	}
}