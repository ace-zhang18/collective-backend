package utilities;

import java.util.List;

import dao.SqlSessionContainer;

//the template class for writing a Database 
public abstract class TemplateDAO{

    public static String getObjectName() {
        String classname =  TemplateDAO.class.getName();
        String[] tokens = classname.split("\\.");
    	return tokens[tokens.length-1];
    }
	
	public static <T> void insert(T obj){	
		SqlSessionContainer.getSession().insert(getObjectName() + ".insert", obj);
		System.out.println(getObjectName() + " successfully inserted");
		SqlSessionContainer.getSession().commit();
		SqlSessionContainer.getSession().close();
	}

	public static <T> void update(T obj){
		SqlSessionContainer.getSession().update(getObjectName() + ".update", obj);
		System.out.println(getObjectName() + " successfully updated");
		SqlSessionContainer.getSession().commit();
		SqlSessionContainer.getSession().close();
	}

	public static void delete(int id) {
		SqlSessionContainer.getSession().update(getObjectName() + ".delete", id);
		System.out.println(getObjectName() + " successfully deleted");
		SqlSessionContainer.getSession().commit();
		SqlSessionContainer.getSession().close();
	}
	
	public static <T> T get(int id){
		return SqlSessionContainer.getSession().selectOne(getObjectName() + ".get", id);
	}

	public static <T> List<T> getAll(){
		return (List<T>) SqlSessionContainer.getSession().selectList(getObjectName() + ".getAll");		
	}
}
