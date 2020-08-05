package utilities;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.json.JSONException;
import org.json.JSONObject;

import objects.User;

public interface ObjectInterface {	
	public default String toJSON() {
		Class<?> act = this.getClass(); 

		Field[] class_fields = act.getDeclaredFields();
		Method[] class_meth = act.getDeclaredMethods();
		Method[] get_meth = new Method[class_fields.length];
		int get_index = 0;
		
		for (Method method: class_meth) {
			if(method.getName().contains("get") && !method.getName().contains("AsJSONObject")) {
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

	public long getId();
	
	public void setId(long id);
}
