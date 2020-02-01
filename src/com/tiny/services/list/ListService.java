package com.tiny.services.list;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

@Path("/list")
public class ListService{
	
	/** The logger. */
	private static Logger LOGGER=Logger.getLogger(ListService.class);

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, String> ping() {
		Map<String, String> apiList = new LinkedHashMap<String, String>();
		apiList.put("/api/list", "GET: Lists all available API");
		apiList.put("/api/ping", "GET: Confirms if the server trusts the incoming request with a string response pong");
		apiList.put("api/pcp", "GET/POST:Used to post pcp data to portal, GET supplies the expected object array format, POST persists the object array");
		apiList.put("api/apm/claimSearch", "GET: Used for APM - Does a Claim Search and responds the excution time");
		apiList.put("api/apm/providerSearch", "GET: Used for APM - Does a PDS and responds the excution time.");
		apiList.put("api/apm/eligibilitySearch", "GET: Used for APM - Does a Member Eligibility Search and responds the excution time.");
		apiList.put("api/apm/serverdetails", "GET: Used for APM - Return JVM freeMemory, maxMemory and totalMemory.");
		return  apiList;
	}
}
