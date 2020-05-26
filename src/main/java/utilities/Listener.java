package utilities;

import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.server.monitoring.ApplicationEvent;
import org.glassfish.jersey.server.monitoring.ApplicationEventListener;
import org.glassfish.jersey.server.monitoring.RequestEvent;
import org.glassfish.jersey.server.monitoring.RequestEventListener;

@Provider
public class Listener implements ApplicationEventListener{
	
	@Override
	public void onEvent(ApplicationEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RequestEventListener onRequest(RequestEvent requestEvent) {
		// TODO Auto-generated method stub
		return null;
	}
}