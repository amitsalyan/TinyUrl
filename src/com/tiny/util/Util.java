package com.tiny.util;

import java.sql.Timestamp;

public class Util {

	public static Timestamp formatDate(String date) {
		Timestamp timeStampDate = Timestamp.valueOf(date);		
		return timeStampDate;
	}
	
	public static java.sql.Timestamp getCurrentTimestamp()
	{
		java.util.Date date= new java.util.Date();
		return new Timestamp(date.getTime());
	}
}
