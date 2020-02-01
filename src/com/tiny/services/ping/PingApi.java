package com.tiny.services.ping;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

@Path("/ping")
public class PingApi{
	
	/** The logger. */
	private static Logger LOGGER=Logger.getLogger(PingApi.class);

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String ping() {
		return  "pong";
	}
}
