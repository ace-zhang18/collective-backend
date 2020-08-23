package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import interfaces.DAOBase;
import objects.ArtTag;
import objects.Artwork;
import utilities.SetUtility;

public class ArtworkDAO extends DAOBase{
	private static ArtworkDAO dao_instance;
	
	private ArtworkDAO() {
		
	}
	
	public Artwork getByTitle(String title){
		return SqlSessionContainer.getSession().selectOne("Artwork.getByTitle", title);
	}
	
	
	public List<Artwork> getByTag(long[] tags) {
		int in_size = 0, not_size = 0;
		for(long tag: tags) {
			if (tag > 0){
				in_size++;
			}else {
				not_size++;
			}
		}
		
		long[] in = new long[in_size];
		long[] not = new long[not_size];
		in_size = 0;
		not_size = 0;
		
		for(long tag: tags) {
			if (tag > 0){
				in[in_size] = tag;
				in_size++;
			}else {
				not[not_size] = -tag;
				not_size++;
			}
		}
		
		List<Artwork> in_set = SqlSessionContainer.getSession().selectList("Artwork.getByTag", in);
		List<Artwork> not_set = SqlSessionContainer.getSession().selectList("Artwork.getByNotTag", not);
		List<Artwork> result = SetUtility.Intersect(in_set, not_set);
		
		return result;
		
	}
	
	public static ArrayList<Artwork> search(String query){
		String[] toks = query.split(" ");
		HashMap<String, Object> params = new HashMap<String, Object>();
		ArrayList<String> tags = new ArrayList<String>();
		ArrayList<String> ntags = new ArrayList<String>();
		ArrayList<String> users = new ArrayList<String>();
		ArrayList<String> nusers = new ArrayList<String>();
		ArrayList<String> groups = new ArrayList<String>();
		ArrayList<String> ngroups = new ArrayList<String>();
		
		
		for(String tok: toks) {
			if(tok.indexOf(':') > 0) {
				String[] bits = tok.split(":", 2);
				boolean not = false;
				if(bits[0].charAt(0) == '-') {
					not = true;
					bits[0] = bits[0].substring(1);
				}
				if(bits[0].equals("title")) {
					params.put("title", bits[1]);
				}
			}
		}
		
		
		
		return null;
	}
	
	public static ArtworkDAO getInstance() {
		if (dao_instance == null) {
			dao_instance = new ArtworkDAO();
		}
		return dao_instance;
	}
}
