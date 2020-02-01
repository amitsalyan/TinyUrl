package com.tiny.services.server;

import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.tiny.sessionbeans.APMSL;


public class ServerApi{
	
	@GET
	@Path("/serverdetails")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, String> serverdetails() throws NamingException {
		APMSL apmSl = (APMSL) new InitialContext().lookup("java:app/TinyUrl/APMSLBean!com.aldera.sessionbeans.APMSL");
		Map<String, String> response = apmSl.serverdetails();
		return  response;
	}
	
}
