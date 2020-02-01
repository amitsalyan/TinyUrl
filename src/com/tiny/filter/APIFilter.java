package com.tiny.filter;

import java.io.IOException;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.tiny.sessionbeans.TinyUrlLocal;
import com.tiny.util.BeanServiceUtil;


/**
 * The Class APIFilter.
 * This class is the entry point to all incoming requests
 * @author asalian
 *
 */
public class APIFilter implements Filter{

	private static Logger LOGGER=Logger.getLogger(APIFilter.class);
	private String API_KEY;
	private List<String> ipAddresses;

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
		
	}


	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) arg0;
		HttpServletResponse res = (HttpServletResponse) arg1;
		isTinyUrl(req, res);
		
		String ipaddress = "";
		
		if(req.getHeader("X-FORWARDED-FOR")!=null){
			ipaddress = req.getHeader("X-FORWARDED-FOR");
		} else{
			ipaddress = req.getRemoteAddr();
		}
		LOGGER.debug("incoming IP:"+ipaddress);
		//if (req.getHeader("API-KEY")!=null && req.getHeader("API-KEY").equals(API_KEY)  && ipAddresses.contains(req.getRemoteAddr())){
		/*if (validateIP(ipaddress, uri)){
			arg2.doFilter(req, res);
		}else{
			LOGGER.info("Inavlid access tp API;"+uri+" from IP"+ipaddress);
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
		}*/
		arg2.doFilter(req, res);
	}

	private void isTinyUrl(HttpServletRequest req, HttpServletResponse res) {
		String uri = req.getRequestURI();
		if(uri.split("/").length==2) {
			TinyUrlLocal tinyUrlLocal = BeanServiceUtil.getBean(BeanServiceUtil.TINY_URL_LOCAL);
			String redirectUrl = tinyUrlLocal.genTinyUrl(uri.split("/")[1]);
			if(redirectUrl!=null) {
				try {
					res.sendRedirect(redirectUrl);
					return;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else {
			return;
		}		
	}


	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		//Init get the whitelisted ip adresses that can access the resources
		//AppPropertySL appSL;
		//try {
			//appSL = (AppPropertySL) new InitialContext().lookup("java:app/PortalApi/AppPropertySLBean!com.aldera.sessionbeans.AppPropertySL");
			//ipAddresses=appSL.getIpAdresses();
			//API_KEY=appSL.getAPIKey();
		/*} catch (NamingException e) {
			LOGGER.error("API Properties not loaded, Portal API may not respond.");
			e.printStackTrace();
		}*/
		
	}
	
	private boolean validateIP(String remoteAddr, String uri) {
		if(uri.contains("/api/apm")){
			return true;
		}
		
		if (ipAddresses.get(0).equalsIgnoreCase("*")) {
			return true;
		}
		for (String allowedIP : ipAddresses) {
			if (remoteAddr.equalsIgnoreCase(allowedIP)) {
				return true;
			}
			if (allowedIP.indexOf("*") > 0) {
				if (remoteAddr.startsWith(allowedIP.split("\\*")[0])) {
					return true;
				}
			}
		}
		return false;
	}


}
