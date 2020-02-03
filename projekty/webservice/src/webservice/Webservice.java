package webservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/webservice")
public class Webservice {
	
	@GET
	@Produces(MediaType.TEXT_XML)
	public String sayHelloXML()
	{
		String response = "?xml version = '1.0'?><hello>HelloXML</hello>";
		return response;
	}
	/*
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String sayHelloJSON()
	{
		String response = null;
		return response;
	}*/
}
