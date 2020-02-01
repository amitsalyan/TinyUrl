package com.tiny.sessionbeans;

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
	public Map<String, String> claimsearch(String name){
		Map<String, String> response = new LinkedHashMap<String, String>();
		try{
		    long startTime = System.currentTimeMillis();
			Query q = em.createNativeQuery("select top 100 claim_id from Claim inner join member on member.member_id=Claim.member_id and member.first_name like :name OR member.last_name like  :name and member.deleted='N' where Claim.deleted='N' order by claim_id desc ");
			q.setParameter("name", "%"+name+"%");
			List list= q.getResultList();
			long stopTime = System.currentTimeMillis();
			Long elapsedTime = new Long(stopTime - startTime);
			response.put("elapsedTime_ms", elapsedTime.toString());
			response.put("name", name);
			return response;
		}catch (Exception e){
			response.put("ERROR", "Unable to get results from DB");
			return response;
			
		}
	}

	@Override
	public Map<String, String> providersearch(String name) {
		Map<String, String> response = new LinkedHashMap<String, String>();
		try{
		    long startTime = System.currentTimeMillis();
			Query q = em.createNativeQuery("select top 100 provider_id from provider where deleted='N' and first_name like :name or last_name like :name order by provider_id desc");
			q.setParameter("name", "%"+name+"%");
			List list= q.getResultList();
			long stopTime = System.currentTimeMillis();
			Long elapsedTime = new Long(stopTime - startTime);
			response.put("elapsedTime_ms", elapsedTime.toString());
			response.put("name", name);
			return response;
		}catch (Exception e){
			response.put("ERROR", "Unable to get results from DB");
			return response;
			
		}
	}

	@Override
	public Map<String, String> eligibilitysearch(String name) {
		Map<String, String> response = new LinkedHashMap<String, String>();
		try{
		    long startTime = System.currentTimeMillis();
			Query q = em.createNativeQuery("select top 100 Eligibility_id from Eligibility as e inner join member as m on m.member_id=e.member_id and m.first_name like :name OR m.last_name like  :name and m.deleted='N' where e.deleted='N' and (getDate() between e.EFFECTIVE_DATE AND COALESCE(e.EXPIRATION_DATE, getDate())) order by eligibility_id desc");
			q.setParameter("name", "%"+name+"%");
			List list= q.getResultList();
			long stopTime = System.currentTimeMillis();
			Long elapsedTime = new Long(stopTime - startTime);
			response.put("elapsedTime_ms", elapsedTime.toString());
			response.put("name", elapsedTime.toString());
			return response;
		}catch (Exception e){
			response.put("ERROR", "Unable to get results from DB");
			return response;
			
		}
	}
	
	
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

	
}
