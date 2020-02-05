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

import com.tiny.services.RESTApi;
import com.tiny.sessionbeans.APMSL;
import com.tiny.sessionbeans.TinyUrlLocal;
import com.tiny.util.BeanServiceUtil;

@Path("/serverdetails")
public class ServerApi extends RESTApi{
	
	APMSL apmSl = BeanServiceUtil.getBean(BeanServiceUtil.APM_SL);

	@GET
	@Path("/jvm")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, String> serverdetails() throws NamingException {
		Map<String, String> response = apmSl.serverdetails();
		return  response;
	}
	
}
