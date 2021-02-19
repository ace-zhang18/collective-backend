package interfaces;

import java.util.ArrayList;
import java.util.List;

import dao.SqlSessionContainer;

public abstract class DAOBase {	
	public <T extends ObjectInterface> long insertNew(T obj){
		obj.setId(generateID());
		SqlSessionContainer.getSession().insert(getObjName() + ".insert", obj);
		System.out.println(getObjName() + " successfully inserted");
	    SqlSessionContainer.getSession().commit();
	    return obj.getId();
	}
	
	public <T extends ObjectInterface> void insert(T obj){
		SqlSessionContainer.getSession().insert(getObjName() + ".insert", obj);
		System.out.println(getObjName() + " successfully inserted");
	    SqlSessionContainer.getSession().commit();
	}
	
	public <T extends ObjectInterface> void update(T obj){
		SqlSessionContainer.getSession().insert(getObjName() + ".update", obj);
		System.out.println(getObjName() + " successfully updated");
	    SqlSessionContainer.getSession().commit();
	}
	
	public <T extends ObjectInterface> void delete(long id) {
		SqlSessionContainer.getSession().delete(getObjName() + ".delete", id);
		System.out.println(getObjName() + " successfully delete");
		SqlSessionContainer.getSession().commit();
	}
	
	public <T extends ObjectInterface> T get(long id){
		return SqlSessionContainer.getSession().selectOne(getObjName() + ".get", id);
	}
	
	public <T extends ObjectInterface> List<T> getSet(long[] ids){
		if(ids.length > 0) {
			return SqlSessionContainer.getSession().selectList(getObjName() + ".getSet", ids);		
		}else {
			return new ArrayList<T>();
		}
	}
	
	public <T extends ObjectInterface> List<T> getAll(){
		return SqlSessionContainer.getSession().selectList(getObjName() + ".getAll");		
	}
	
	public <T extends ObjectInterface> String getObjName() {
		String full_name = this.getClass().getSimpleName();
		String name = full_name.substring(0, full_name.indexOf("DAO"));
		return name;
	}
	
	public <T extends ObjectInterface> long generateID(){
		ArrayList<T> list = (ArrayList)getAll();
		long[] ids = new long[list.size()];
		for(int i = 0; i < list.size(); i++) {
			ids[i] = list.get(i).getId();
		}
		long counter = 100;
		for (long i: ids) {
			if (counter != i) {
				break;
			}
			counter++;
		}
		return counter;
	}
}
