package com.twitter.utils;

import java.sql.Date;

public class DateConversion {
	
	public static java.sql.Date javaToSqlDate(java.util.Date javaDate){
		java.sql.Date sqlDate = new java.sql.Date(javaDate.getDate());
		
		return sqlDate;
		
	}
	
	public static java.util.Date sqlToJavaDate(java.sql.Date sqlDate){
		java.util.Date javaDate = new java.util.Date(sqlDate.getDate());
		return javaDate;
	}

}
