package dao;

import java.util.List;
import objects.Thread;

public class ThreadDAO {
	public static void insert(Thread thread){
		SqlSessionContainer.getSession().insert("Thread.insert", thread);
		System.out.println("Thread successfully updated");
	    SqlSessionContainer.getSession().commit();
	    SqlSessionContainer.getSession().close();
	}
	
	public static void update(Thread thread){
		SqlSessionContainer.getSession().update("Thread.update", thread);
		System.out.println("Thread successfully updated");
	    SqlSessionContainer.getSession().commit();
	    SqlSessionContainer.getSession().close();
	}
	
	public static Thread get(int thread_id){
		return SqlSessionContainer.getSession().selectOne("Thread.get", thread_id);
	}
	
	
	public static List<Thread> getAll(){
		return SqlSessionContainer.getSession().selectList("Thread.getAll");		
	}
	
	public static List<Thread> getForumThreads(int forum_id){
		return SqlSessionContainer.getSession().selectList("Thread.getForumThreads", forum_id);		
	}
}
