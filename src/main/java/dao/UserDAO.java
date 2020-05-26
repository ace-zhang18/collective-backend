package dao;

import java.util.ArrayList;
import java.util.List;

import objects.*;

public class UserDAO {
	public static void insert(User user){
		SqlSessionContainer.getSession().insert("User.insert", user);
		System.out.println("user successfully updated");
	    SqlSessionContainer.getSession().commit();
	    SqlSessionContainer.getSession().close();
	}
	
	public static void update(User user){
		SqlSessionContainer.getSession().update("User.update", user);
		System.out.println("user successfully updated");
	    SqlSessionContainer.getSession().commit();
	    SqlSessionContainer.getSession().close();
	}
	
	public static User get(int user_id){
		return SqlSessionContainer.getSession().selectOne("User.get", user_id);
	}
	
	
	public static List<User> getAll(){
		return SqlSessionContainer.getSession().selectList("User.getAll");	
	}
}
