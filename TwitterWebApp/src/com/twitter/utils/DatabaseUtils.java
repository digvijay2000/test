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
		java.sql.Date sqlDate = new java.sql.Date(javaDate.getDate());
		
		return sqlDate;
		
	}
	
	public static java.util.Date sqlToJavaDate(java.sql.Date sqlDate){
		java.util.Date javaDate = new java.util.Date(sqlDate.getDate());
		return javaDate;
	}
}

