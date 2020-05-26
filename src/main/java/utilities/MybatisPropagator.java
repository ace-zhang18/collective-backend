package utilities;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class MybatisPropagator {
	public static void main (String args[]) {
		File folder = new File("src/main/java/objects");

		ArrayList<String> classnames = new ArrayList<String>();
		for (File fileEntry : folder.listFiles()) {
			String classtitle = fileEntry.getName();
			String[] tokens = classtitle.split("\\.");
			String classpath = "objects." + tokens[tokens.length-2];
			classnames.add(classpath);
		}
		
		try {
			Class cls = Class.forName(classnames.get(0));
			System.out.println(cls.getCanonicalName());

			// returns the array of Field objects representing the public fields
			Field[] f = cls.getDeclaredFields();
			System.out.println(f.length);
			for (int i = 0; i < f.length; i++) {
				System.out.println(f[i]);
			}
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}
	}
}
