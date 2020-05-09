import java.util.ArrayList;

import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.JerseyClient;
import org.glassfish.jersey.client.JerseyInvocation;
import org.glassfish.jersey.client.JerseyWebTarget;
import org.json.JSONObject;

import dao.*;
import objects.*;

public class TestClient {
	public static void main(String[] args) {

		try {
			JerseyClient client;
			JerseyWebTarget webTarget;
			JerseyInvocation.Builder invocationBuilder;
			Response response;
			String input, output;
			int test = 1;
			switch(test)
			{
			case 0: //get Staff Role
				User test_role = UserDAO.get(1);
				
				long[] ln = {1,2,3,4};
				test_role.setStaff_roles(ln);
				
				UserDAO.update(test_role);
				
				break;
			case 1: //get All Staff Roles
				ArrayList<User> list = (ArrayList<User>) UserDAO.getAll();
				
				for(User role : list) {
					System.out.println(role);
				}
				
				break;
			}
		} catch (Exception e) {

			e.printStackTrace();

		}

	}


}

