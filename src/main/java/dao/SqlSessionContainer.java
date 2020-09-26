package dao;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import interfaces.Mapper;

public class SqlSessionContainer {
	static Reader reader = null;	
	static SqlSessionFactory sqlSessionFactory;		
	static SqlSession session;
	
	private static void initSqlSession() {
		try {
			reader = Resources.getResourceAsReader("db_map/SqlMapConfig.xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader); 
	}
	
	public static SqlSession getSession() {
		if(session == null) {
			initSqlSession();
		}
		session = sqlSessionFactory.openSession();
		return session;
	}
	
	public static Mapper getMapper() {
		return getSession().getMapper(Mapper.class);
	}
}
