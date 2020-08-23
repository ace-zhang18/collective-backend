package dao;

import java.util.ArrayList;
import java.util.List;

import interfaces.DAOBase;
import objects.*;

public class StaffRoleDAO extends DAOBase{
	private static StaffRoleDAO dao_instance;
	
	private StaffRoleDAO() {
		
	}
	
	public static StaffRoleDAO getInstance() {
		if (dao_instance == null) {
			dao_instance = new StaffRoleDAO();
		}
		return dao_instance;
	}
}
