package dao;

import interfaces.DAOBase;

public class CategoryDAO extends DAOBase{
	private static CategoryDAO dao_instance;

	private CategoryDAO() {

	}

	public static CategoryDAO getInstance() {
		if (dao_instance == null) {
			dao_instance = new CategoryDAO();
		}
		return dao_instance;
	}
}
