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
		res.setHeader("Access-Control-Allow-Origin", "*");
		res.setHeader("Access-Control-Allow-HEADERS", "Access-Control-Allow-Origin,Access-Control-Allow-HEADERS,Content-Type,Accept");
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

	private void isTinyUrl(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String uri = req.getRequestURI();
		TinyUrlLocal tinyUrlLocal = BeanServiceUtil.getBean(BeanServiceUtil.TINY_URL_LOCAL);
		if(uri.split("/").length==2) {
			String redirectUrl = tinyUrlLocal.genTinyUrl(uri.split("/")[1]);
			if(redirectUrl!=null) {
				
					res.sendRedirect(redirectUrl);
					return;
				
			}
		}
	}


	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}


}
