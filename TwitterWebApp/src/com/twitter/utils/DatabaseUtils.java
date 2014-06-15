package com.twitter.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatabaseUtils {

	private static SimpleDateFormat dateFormat;
	public static void close (Connection connection){
		if(connection!=null)
		try{
			connection.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void close (Statement statement){
		if(statement!=null)
			try{
				statement.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	public static void close (PreparedStatement statement){
		if(statement!=null)
			try{
				statement.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	
	public static void close (ResultSet resultSet){
		if(resultSet!=null)
			try{
				resultSet.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	public static java.sql.Date javaToSqlDate(java.util.Date javaDate){
		java.sql.Date sqlDate = new java.sql.Date(javaDate.getTime());
		
		return sqlDate;
		
	}
	
	public static java.util.Date sqlToJavaDate(java.sql.Date sqlDate){
		java.util.Date javaDate = new java.util.Date(sqlDate.getTime());
		return javaDate;
	}
	
	public static java.sql.Timestamp javaToSqlTimeStamp (java.util.Date javaDate){
		java.sql.Timestamp timestamp = new java.sql.Timestamp(javaDate.getTime());
		return timestamp;
		
	}
	
	public static java.util.Date sqltToJavaTimeStamp (java.sql.Timestamp timestamp){
		java.util.Date date = new java.util.Date(timestamp.getTime());
		return date;
		
	}
	
	public static java.util.Date stringToDate (String dateString, String pattern){
		dateFormat = new SimpleDateFormat(pattern);
		
		java.util.Date date = new Date();
		try {
			date =dateFormat.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
}

