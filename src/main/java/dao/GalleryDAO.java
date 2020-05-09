package dao;

import java.util.List;

import objects.*;

public class GalleryDAO {
	public static void insert(Gallery gallery) throws Exception{
		SqlSessionContainer.getSession().insert("Gallery.insert", gallery);
		System.out.println("Gallery successfully updated");
	    SqlSessionContainer.getSession().commit();
	    SqlSessionContainer.getSession().close();
	}
	
	public static void update(Gallery gallery) throws Exception{
		SqlSessionContainer.getSession().update("Gallery.update", gallery);
		System.out.println("Gallery successfully updated");
	    SqlSessionContainer.getSession().commit();
	    SqlSessionContainer.getSession().close();
	}
	
	public static Gallery get(int gallery_id) throws Exception{
		return SqlSessionContainer.getSession().selectOne("Gallery.get", gallery_id);
	}
	
	
	public static List<Gallery> getAll() throws Exception{
		return SqlSessionContainer.getSession().selectList("Gallery.getAll");		
	}
}
