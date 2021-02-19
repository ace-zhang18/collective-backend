package dao;

import interfaces.DAOBase;

public class GroupDAO extends DAOBase{
	private static GroupDAO dao_instance = null;
	
	private GroupDAO() {
		
	}
	
	public static GroupDAO getInstance() {
		if (dao_instance == null) {
			dao_instance = new GroupDAO();
		}
		return dao_instance;
	}
}
