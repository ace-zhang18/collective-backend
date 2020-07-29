package dao;

import java.util.List;

import objects.Post;
import utilities.DAOBase;

public class PostDAO extends DAOBase {
	private static PostDAO dao_instance = null;
	
	public PostDAO() {
		
	}
	
	public List<Post> getThreadPosts(int thread){
		return SqlSessionContainer.getSession().selectList("Post.getThreadPosts", thread);		
	}
	
	public static PostDAO getInstance() {
		if (dao_instance == null) {
			dao_instance = new PostDAO();
		}
		return (PostDAO) dao_instance;
	}
}
