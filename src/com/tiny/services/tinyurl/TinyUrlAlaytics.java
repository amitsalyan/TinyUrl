package com.tiny.services.tinyurl;

import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.tiny.services.RESTApi;
import com.tiny.sessionbeans.APMSL;

@Path("/tiny/analytics")
public class TinyUrlAlaytics extends RESTApi{

	
	@GET
	@Path("/usage")
	@Produces(MediaType.TEXT_PLAIN)
	public Map<String, String> usage() throws NamingException {
		APMSL apmSl = (APMSL) new InitialContext().lookup("java:app/TinyUrl/APMSLBean!com.tiny.sessionbeans.APMSL");
		Map<String, String> response = apmSl.serverdetails();
		return  response;
	}
	
	@GET
	@Path("/usage/{date}")
	@Produces(MediaType.TEXT_PLAIN)
	public Map<String, String> usageDate() throws NamingException {
		APMSL apmSl = (APMSL) new InitialContext().lookup("java:app/TinyUrl/APMSLBean!com.tiny.sessionbeans.APMSL");
		Map<String, String> response = apmSl.serverdetails();
		return  response;
	}
	
	@GET
	@Path("/usage/days/{days}")
	@Produces(MediaType.TEXT_PLAIN)
	public Map<String, String> usageDays() throws NamingException {
		APMSL apmSl = (APMSL) new InitialContext().lookup("java:app/TinyUrl/APMSLBean!com.tiny.sessionbeans.APMSL");
		Map<String, String> response = apmSl.serverdetails();
		return  response;
	}
	
	@GET
	@Path("/active")
	@Produces(MediaType.TEXT_PLAIN)
	public Map<String, String> active() throws NamingException {
		APMSL apmSl = (APMSL) new InitialContext().lookup("java:app/TinyUrl/APMSLBean!com.tiny.sessionbeans.APMSL");
		Map<String, String> response = apmSl.serverdetails();
		return  response;
	}
	
}
