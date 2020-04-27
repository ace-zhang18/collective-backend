package dao;

import java.util.ArrayList;
import java.util.List;

import objects.*;

public class PostDAO {
	public static void insert(Post post) throws Exception{
		SqlSessionContainer.getSession().insert("Post.insert", post);
		System.out.println("Post successfully updated");
	    SqlSessionContainer.getSession().commit();
	    SqlSessionContainer.getSession().close();
	}
	
	public static void update(Post post) throws Exception{
		SqlSessionContainer.getSession().update("Post.update", post);
		System.out.println("Post successfully updated");
	    SqlSessionContainer.getSession().commit();
	    SqlSessionContainer.getSession().close();
	}
	
	public static Post get(int post_id) throws Exception{
		return SqlSessionContainer.getSession().selectOne("Post.get", post_id);
	}
	
	
	public static List<Post> getAll() throws Exception{
		return SqlSessionContainer.getSession().selectList("Post.getAll");		
	}
}
