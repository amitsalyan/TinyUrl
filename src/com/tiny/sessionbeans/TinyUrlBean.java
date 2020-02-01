package com.tiny.sessionbeans;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.util.List;
import java.util.Random;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.tiny.entity.TinyUrlEB;
import com.tiny.enums.Deleted;
import com.tiny.enums.TinyUrlStatus;
import com.tiny.util.Util;

@Stateless
public class TinyUrlBean implements TinyUrlLocal{

	@PersistenceContext
	protected EntityManager em;

	@Override
	public TinyUrlEB genTinyUrl(TinyUrlEB tinyUrlEB){
		if(!isValidUrl(tinyUrlEB)){
			tinyUrlEB.setStatus(TinyUrlStatus.INVALID.toString());
		}else {
			tinyUrlEB.setStatus(TinyUrlStatus.ACTIVE.toString());
			tinyUrlEB.setTinyUrl(generateTinyUrl(tinyUrlEB.getTinyUrl()));
		}
		tinyUrlEB.setDeleted(Deleted.N.toString());
		tinyUrlEB.setEntryDate(Util.getCurrentTimestamp());
		tinyUrlEB.setHitCount(0);
		em.persist(tinyUrlEB);
		return tinyUrlEB;
	}

	private boolean isValidUrl(TinyUrlEB tinyUrlEB) {
		if(tinyUrlEB.getUrl().indexOf("http://")==-1 && tinyUrlEB.getUrl().indexOf("https://")==-1) {
			tinyUrlEB.setUrl("http://"+tinyUrlEB.getUrl());
		}
		 try (Socket socket = new Socket()) {
			 HttpURLConnection connection = (HttpURLConnection) new URL(tinyUrlEB.getUrl()).openConnection();
		        connection.setRequestMethod("HEAD");
		        connection.setReadTimeout(5000);
		        int responseCode = connection.getResponseCode();
		        if (200 <= responseCode && responseCode <= 399){
		        	return true;
		        }
		    } catch (IOException e) {
		        return false; 
		    }
		return false;
	}

	private String generateTinyUrl(String baseUrl) {
		Random r = new Random();
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < 6; i++) {
			int ascii = r.nextInt(26) + 65;
			stringBuilder.append(Character.toString((char)ascii));
		}
		//return baseUrl+"/"+stringBuilder.toString();
		return stringBuilder.toString();
	}

	@Override
	public String genTinyUrl(String tinyUri) {
		Query query = em.createQuery("from TINY_URL where tinyUrl=:tinyUrl and hitCount<10 and deleted='N' ");
		query.setParameter("tinyUrl", tinyUri);
		List<TinyUrlEB> tinyUrlEBs = query.getResultList();
		if(tinyUrlEBs.size()==1) {
			TinyUrlEB tinyUrlEB = tinyUrlEBs.get(0);
			tinyUrlEB.setHitCount(tinyUrlEB.getHitCount()+1);
			if(tinyUrlEB.getHitCount()==10) {
				tinyUrlEB.setStatus(TinyUrlStatus.EXPIRED.toString());
			}
			em.persist(tinyUrlEB);
			return tinyUrlEB.getUrl();
		}
		return null;
	}
	
}
