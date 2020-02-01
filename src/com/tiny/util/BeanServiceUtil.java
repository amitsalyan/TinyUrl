package com.tiny.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

/**
 * The Class BeanServiceUtl used to lookup a Bean.
 *
 */
public class BeanServiceUtil {
	
	/** The logger. */
	private static Logger LOGGER=Logger.getLogger(BeanServiceUtil.class);
	public static String TINY_URL_LOCAL = "java:app/TinyUrl/TinyUrlBean!com.tiny.sessionbeans.TinyUrlLocal";

	/**
	 * Gets the initial context.
	 *
	 * @return the initial context
	 * @throws NamingException the naming exception
	 */
	public static Context getInitialContext() throws javax.naming.NamingException
	{
		return new InitialContext(/* getJNDIProperties() */);
	}

	/**
	 * Gets the DB service.
	 *
	 * @param <T> the generic type
	 * @param serviceName the service name
	 * @return the DB service
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String serviceName){
		T t=null;
		try {
			t = (T) (getInitialContext().lookup(serviceName));
			return t;
		} catch (javax.naming.NamingException ne) {
			LOGGER.debug("NamingException", ne);
		}
		return null;
	}

}
