package utilities;

import java.util.List;

import dao.SqlSessionContainer;
import objects.Artwork;

//the template class for writing a Database 
public abstract class TemplateDAO {
	protected static Class beanType = null;
	
	public static <T> void insert(T bean){
		beanType = T.class;
		
		SqlSessionContainer.getSession().insert(beanType.getSimpleName() + ".insert", bean);
		System.out.println("Artwork successfully updated");
		SqlSessionContainer.getSession().commit();
		SqlSessionContainer.getSession().close();
	}

	public static <T> void update(T bean){
		SqlSessionContainer.getSession().update(beanType.getSimpleName() + ".update", bean);
		System.out.println("Artwork successfully updated");
		SqlSessionContainer.getSession().commit();
		SqlSessionContainer.getSession().close();
	}

	public static Artwork get(int art_id){
		return SqlSessionContainer.getSession().selectOne("Artwork.get", art_id);
	}

	public static Artwork getByTitle(String title){
		return SqlSessionContainer.getSession().selectOne("Artwork.getByTitle", title);
	}

	public static <T> List<T> getAll(){
		return (List<T>) SqlSessionContainer.getSession().selectList(T.class.getSimpleName() + ".getAll");		
	}


}
