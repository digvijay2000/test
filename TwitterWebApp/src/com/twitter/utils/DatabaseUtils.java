package com.twitter.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseUtils {

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
	
	public static java.sql.Timestamp javatToSqlTimeStamp (java.util.Date javaDate){
		java.sql.Timestamp timestamp = new java.sql.Timestamp(javaDate.getTime());
		return timestamp;
		
	}
	
	public static java.Util.Date sqltToSqlTimeStamp (java.sql.Timestamp timestamp){
		java.util.Date date = new java.Util.Date(timestamp.getTime());
		return date;
		
	}
}

