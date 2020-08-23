package dao;

import java.util.List;

import interfaces.DAOBase;
import objects.Artwork;
import objects.Thread;

public class ThreadDAO extends DAOBase {
	private static ThreadDAO dao_instance;
	
	private ThreadDAO() {
		
	}
	
	public static List<Thread> getForumThreads(int forum_id){
		return SqlSessionContainer.getSession().selectList("Thread.getForumThreads", forum_id);		
	}
	
	public static ThreadDAO getInstance() {
		if (dao_instance == null) {
			dao_instance = new ThreadDAO();
		}
		return dao_instance;
	}
}
