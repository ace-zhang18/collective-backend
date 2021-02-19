package dao;

import java.util.List;

import interfaces.DAOBase;
import objects_table.Reply;

public class ReplyDAO extends DAOBase{
	private static ReplyDAO dao_instance = null;
	
	private ReplyDAO() {
		
	}
	
	public static ReplyDAO getInstance() {
		if (dao_instance == null) {
			dao_instance = new ReplyDAO();
		}
		return dao_instance;
	}
	
	public List<Reply> getAll(Reply r){
		return SqlSessionContainer.getSession().selectList("Reply.getAll", r);	
	}
}
