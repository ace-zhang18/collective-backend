package dao;

import interfaces.JunctionDAOBase;

public class ArtworkUserDAO extends JunctionDAOBase{
	private static ArtworkUserDAO dao_instance;

	private ArtworkUserDAO() {

	}

	public static ArtworkUserDAO getInstance() {
		if (dao_instance == null) {
			dao_instance = new ArtworkUserDAO();
		}
		return dao_instance;
	}

}
