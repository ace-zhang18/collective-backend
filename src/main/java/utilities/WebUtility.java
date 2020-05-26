package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;

public class WebUtility {
	public static StreamingOutput getFileStream(String path) throws Exception {
	    return new StreamingOutput() {
			@Override
			public void write(OutputStream output) throws IOException, WebApplicationException {
				// TODO Auto-generated method stub
				Path currentRelativePath = Paths.get("");
				String s = currentRelativePath.toAbsolutePath().toString();
				System.out.println("Current relative path is: " + s);
		        FileInputStream input = new FileInputStream(path);
		        try {
		            int bytes;
		            while ((bytes = input.read()) != -1) {
		                output.write(bytes);
		            }
		        } catch (Exception e) {
		            throw new WebApplicationException(e);
		        } finally {
		            if (output != null) output.close();
		            if (input != null) input.close();
		        }
			}
	    };
	}
}
