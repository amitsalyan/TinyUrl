package com.tiny.sessionbeans;

import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

@Stateless
public class APMSLBean implements APMSL {
	private static Logger LOGGER = Logger.getLogger(APMSLBean.class);
	String claimSearchQuery;
	String pdsQuery;
	String eligibilityQuery;

	@PersistenceContext
	protected EntityManager em;

	@Override
	public Map<String, String> serverdetails() {
		Map<String, String> response = new LinkedHashMap<String, String>();
		Runtime runtime = Runtime.getRuntime();
		Long freeMemory = new Long(runtime.freeMemory());
		response.put("freeMemory_bytes", freeMemory.toString());
		Long maxMemory = new Long(runtime.maxMemory());
		response.put("maxMemory_bytes", maxMemory.toString());
		Long totalMemory = new Long(runtime.totalMemory());
		response.put("totalMemory_bytes", totalMemory.toString());
		return response;
	}

	@Override
	public Integer tinyAPiUsage() {
		Query q = em.createNativeQuery("select count (*) from TINY_URL");
		List<Integer> result = q.getResultList();
		return result.get(0);
	}

	@Override
	public Integer usageDate(String yyyymmdd) {
		Query q = em.createNativeQuery("select count (*) from TINY_URL where datediff(dd, ENTRY_DATE, :p) = 0");
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
				q.setParameter("p", yyyymmdd);
				List<Integer> result = q.getResultList();
				return result.get(0);				
	}

	@Override
	public Integer usageDays(String days) {
		Query q = em.createNativeQuery("select count (*) from TINY_URL where ENTRY_DATE BETWEEN  DATEADD(DAY, :p, getdate()) and getdate()");
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
				q.setParameter("p", Integer.parseInt(days)*(-1));
				List<Integer> result = q.getResultList();
				return result.get(0);				
	}
}
