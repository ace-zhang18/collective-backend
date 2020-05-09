package dao;

import java.util.List;

import objects.*;

public class ArtworkDAO {
	public static void insert(Artwork art) throws Exception{
		SqlSessionContainer.getSession().insert("Artwork.insert", art);
		System.out.println("Artwork successfully updated");
	    SqlSessionContainer.getSession().commit();
	    SqlSessionContainer.getSession().close();
	}
	
	public static void update(Artwork art) throws Exception{
		SqlSessionContainer.getSession().update("Artwork.update", art);
		System.out.println("Artwork successfully updated");
	    SqlSessionContainer.getSession().commit();
	    SqlSessionContainer.getSession().close();
	}
	
	public static Artwork get(int art_id) throws Exception{
		return SqlSessionContainer.getSession().selectOne("Artwork.get", art_id);
	}
	
	
	public static List<Artwork> getAll() throws Exception{
		return SqlSessionContainer.getSession().selectList("Artwork.getAll");		
	}
}
