package com.tiny.services.tinyurl;

import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.tiny.entity.TinyUrlEB;
import com.tiny.services.RESTApi;
import com.tiny.sessionbeans.APMSL;
import com.tiny.sessionbeans.TinyUrlLocal;
import com.tiny.util.BeanServiceUtil;

@Path("/tiny")
public class TinyUrlApi extends RESTApi{
	
	@Context
	protected HttpServletRequest req;
	
	TinyUrlLocal tinyUrlLocal = BeanServiceUtil.getBean(BeanServiceUtil.TINY_URL_LOCAL);
	
	@POST
	@Path("/genTinyLink")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public TinyUrlEB genTinyLink(TinyUrlEB tinyUrl) throws NamingException {
		System.out.println("tinyUrl.getUrl()-->"+tinyUrl.getUrl());
		tinyUrl.setEntryIP(req.getRemoteAddr());
		tinyUrl =  tinyUrlLocal.genTinyUrl(tinyUrl);
		if(tinyUrl.getTinyUrl()!=null)tinyUrl.setTinyUrl(req.getHeader("origin")+ "/" + tinyUrl.getTinyUrl());
		return tinyUrl;
	}
}
