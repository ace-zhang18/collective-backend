package dao;

import java.util.ArrayList;
import java.util.List;

import objects.*;

public class StaffRoleDAO {
	public static void insert(StaffRole role){
		SqlSessionContainer.getSession().insert("StaffRole.insert", role);
		System.out.println("Role successfully updated");
	    SqlSessionContainer.getSession().commit();
	    SqlSessionContainer.getSession().close();
	}
	
	public static void update(StaffRole role){
		SqlSessionContainer.getSession().update("StaffRole.update", role);
		System.out.println("Role successfully updated");
	    SqlSessionContainer.getSession().commit();
	    SqlSessionContainer.getSession().close();
	}
	
	public static StaffRole get(int role_id){
		return SqlSessionContainer.getSession().selectOne("StaffRole.get", role_id);
	}
	
	
	public static List<StaffRole> getAll(){
		return SqlSessionContainer.getSession().selectList("StaffRole.getAll");		
	}
}
