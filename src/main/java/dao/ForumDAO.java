package dao;

import java.util.List;

import interfaces.DAOBase;
import objects.*;

public class ForumDAO extends DAOBase{
	private static ForumDAO dao_instance;
	
	private ForumDAO() {
		
	}
	
	public List<Forum> getSub(int forum_id){
		return SqlSessionContainer.getSession().selectList("Forum.getSub", forum_id);		
	}
	
	public static ForumDAO getInstance() {
		if (dao_instance == null) {
			dao_instance = new ForumDAO();
		}
		return dao_instance;
	}
}
