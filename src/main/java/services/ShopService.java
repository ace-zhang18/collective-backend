package services;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;

import dao.ArtworkDAO;
import dao.CategoryDAO;
import dao.ShopDAO;
import dao.ShopOwnerDAO;
import dao.UserDAO;
import objects_table.Artwork;
import objects_table.Category;
import objects_table.Shop;
import objects_table.ShopOwner;
import objects_table.User;
import utilities.ImageUtility;
import utilities.JSONUtility;
import utilities.WebUtility;

@Path("shops")
public class ShopService {	
	
	@GET
	@Path("/categories")
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response getCategories() {
		ArrayList<Category> list = (ArrayList)CategoryDAO.getInstance().getAll();
		ArrayList<JSONObject> converted = new ArrayList<JSONObject>();
		long[] ids = new long[list.size() * 2];
		
		for(int i = 0; i < list.size(); i++) {
			ids[i*2] = list.get(i).getCover();
			ids[i*2+1] = list.get(i).getSymbol();
		}
		
		ArrayList<Artwork> art = new ArrayList<Artwork>();
		art = (ArrayList)ArtworkDAO.getInstance().getSet(ids);
		
		for(Category c: list) {
			JSONObject jo = c.toJSONObject();
			for(Artwork a: art) {
				if(c.getCover() == a.getId()){
					JSONObject ja = new JSONObject();
					ja.put("id", a.getId());
					ja.put("file_type", a.getFile_type());
					jo.put("cover", ja);
				}
				if(c.getSymbol() == a.getId()) {
					JSONObject js = new JSONObject();
					js.put("id", a.getId());
					js.put("file_type", a.getFile_type());
					jo.put("symbol", js);
				}
			}
			converted.add(jo);
		}
		
		return Response.status(200).entity(JSONUtility.ToJSONArray(converted).toString()).build();
	}
	
	@GET
	@Path("/categories/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response getShopCat(@PathParam("id") String id_str) {
		long id = Long.parseLong(id_str);
		ArrayList<Shop> list = (ArrayList)ShopDAO.getInstance().getByCategory(id);
		ArrayList<JSONObject> converted = new ArrayList<JSONObject>();
		for(Shop s: list) {
			JSONObject jo = s.toJSONObject();
			long cover_id = s.getCover();
			Artwork a = ArtworkDAO.getInstance().get(cover_id);
			jo.put("cover", a.getUrl());
			JSONArray own = new JSONArray();
			ArrayList<ShopOwner> sol = (ArrayList)ShopOwnerDAO.getInstance().getByComponent(s.getId(),0);
			for(ShopOwner so : sol) {
				User u = (User)UserDAO.getInstance().get(so.getOwner());
				own.put(u.getUsername());
			}
			jo.put("owners", own);
			converted.add(jo);
		}
		return Response.status(200).entity(JSONUtility.ToJSONArray(converted).toString()).build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response getShop(@PathParam("id") String id_str) {
		long id = Long.parseLong(id_str);
		Shop s = ShopDAO.getInstance().get(id);

		JSONObject jo = s.toJSONObject();
		long cover_id = s.getCover();
		Artwork a = ArtworkDAO.getInstance().get(cover_id);
		jo.put("cover", a.getUrl());
		JSONArray own = new JSONArray();
		ArrayList<ShopOwner> sol = (ArrayList)ShopOwnerDAO.getInstance().getByComponent(s.getId(), 0);
		for(ShopOwner so : sol) {
			User u = (User)UserDAO.getInstance().get(so.getOwner());
			own.put(u.getUsername());
		}
		jo.put("owners", own);

		return Response.status(200).entity(jo.toString()).build();
	}
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response getAllShop() {
		ArrayList<Shop> list = (ArrayList)ShopDAO.getInstance().getAll();
		ArrayList<JSONObject> converted = new ArrayList<JSONObject>();
		for(Shop s: list) {
			JSONObject jo = s.toJSONObject();
			long cover_id = s.getCover();
			Artwork a = ArtworkDAO.getInstance().get(cover_id);
			jo.put("cover", a.getUrl());
			JSONArray own = new JSONArray();
			ArrayList<ShopOwner> sol = (ArrayList)ShopOwnerDAO.getInstance().getByComponent(s.getId(), 0);
			for(ShopOwner so : sol) {
				User u = (User)UserDAO.getInstance().get(so.getOwner());
				own.put(u.getUsername());
			}
			jo.put("owners", own);
			converted.add(jo);
		}
		return Response.status(200).entity(JSONUtility.ToJSONArray(converted).toString()).build();
	}
	

}