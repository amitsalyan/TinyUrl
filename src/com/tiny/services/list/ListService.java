package com.tiny.services.list;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
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
		apiList.put("GET: /api/list", "{produces:'APPLICATION_JSON',message:'Lists all available API'}");
		apiList.put("GET: api/serverdetails", "{produces:'APPLICATION_JSON',messsge:'Return JVM freeMemory, maxMemory and totalMemory.'}");
		apiList.put("GET: api/tiny/genTinyLink", "{produces:'APPLICATION_JSON', message:'Return Expected JSON format for the corresponding POST.'}");
		apiList.put("POST: api/tiny/genTinyLink", "{consumes:'APPLICATION_JSON',produces:'APPLICATION_JSON',message:'Generates the Tiny URL.'}");		
		return  apiList;
	}
}
