package dao;

import java.util.List;

import interfaces.JunctionDAOBase;
import interfaces.ObjectInterface;
import objects_table.ShopOwner;

public class ShopOwnerDAO extends JunctionDAOBase{
	private static ShopOwnerDAO dao_instance;

	private ShopOwnerDAO() {

	}

	public static ShopOwnerDAO getInstance() {
		if (dao_instance == null) {
			dao_instance = new ShopOwnerDAO();
		}
		return dao_instance;
	}
	
	public void delete(ShopOwner o){	
		SqlSessionContainer.getSession().delete("ShopOwner.delete", o);
		System.out.println("ShopOwner successfully delete");
		SqlSessionContainer.getSession().commit();
	}
}
