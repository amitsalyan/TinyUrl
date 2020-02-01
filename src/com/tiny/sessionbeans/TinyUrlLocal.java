package com.tiny.sessionbeans;

import javax.ejb.Local;

import com.tiny.entity.TinyUrlEB;


@Local
public interface TinyUrlLocal {

	TinyUrlEB genTinyUrl(TinyUrlEB tinyUrl);

	String genTinyUrl(String tinyUri);
	
}
