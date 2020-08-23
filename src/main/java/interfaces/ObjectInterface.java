package interfaces;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.json.JSONException;
import org.json.JSONObject;

public interface ObjectInterface {
	
	public long getId();
	
	public void setId(long id);
	
	public default String toJSON() {
		Class<?> act = this.getClass(); 

		Field[] class_fields = act.getDeclaredFields();
		Method[] class_meth = act.getDeclaredMethods();
		Method[] get_meth = new Method[class_fields.length];
		int get_index = 0;
		
		for (Method method: class_meth) {
			if(method.getName().contains("get")) {
				get_meth[get_index] = method;
				get_index++;
			}
		}
		
		String json_str = "{";
		
		int json_items = 0;
		for(Field field: class_fields) {
			if(json_items > 0) json_str += ",";
			Method target = null;
			for(Method method: get_meth) {
				if (method.getName().toLowerCase().contains(field.getName().toLowerCase())) {
					target = method;
					break;
				}
			}
			
			try {
				Object obj = target.invoke(this);
				if(obj == null) continue;
				String value = obj.toString();
				JSONObject job = null;
				Long num = null;
				try {
					job = new JSONObject(value);
				}catch(JSONException e) {

				}
				try {
					
					num = Long.parseLong(value);
				}catch(NumberFormatException e) {
					
				}
				
				if(job == null && num == null) {
					json_str += "\"" + field.getName() + "\":\"" + value + "\"";
				}else if(num == null){
					json_str += "\"" + field.getName() + "\":" + job.toString();					
				}else {
					json_str += "\"" + field.getName() + "\":" + num;
				}
				
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			json_items++;
		}

		json_str += "}";
		
		return json_str;
	}
	
	public default JSONObject toJSONObject() {
		return new JSONObject(this);
	} 
	
	public default <T extends ObjectInterface> boolean equals(T compare) {
		Class<?> act = this.getClass(); 
		Class<?> comp = compare.getClass();
		if (!compare.getClass().equals(act)) {
			return false;
		}
		
		Field[] act_fields = act.getDeclaredFields();
		Method[] act_meth = act.getDeclaredMethods();
		Method[] act_get = new Method[act_fields.length];
		int act_index = 0;
				
		for (Method method: act_meth) {
			if(method.getName().contains("get")) {
				try {
					Object obj1 = method.invoke(this);
					Object obj2 = method.invoke(compare);
					if (obj1 == null && obj2 == null) continue;
					if (obj1 == null || obj2 == null) return false;
					if (obj1.getClass() == JSONObject.class && obj2.getClass() == JSONObject.class) {
						return obj1.toString().equals(obj2.toString());
					}
					if(!obj1.equals(obj2)) return false;
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		
		return true;
	}
	

	
}
