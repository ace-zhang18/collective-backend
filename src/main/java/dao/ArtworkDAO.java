package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.jdbc.SQL;

import interfaces.DAOBase;
import interfaces.Mapper;
import interfaces.ObjectInterface;
import objects.ArtTag;
import objects.Artwork;
import utilities.ArrayTree;
import utilities.SetUtility;

public class ArtworkDAO extends DAOBase{
	private static ArtworkDAO dao_instance;

	private ArtworkDAO() {

	}

	public List<Object> getLatest() {
		return SqlSessionContainer.getSession().selectList("Artwork.getLatest");
	}
	
	public Artwork getByTitle(String title){
		return SqlSessionContainer.getSession().selectOne("Artwork.getByTitle", title);
	}
	
	public ArrayList<Artwork> doSearch(ArrayTree<String> struct){
		String query = constructQuery(struct);
		
		ArrayList<Artwork> results = (ArrayList<Artwork>) SqlSessionContainer.getSession().getMapper(Mapper.class).executeImmediate(query, new Artwork());
		
		return results;
	}

	public String constructQuery(ArrayTree<String> struct) {
		String query = "SELECT * FROM artworks WHERE id IN (\n";
		query += queryLevel(struct);
		return query += "\n)";
	}
	
	public String queryLevel(ArrayTree<String> struct) {
		String query = "";
		String search = struct.getData().trim();

		while(true) {
			boolean isOR = false;
			boolean isNOT = false;
			if(search.indexOf('~') < search.indexOf(' ')) {
				isOR = true;
			}


			String[] chunks = search.split("[~ ]", 2);
			if(chunks.length > 1) search = chunks[1].trim();
			String term = chunks[0].trim();


			if(term.charAt(0) == '-') {
				isNOT = true;
				term = term.substring(1, term.length());
			}

			if(term.matches("\\{\\d+\\}")) {
				int index = Integer.parseInt(term.trim().substring(1,term.length()-1));
				ArrayTree node = struct.getNodes().get(index-1);

				query += "(";
				query += queryLevel(node);
				query += ")";
			}else {

				if(term.indexOf(":") <= 0) {
					query += "SELECT id FROM artworks WHERE (SELECT id FROM art_tags WHERE name='"+ term +"') ";
					if(isNOT) query += "!= ";
					else query += "= ";
					query += "ANY(tags)";
				}
				String[] parts = term.split(":", 2);

				if(parts[0].equalsIgnoreCase("title")) {
					query += "SELECT id FROM artworks WHERE title ";
					if(isNOT) query += "!= ";
					else query += "= ";
					query += parts[1];
				}else if(parts[0].equalsIgnoreCase("filetype")) {
					query += "SELECT id FROM artworks WHERE file_type ";
					if(isNOT) query += "!= ";
					else query += "= ";
					query += parts[1];
				}else if(parts[0].equalsIgnoreCase("rating")) {
					String subquery = "SELECT id FROM artworks WHERE calc_score(rating)";
					if(parts[1].charAt(parts[1].length()-1) == '-') {
						subquery += "<=";
						parts[1] = parts[1].substring(0, parts[1].length()-2);
					}else {
						subquery += ">=";
					}
					subquery += parts[1];
					query += subquery;
				}else if(parts[0].equalsIgnoreCase("user")) {
					query += "SELECT * FROM artworks, LATERAL json_array_elements_text(owners->'users') AS users GROUP BY id " + 
							"HAVING (SELECT id FROM users WHERE username ";
					if(isNOT) query += "!= ";
					else query += "= ";
					query += "'" +parts[1] +"') = ANY(array_agg(groups::bigint))";
				}else if(parts[0].equalsIgnoreCase("group")) {
					query += "SELECT * FROM artworks, LATERAL json_array_elements_text(owners->'groups') AS groups GROUP BY id " + 
							"HAVING (SELECT id FROM groups WHERE name ";
					if(isNOT) query += "!= ";
					else query += "= ";
					query += "'" +parts[1] +"') = ANY(array_agg(groups::bigint))";
				}else if(parts[0].equalsIgnoreCase("before") || parts[0].equalsIgnoreCase("after")) {
					String subquery = "SELECT id FROM artworks WHERE get_latest(history)";
					if(parts[0].equalsIgnoreCase("before")) subquery += " < ";
					else subquery += " > ";
					subquery += "TO_DATE(" + parts[1] + ", 'YYYY MM DD')";
					query += subquery;
				}
			}
			if(chunks.length > 1) {
				if(isOR) query += "\nUNION\n";
				else query += "\nINTERSECT\n";
			}else {
				break;
			}
		}

		return query;
	}
	/*
			if(term.indexOf(":") <= 0) {
				query += "SELECT * FROM artworks WHERE (SELECT id FROM art_tags WHERE name='"+ term +"') = ANY(tags)";
			}



			String[] parts = term.split(":", 2);
			if(parts[0].equalsIgnoreCase("title")) {
				query += "SELECT * FROM artworks WHERE title=" + parts[1];
			}else if(parts[0].equalsIgnoreCase("filetype")) {
				query += "SELECT * FROM artworks WHERE file_type=" + parts[1];
			}else if(parts[0].equalsIgnoreCase("rating")) {
				String subquery = "SELECT * FROM artworks WHERE calc_score(rating)";
				if(parts[1].charAt(parts[1].length()-1) == '-') {
					subquery += "<=";
					parts[1] = parts[1].substring(0, parts[1].length()-2);
				}else {
					subquery += ">=";
				}
				subquery += parts[1];
				query += subquery;
			}else if(parts[0].equalsIgnoreCase("user")) {
				query += "SELECT * FROM artworks, LATERAL json_array_elements_text(owners->'users') AS users GROUP BY id " + 
						"HAVING (SELECT id FROM users WHERE username='"+ parts[1] +"') = ANY(array_agg(users::bigint))";
			}else if(parts[0].equalsIgnoreCase("group")) {
				query += "SELECT * FROM artworks, LATERAL json_array_elements_text(owners->'groups') AS groups GROUP BY id " + 
						"HAVING (SELECT id FROM groups WHERE name='"+ parts[1] +"') = ANY(array_agg(groups::bigint))";
			}else if(parts[0].equalsIgnoreCase("before") || parts[0].equalsIgnoreCase("after")) {
				String subquery = "SELECT * FROM artworks WHERE get_latest(history)";
				if(parts[0].equalsIgnoreCase("before")) subquery += '<';
				else subquery += '>';
				subquery += "TO_DATE(" + parts[1] + ", 'YYYY MM DD')";
			}
	 */


	public static ArtworkDAO getInstance() {
		if (dao_instance == null) {
			dao_instance = new ArtworkDAO();
		}
		return dao_instance;
	}
}
