package utilities;

import java.util.ArrayList;
import java.util.List;

import interfaces.ObjectInterface;

public class SetUtility {
	
	public static <T extends ObjectInterface> List<T> Union(java.util.List<T> list1, List<T> list2) {
		List<T> result = new ArrayList<T>();
		for(T t: list1) {
			result.add(t);
		}
		
		for(T t: list2) {
			boolean match = false;
			for(T a: list1) {
				if(t.equals(a)) {
					match = true;
					break;
				}
				result.add(t);
			}
			
		}
		
		return result;
	}
	
	public static <T extends ObjectInterface> List<T> Intersect(List<T> list1, List<T> list2) {
		List<T> result = new ArrayList<T>();
		for(T t: list1) {
			for(T a: list2) {
				if(t.equals(a)){
					result.add(t);
				}
			}
		}
		
		return result;
	}
	
	public static <T extends ObjectInterface> List<T> Minus(java.util.List<T> list1, List<T> list2) {
		List<T> result = new ArrayList<T>();
		for(T t: list1) {
			boolean match = false;
			for(T a: list2) {
				if(t.equals(a)) {
					match = true;
					break;
				}
			}
			if(match) continue;
			result.add(t);
		}
		
		return result;
	}
}
