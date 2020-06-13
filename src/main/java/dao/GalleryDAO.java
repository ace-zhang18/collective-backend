package dao;

import java.util.List;

import objects.*;

public class GalleryDAO {
	public static void insert(Gallery gallery){
		SqlSessionContainer.getSession().insert("Gallery.insert", gallery);
		System.out.println("Gallery successfully updated");
	    SqlSessionContainer.getSession().commit();
	    SqlSessionContainer.getSession().close();
	}
	
	public static void update(Gallery gallery){
		SqlSessionContainer.getSession().update("Gallery.update", gallery);
		System.out.println("Gallery successfully updated");
	    SqlSessionContainer.getSession().commit();
	    SqlSessionContainer.getSession().close();
	}
	
	public static Gallery get(long gallery_id){
		return SqlSessionContainer.getSession().selectOne("Gallery.get", gallery_id);
	}
	
	
	public static List<Gallery> getAll(){
		return SqlSessionContainer.getSession().selectList("Gallery.getAll");		
	}
}
