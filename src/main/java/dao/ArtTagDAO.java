package dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

import objects.ArtTag;
import utilities.DAOBase;

public class ArtTagDAO extends DAOBase{
	private static ArtTagDAO dao_instance;
	
	private ArtTagDAO() {
		
	}
	
	public ArtTag getByName(String name){
		return SqlSessionContainer.getSession().selectOne("ArtTag.getByName", name);
	}
	
	public void writeToDBFromFile() {
		File file = new File("C:\\collective-backend\\data\\art_tags.txt"); 
		Scanner sc = null;
		try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Stack<ArtTag> st = new Stack<ArtTag>();
        
	    while (sc.hasNextLine()) {
	        String line = sc.nextLine();
	        String tag = "";
	        int level = 0;

	        while(line.substring(0, 1).equals("-")) {
	        	line = line.substring(1);
	        	level++;
	        }
	        
	        while(level + 1 <= st.size())
	        	st.pop();
	        ArtTag at = new ArtTag();
	        at.setName(line);
	        if(st.size() > 0) {
	        	ArtTag up = st.peek();
	        	long up_id = up.getId();
	        	at.setParent(up_id);
	        }
	        long at_id = ArtTagDAO.getInstance().insertNew(at);
	        at = ArtTagDAO.getInstance().get(at_id);
	        st.push(at);
	    }
	}
	
	public static ArtTagDAO getInstance() {
		if (dao_instance == null) {
			dao_instance = new ArtTagDAO();
		}
		return dao_instance;
	}
}
