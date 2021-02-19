package dao;

import java.util.List;

import interfaces.DAOBase;
import objects_table.Reply;

public class StatusDAO extends DAOBase{
	private static StatusDAO dao_instance = null;
	
	private StatusDAO() {
		
	}
	
	public static StatusDAO getInstance() {
		if (dao_instance == null) {
			dao_instance = new StatusDAO();
		}
		return dao_instance;
	}
}
