package dao;

import java.util.List;

import interfaces.JunctionDAOBase;
import interfaces.ObjectInterface;
import objects_table.GroupUser;

public class GroupUserDAO extends JunctionDAOBase{
	private static GroupUserDAO dao_instance;

	private GroupUserDAO() {

	}

	public static GroupUserDAO getInstance() {
		if (dao_instance == null) {
			dao_instance = new GroupUserDAO();
		}
		return dao_instance;
	}	
	
	public void delete(GroupUser o){	
		SqlSessionContainer.getSession().delete("GroupUser.delete", o);
		System.out.println("GroupUser successfully delete");
		SqlSessionContainer.getSession().commit();
	}
}
