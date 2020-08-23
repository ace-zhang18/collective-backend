package dao;

import java.util.ArrayList;
import java.util.List;

import interfaces.DAOBase;
import objects.*;

public class UserDAO extends DAOBase{
	private static UserDAO dao_instance = null;
	
	private UserDAO() {
		
	}
	
	public static UserDAO getInstance() {
		if (dao_instance == null) {
			dao_instance = new UserDAO();
		}
		return dao_instance;
	}
}
