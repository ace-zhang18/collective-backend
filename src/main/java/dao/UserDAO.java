package dao;

import interfaces.DAOBase;
import objects_table.User;

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
	
	public User getByUsername(String username) {
		return SqlSessionContainer.getSession().selectOne("User.getByUsername", username);
	}
	
	public User getByEmail(String email) {
		return SqlSessionContainer.getSession().selectOne("User.getByEmail", email);
	}
}
