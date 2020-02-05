package com.tiny.services.tinyurl;

import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.tiny.services.RESTApi;
import com.tiny.sessionbeans.APMSL;
import com.tiny.util.BeanServiceUtil;

@Path("/tiny/analytics")
public class TinyUrlAnalytics extends RESTApi{

	APMSL apmSl = BeanServiceUtil.getBean(BeanServiceUtil.APM_SL);

	@GET
	@Path("/usage")
	@Produces(MediaType.TEXT_PLAIN)
	public Integer usage() throws NamingException {
		return apmSl.tinyAPiUsage();
	}
	
	@GET
	@Path("/usage/{date}")
	@Produces(MediaType.TEXT_PLAIN)
	public Integer usageDate(@PathParam("date") String yyyymmdd) throws NamingException {
		return apmSl.usageDate(yyyymmdd);
	}
	
	@GET
	@Path("/usage/days/{days}")
	@Produces(MediaType.TEXT_PLAIN)
	public Integer usageDays(@PathParam("days") String days) throws NamingException {
		return apmSl.usageDays(days);

	}
	
	/*@GET
	@Path("/active")
	@Produces(MediaType.TEXT_PLAIN)
	public Map<String, String> active() throws NamingException {
		return apmSl.usageDays(days);
	}
	
	@GET
	@Path("/active")
	@Produces(MediaType.TEXT_PLAIN)
	public Map<String, String> active() throws NamingException {
		return apmSl.usageDays(days);
	}*/
	
}
