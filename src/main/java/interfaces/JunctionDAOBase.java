package interfaces;

import java.util.ArrayList;
import java.util.List;

import dao.SqlSessionContainer;

public abstract class JunctionDAOBase {	
	public <T extends ObjectInterface> void insert(T obj){
		SqlSessionContainer.getSession().insert(getObjName() + ".insert", obj);
		System.out.println(getObjName() + " successfully inserted");
	    SqlSessionContainer.getSession().commit();
	}
	
	public <T extends ObjectInterface> void delete(T obj) {
		SqlSessionContainer.getSession().delete(getObjName() + ".delete", obj);
		System.out.println(getObjName() + " successfully delete");
		SqlSessionContainer.getSession().commit();
	}
	
	public <T extends ObjectInterface> List<T> getByComponent(long id, int part){
		return SqlSessionContainer.getSession().selectList(getObjName() + ".getBy" + getObjComponent(part), id);		
	}
		
	public <T extends ObjectInterface> List<T> getAll(){
		return SqlSessionContainer.getSession().selectList(getObjName() + ".getAll");		
	}
	
	public <T extends ObjectInterface> String[] getObjComponent(int index) {
        char[] charArray = getObjName().toCharArray();
        ArrayList<Integer> upArr = new ArrayList<Integer>();
        
        for(int i=0; i < charArray.length; i++){          
            if(Character.isUpperCase( charArray[i] )) {
            	upArr.add(i);
            }
        }
		
        int[] up = new int[upArr.size()];
        for(int i=0; i < up.length; i++) {
        	up[i] = upArr.get(i);
        }
        
        String[] comps = new String[up.length];
        for(int i=0; i < up.length; i++) {
        	if(i < up.length-1) {
        		comps[i] = getObjName().substring(up[i], up[i+1]);
        	}else {
        		comps[i] = getObjName().substring(up[i]);
        	}
        }
        
        return comps;
	}
	
	public <T extends ObjectInterface> String getObjName() {
		String full_name = this.getClass().getSimpleName();
		String name = full_name.substring(0, full_name.indexOf("DAO"));
		return name;
	}
}
