package utilities;

import java.util.Iterator;
import java.util.List;

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
}
