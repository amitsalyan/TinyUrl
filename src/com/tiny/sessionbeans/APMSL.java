package com.tiny.sessionbeans;

import java.util.Map;

import javax.ejb.Local;


@Local
public interface APMSL {

	Map<String, String> claimsearch(String name);

	Map<String, String> providersearch(String name);

	Map<String, String> eligibilitysearch(String name);

	Map<String, String> serverdetails();

}
