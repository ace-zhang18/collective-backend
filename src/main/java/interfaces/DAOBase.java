package interfaces;

import java.util.List;

import org.glassfish.hk2.api.Self;

import dao.SqlSessionContainer;
import objects.Gallery;

public abstract class DAOBase {	
	public <T extends ObjectInterface> long insertNew(T obj){
		obj.setId(generateID());
		SqlSessionContainer.getSession().insert(getObjName() + ".insert", obj);
		System.out.println(getObjName() + " successfully inserted");
	    SqlSessionContainer.getSession().commit();
	    SqlSessionContainer.getSession().close();
	    return obj.getId();
	}
	
	public <T extends ObjectInterface> void insert(T obj){
		SqlSessionContainer.getSession().insert(getObjName() + ".insert", obj);
		System.out.println(getObjName() + " successfully inserted");
	    SqlSessionContainer.getSession().commit();
	    SqlSessionContainer.getSession().close();
	}
	
	public <T extends ObjectInterface> void update(T obj){
		SqlSessionContainer.getSession().insert(getObjName() + ".update", obj);
		System.out.println(getObjName() + " successfully updated");
	    SqlSessionContainer.getSession().commit();
	    SqlSessionContainer.getSession().close();
	}
	
	public <T extends ObjectInterface> void delete(long id) {
		SqlSessionContainer.getSession().delete(getObjName() + ".delete", id);
		System.out.println(getObjName() + " successfully delete");
		SqlSessionContainer.getSession().commit();
		SqlSessionContainer.getSession().close();
	}
	
	public <T extends ObjectInterface> T get(long id){
		return SqlSessionContainer.getSession().selectOne(getObjName() + ".get", id);
	}
	
	public <T extends ObjectInterface> List<T> getSet(long[] ids){
		return SqlSessionContainer.getSession().selectList(getObjName() + ".getSet", ids);		
	}
	
	public <T extends ObjectInterface> List<T> getAll(){
		return SqlSessionContainer.getSession().selectList(getObjName() + ".getAll");		
	}
	
	public <T extends ObjectInterface> String getObjName() {
		String full_name = this.getClass().getSimpleName();
		System.out.println(full_name);
		String name = full_name.substring(0, full_name.indexOf("DAO"));
		return name;
	}
	
	public <T extends ObjectInterface> long generateID(){
		List<T> list = getAll();
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
