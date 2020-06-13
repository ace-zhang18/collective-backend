package objects;

import java.util.Iterator;

import javax.ws.rs.core.StreamingOutput;

import org.json.JSONArray;

import dao.ArtworkDAO;
import dao.UserDAO;
import utilities.ImageUtility;
import utilities.JSONInterface;
import utilities.WebUtility;

public class ArtworkPreview implements JSONInterface{
	StreamingOutput stream;
	String title;
	String owners;
	
	public ArtworkPreview (long art_id, int thumb_size) {
		Artwork a = ArtworkDAO.get(art_id);
		title = a.getTitle();
		String path = ImageUtility.getImagePath(a, thumb_size);
		owners = "";
		try {
			stream = WebUtility.getFileStream(path);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JSONArray ar = a.getOwnerAsJSONObject().getJSONArray("users");
		long[] owner_ids = new long[ar.length()];
		for(int x = 0; x < ar.length(); x++) {
			owner_ids[x] = ar.getLong(x);
		}
		
		System.out.println(owner_ids[0]);
		
		User u = UserDAO.get(owner_ids[0]);
		owners += u.getUsername();
		
		for(int x = 1; x < owner_ids.length; x++) {
			owners += ", ";
			u = UserDAO.get(owner_ids[x]);
			owners += u.getUsername();
		}
		System.out.println(u.getUsername());
	}
}
