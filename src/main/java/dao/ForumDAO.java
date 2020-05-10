package dao;

import java.util.List;

import objects.*;

public class ForumDAO {
	public static void insert(Forum forum) throws Exception{
		SqlSessionContainer.getSession().insert("Forum.insert", forum);
		System.out.println("Forum successfully updated");
	    SqlSessionContainer.getSession().commit();
	    SqlSessionContainer.getSession().close();
	}
	
	public static void update(Forum forum) throws Exception{
		SqlSessionContainer.getSession().update("Forum.update", forum);
		System.out.println("Forum successfully updated");
	    SqlSessionContainer.getSession().commit();
	    SqlSessionContainer.getSession().close();
	}
	
	public static Forum get(int forum_id) throws Exception{
		return SqlSessionContainer.getSession().selectOne("Forum.get", forum_id);
	}
	
	
	public static List<Forum> getAll() throws Exception{
		return SqlSessionContainer.getSession().selectList("Forum.getAll");		
	}
	
	public static List<Forum> getSub(int forum_id) throws Exception{
		return SqlSessionContainer.getSession().selectList("Forum.getSub", forum_id);		
	}
}
