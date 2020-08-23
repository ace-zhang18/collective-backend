package utilities;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONObject;
import org.postgresql.geometric.PGline;
import org.postgresql.util.PGobject;

import interfaces.ObjectInterface;

public class JSONUtility {
	public static <T extends ObjectInterface> String ToJSONArray(List<T> list) {

		String json = "[";
		Iterator i = list.iterator();

		while(i.hasNext()) {
			json += ((T)i.next()).toJSONObject().toString();
			if(i.hasNext()) json += ",";
		}

		json += "]";

		return json;
	}
}
