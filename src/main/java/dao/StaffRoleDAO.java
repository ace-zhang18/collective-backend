package dao;

import java.util.ArrayList;
import java.util.List;

import objects.*;

public class StaffRoleDAO {
	public static void insert(StaffRole role) throws Exception{
		SqlSessionContainer.getSession().insert("StaffRole.insert", role);
		System.out.println("Role successfully updated");
	    SqlSessionContainer.getSession().commit();
	    SqlSessionContainer.getSession().close();
	}
	
	public static void update(StaffRole role) throws Exception{
		SqlSessionContainer.getSession().update("StaffRole.update", role);
		System.out.println("Role successfully updated");
	    SqlSessionContainer.getSession().commit();
	    SqlSessionContainer.getSession().close();
	}
	
	public static StaffRole get(int role_id) throws Exception{
		return SqlSessionContainer.getSession().selectOne("StaffRole.get", role_id);
	}
	
	
	public static List<StaffRole> getAll() throws Exception{
		return SqlSessionContainer.getSession().selectList("StaffRole.getAll");		
	}
}
