package utilities;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import dao.ArtworkDAO;
import dao.ArtworkUserDAO;
import dao.CategoryDAO;
import dao.ReactionDAO;
import dao.ReplyDAO;
import dao.SqlSessionContainer;
import dao.UserDAO;
import interfaces.Mapper;
import objects_misc.ReactionCount;
import objects_table.Announcement;
import objects_table.Artwork;
import objects_table.ArtworkUser;
import objects_table.Category;
import objects_table.Reaction;
import objects_table.Reply;
import objects_table.User;

public class CollectiveUtility {

	//Assemblers
	public static JSONObject assemble(Announcement a) {
		JSONObject jo = new JSONObject();
		try {
			jo = a.toJSONObject();
			System.out.println(jo.toString());
			User u = UserDAO.getInstance().get(a.getAuthor());
			jo.put("author", u.getUsername());
			
			JSONArray reac_jos = getPostReactionCounts("announcements", a.getId());
			jo.put("reactions", reac_jos);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jo;
	}
	
	public static JSONObject assemble(Artwork a) {
		JSONObject jo = new JSONObject();
		try {
			jo = a.toJSONObject();

			ArrayList<ArtworkUser> aul = (ArrayList)ArtworkUserDAO.getInstance().getByComponent(a.getId(), 0);
			User sub = UserDAO.getInstance().get(a.getSubmitter());
			jo.put("submitter", sub.getUsername());
			
			JSONArray alar = new JSONArray();
			for(ArtworkUser au: aul) {
				User u = UserDAO.getInstance().get(au.getUser());
				alar.put(u.getUsername());
			}
			jo.put("owners", alar);

			Category cat = (Category)CategoryDAO.getInstance().get(a.getCategory());
			jo.put("category", cat.getName());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jo;
	}

	public static JSONObject assemble(Reaction em) {
		JSONObject jo = new JSONObject();
		try {
			jo = em.toJSONObject();
			long uid = em.getOwner();
			if(uid == 0) { //this reaction is owned by a specific user
				User u = UserDAO.getInstance().get(em.getOwner());
				jo.put("owner", u.getUsername());
			}else {
				jo.put("owner", "public");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jo;
	}
	
	private static JSONObject assemble(Reply re) {
		JSONObject jo = new JSONObject();
		try {
			jo = re.toJSONObject();
			User u = UserDAO.getInstance().get(re.getAuthor());
			jo.put("owner", u.getUsername());
			
			jo.put("reactions", getPostReactionCounts("replies", re.getId()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jo;
	}
	
	public static <T> List<JSONObject> assemble(List<T> list) {
		List<JSONObject> jl = new ArrayList<JSONObject>();
		Class cls = list.get(0).getClass();
		
		for(Object obj : list) {
			if(cls.getSimpleName().equalsIgnoreCase("Announcement"))
				jl.add(assemble((Announcement)obj));
			else if(cls.getSimpleName().equalsIgnoreCase("Artwork"))
				jl.add(assemble((Artwork)obj));
			else if(cls.getSimpleName().equalsIgnoreCase("Reaction")) 
				jl.add(assemble((Reaction)obj));
			else if(cls.getSimpleName().equalsIgnoreCase("Reply")) 
				jl.add(assemble((Reply)obj));
		}
		
		return jl;
	}

	//Action Functions
	
	public static void favorite(boolean favorite, String source, int source_id, int user_id) {
		
	}
	
	//metadata retrievers
	public static File[] getFiles(String src) {
		File dir = new File(src);
		File[] directoryListing = dir.listFiles();
		return directoryListing;
	}
	
	public static ArrayList<Reply> getPostReplies(String source, long id) {
		long[] ids = SqlSessionContainer.getSession().getMapper(Mapper.class).getReplies(source, id);
		ArrayList<Reply> replies = (ArrayList)ReplyDAO.getInstance().getSet(ids);
		return replies;
	}
	
	public static ArrayList<Reaction> getAvailableReactions(long uid){		
		ArrayList<Reaction> reacts = new ArrayList<Reaction>();
		HashSet<Reaction> reacts_hash = new HashSet<Reaction>();

		String p_query = "SELECT id FROM reactions WHERE owner = 0";
		
		long[] pub_ids = SqlSessionContainer.getSession().getMapper(Mapper.class).executeImmediate(p_query);

		ArrayList<Reaction> pub_reacts = (ArrayList)ReactionDAO.getInstance().getSet(pub_ids);
		
		for(Reaction r: pub_reacts) {
			reacts_hash.add(r);
		}

		String o_query = "SELECT id FROM reactions WHERE owner = " + uid;
		
		long[] own_ids = SqlSessionContainer.getSession().getMapper(Mapper.class).executeImmediate(o_query);

		ArrayList<Reaction> own_reacts = (ArrayList)ReactionDAO.getInstance().getSet(pub_ids);
		
		for(Reaction r: own_reacts) {
			reacts_hash.add(r);
		}
		
		String g_query = "SELECT r.id FROM reactions r JOIN users u ON u.id = r.owner WHERE u.id = " + uid + "\r\n" + 
				"UNION\r\n" + 
				"SELECT r.id FROM reactions r JOIN group_reactions gr ON r.id = gr.reaction JOIN \"groups\" g ON gr.group = g.id JOIN group_users gu ON gu.group = g.id JOIN users ON gu.user = users.id WHERE users.id = " + uid;
		
		long[] group_ids = SqlSessionContainer.getSession().getMapper(Mapper.class).executeImmediate(o_query);
		
		ArrayList<Reaction> group_reacts = (ArrayList)ReactionDAO.getInstance().getSet(group_ids);
		
		for(Reaction r: group_reacts) {
			reacts_hash.add(r);
		}
		
		for(Reaction r: reacts_hash) {
			reacts.add(r);
		}
		
		return reacts;
	}
	
	public static ArrayList<Reaction> getLockedReactions(long uid){
		HashSet<Reaction> reacts_hash = new HashSet<Reaction>();
		
		String lock_query = "SELECT gr.reaction FROM \"groups\" g JOIN group_users gu ON g.id = gu.group JOIN group_reactions gr ON gr.group = g.id WHERE g.is_private = false and gu.user <> " + uid;
		
		long[] lock_ids = SqlSessionContainer.getSession().getMapper(Mapper.class).executeImmediate(lock_query);

		ArrayList<Reaction> lock_reacts = (ArrayList)ReactionDAO.getInstance().getSet(lock_ids);

		return lock_reacts;
	}
	
	public static JSONArray getPostReactionCounts(String source, long id) {
		ReactionCount[] counts = SqlSessionContainer.getSession().getMapper(Mapper.class).getPostReactionCounts(source.toLowerCase(), id);
		JSONArray reac_jos = new JSONArray();
		for(ReactionCount rc: counts) {
			JSONObject rjo = new JSONObject();
			Reaction r = ReactionDAO.getInstance().get(rc.getReaction());
			rjo.put("id", r.getId());
			rjo.put("url", r.getUrl());
			rjo.put("count", rc.getCount());
			reac_jos.put(rjo);
		}
		return reac_jos;
	}

}
