package dao;

import interfaces.JunctionDAOBase;
import objects_table.GroupReaction;

public class GroupReactionDAO extends JunctionDAOBase{
	private static GroupReactionDAO dao_instance;

	private GroupReactionDAO() {

	}

	public static GroupReactionDAO getInstance() {
		if (dao_instance == null) {
			dao_instance = new GroupReactionDAO();
		}
		return dao_instance;
	}

	public void delete(GroupReaction o){	
		SqlSessionContainer.getSession().delete("GroupReaction.delete", o);
		System.out.println("GroupReaction successfully delete");
		SqlSessionContainer.getSession().commit();
	}
}
