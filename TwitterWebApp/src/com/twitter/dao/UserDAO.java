package com.twitter.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.twitter.connectionManager.ConnectionManager;
import com.twitter.pojo.User;
import com.twitter.utils.DateConversion;
import com.twitter.utils.DbClose;

public class UserDAO {
	
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet =null;
	 User user;
	public User findById(int user_Id) {
		 String selectQuery ="select * from USERS where USER_ID = ? ";
		 try{
		 connection = ConnectionManager.getConnection();
		 preparedStatement = connection.prepareStatement(selectQuery);
		 preparedStatement.setInt(1, user_Id);
		 resultSet=preparedStatement.executeQuery();
		 user= new User();
			setUser(resultSet,user);
		 }catch(SQLException e){
			 e.printStackTrace();
		 }
		
		return user;
	}
	
	


	public User findByName(String email_Id){
		String selectQuery = "select * from USERS where EMAIL_ID = ?";
		try{
		connection =ConnectionManager.getConnection();
		preparedStatement = connection.prepareStatement(selectQuery);
		preparedStatement.setString(1, email_Id);
		resultSet = preparedStatement.executeQuery();
		user = new User();
		setUser(resultSet, user);
		}catch(SQLException e){
			e.printStackTrace();
		}
		closeAll();
		return user;
	}
	
	
	public boolean insertUser(User user){
		String InsertQuery = "INSERT INTO USERS(EMAILD_ID,PASSWORD,FIRST_NAME,LAST_NAME,"
				+ "GENDER,DOB,PHONE_NO,ALTERNATE_NO) VALUES(?,?,?,?,?,?,?)";
		boolean flag = false;
		try{
		connection = ConnectionManager.getConnection();
		preparedStatement = connection.prepareStatement(InsertQuery);
		preparedStatement.setString(1, user.getEmail_Id());
		preparedStatement.setString(2, user.getPassword());
		preparedStatement.setString(3, user.getFirst_Name());
		preparedStatement.setString(4, user.getLast_Name());
		preparedStatement.setString(5, user.getGender());
		preparedStatement.setDate(6, DateConversion.javaToSqlDate(user.getDob()));
		preparedStatement.setInt(7, user.getPhone_No());
		preparedStatement.setInt(7, user.getAlternate_No());
		flag = preparedStatement.execute();
		closeAll();	
		
		}catch(SQLException e){
			e.printStackTrace();
		}
		return flag;
	}
	
	
	private void setUser(ResultSet resultSet, User user)  {
		try{
		while(resultSet.next()){
			user.setUser_id(resultSet.getInt("USER_ID"));
			user.setFirst_Name(resultSet.getString("FIRST_NAME"));
			user.setLast_Name(resultSet.getString("LAST_NAME"));
			user.setGender(resultSet.getString("GENDER"));
			user.setPhone_No(resultSet.getInt("PHONE_NO"));
			user.setStatus(resultSet.getString("STATUS"));
			user.setEmail_Id(resultSet.getString("EMAIL_ID"));
			java.sql.Date dob = resultSet.getDate("DOB");
			java.util.Date dobFormatted = new java.util.Date(dob.getDate());
			user.setDob(dobFormatted);
			java.sql.Timestamp created_time =resultSet.getTimestamp("CREATED_TIME");
			java.util.Date created_timeFormatted = new java.util.Date(created_time.getTime());
//			user.setCreated_Time(created_timeFormatted);			
		}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
	}
	
	
	



	private void closeAll() {
		DbClose.close(resultSet);
		DbClose.close(preparedStatement);
		DbClose.close(connection);
		
	}

}
