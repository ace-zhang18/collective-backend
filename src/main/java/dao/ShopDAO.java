package dao;

import java.util.List;

import interfaces.DAOBase;

public class ShopDAO extends DAOBase{
	private static ShopDAO dao_instance;

	private ShopDAO() {

	}

	public static ShopDAO getInstance() {
		if (dao_instance == null) {
			dao_instance = new ShopDAO();
		}
		return dao_instance;
	}
	
	public List<Object> getByCategory(long cat){
		return SqlSessionContainer.getSession().selectList("Shop.getByCategory", cat);	
	}
}
