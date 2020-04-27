package utilities;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.json.JSONObject;
import org.postgresql.geometric.PGline;
import org.postgresql.util.PGobject;

public class JSONUtility {
	public static <T extends JSONInterface> String ToJSONArray(List<T> list) {

		String json = "[";
		Iterator i = list.iterator();

		while(i.hasNext()) {
			json += ((T)i.next()).toJSON();
			if(i.hasNext()) json += ",";
		}

		json += "]";

		return json;
	}

	public static JSONObject PGtoJSON(PGobject pg) {
		JSONObject js = new JSONObject(pg.getValue());

		return js;
	}

	public static JSONObject[] PGtoJSON(PGobject[] pg) {
		JSONObject[] js = new JSONObject[pg.length];
		for(int i = 0; i < pg.length; i++) {
			js[i] = new JSONObject(pg[i].getValue());
		}

		return js;
	}

	public static PGobject JSONtoPG(JSONObject js) {
		PGobject pg = new PGobject();
		pg.setType("json");
		try {
			pg.setValue(js.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return pg;
	}

	public static PGobject[] JSONtoPG(JSONObject[] js) {
		PGobject[] pg = new PGobject[js.length];

		for(int i = 0; i < js.length; i++) {
			pg[i] = new PGobject();
			try {
				pg[i].setType("json");
				pg[i].setValue(js[i].toString());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return pg;
	}
	
	public static PGobject StringToPG(String s) {
		PGobject pg = new PGobject();
		pg.setType("json");
		try {
			pg.setValue(s);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return pg;
	}
	
	public static JSONObject[] StringToJSON(String[] s) {
		JSONObject[] js = new JSONObject[s.length];
		for(int i = 0; i < s.length; i++) {
			js[i] = new JSONObject(s[i]);
		}

		return js;
	}
	
	public static PGobject[] StringToPG(String[] s) {
		PGobject[] pg = new PGobject[s.length];

		for(int i = 0; i < s.length; i++) {
			pg[i] = new PGobject();
			try {
				pg[i].setType("json");
				pg[i].setValue(s[i]);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return pg;
	}
}
