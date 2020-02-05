package com.tiny.sessionbeans;

import java.util.Map;

import javax.ejb.Local;


@Local
public interface APMSL {

	Map<String, String> serverdetails();

	Integer tinyAPiUsage();

	Integer usageDate(String yyyymmdd);

	Integer usageDays(String days);

}
