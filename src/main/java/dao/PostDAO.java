package dao;

import java.util.ArrayList;
import java.util.List;

import objects.*;

public class PostDAO {
	public static void insert(Post post){
		SqlSessionContainer.getSession().insert("Post.insert", post);
		System.out.println("Post successfully updated");
	    SqlSessionContainer.getSession().commit();
	    SqlSessionContainer.getSession().close();
	}
	
	public static void update(Post post){
		SqlSessionContainer.getSession().update("Post.update", post);
		System.out.println("Post successfully updated");
	    SqlSessionContainer.getSession().commit();
	    SqlSessionContainer.getSession().close();
	}
	
	public static Post get(long post_id){
		return SqlSessionContainer.getSession().selectOne("Post.get", post_id);
	}
	
	public static List<Post> getThreadPosts(int thread){
		return SqlSessionContainer.getSession().selectList("Post.getThreadPosts", thread);		
	}
	
	public static List<Post> getAll(){
		return SqlSessionContainer.getSession().selectList("Post.getAll");		
	}
}
