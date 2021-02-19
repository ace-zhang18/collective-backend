package dao;

import java.util.ArrayList;
import java.util.List;

import interfaces.DAOBase;
import interfaces.Mapper;
import objects_table.Artwork;
import utilities.ArrayTree;

public class ReactionDAO extends DAOBase{
	private static ReactionDAO dao_instance;

	private ReactionDAO() {

	}
	
	public static ReactionDAO getInstance() {
		if (dao_instance == null) {
			dao_instance = new ReactionDAO();
		}
		return dao_instance;
	}
}
