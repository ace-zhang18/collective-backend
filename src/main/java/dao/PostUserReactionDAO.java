package dao;

import interfaces.DAOBase;
import objects_table.PostUserReaction;

public class PostUserReactionDAO extends DAOBase {
	private static PostUserReactionDAO dao_instance;

	private PostUserReactionDAO() {

	}

	public static PostUserReactionDAO getInstance() {
		if (dao_instance == null) {
			dao_instance = new PostUserReactionDAO();
		}
		return dao_instance;
	}

	public void delete(PostUserReaction o){	
		SqlSessionContainer.getSession().delete("PostUserReaction.delete", o);
		System.out.println("PostUserReaction successfully delete");
		SqlSessionContainer.getSession().commit();
	}
}
