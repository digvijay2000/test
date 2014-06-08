package com.twitter.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.twitter.connectionManager.ConnectionManager;
import com.twitter.pojo.User;

import com.twitter.utils.DatabaseUtils;

public class UserDAO {
	
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet =null;
	 User user;
	 
	public User findById(int userId) {
		 String selectQuery ="select * from USERS where USER_ID = ? ";
		 try{
		 connection = ConnectionManager.getConnection();
		 preparedStatement = connection.prepareStatement(selectQuery);
		 preparedStatement.setInt(1, userId);
		 resultSet=preparedStatement.executeQuery();
		 user= resultSetIterator(resultSet);
		 }catch(SQLException e){
			 e.printStackTrace();
		 }finally{
			 closeAll();
		 }
		
		return user;
	}
	
	


	public User findByName(String emailAddress){
		String selectQuery = "select * from USERS where EMAIL_ID = ?";
		try{
		connection =ConnectionManager.getConnection();
		preparedStatement = connection.prepareStatement(selectQuery);
		preparedStatement.setString(1, emailAddress);
		resultSet = preparedStatement.executeQuery();
		user =resultSetIterator(resultSet);
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
		closeAll();
		}
		return user;
	}
	
	
	public boolean addUser(User user){
		String InsertQuery = "INSERT INTO USERS(EMAILD_ID,PASSWORD,FIRST_NAME,LAST_NAME,"
				+ "GENDER,DOB,PHONE_NO,ALTERNATE_NO) VALUES(?,?,?,?,?,?,?)";
		boolean isUserAdded = false;
		try{
		connection = ConnectionManager.getConnection();
		preparedStatement = connection.prepareStatement(InsertQuery);
		preparedStatement.setString(1, user.getEmail_Id());
		preparedStatement.setString(2, user.getPassword());
		preparedStatement.setString(3, user.getFirst_Name());
		preparedStatement.setString(4, user.getLast_Name());
		preparedStatement.setString(5, user.getGender());
		preparedStatement.setDate(6, DatabaseUtils.javaToSqlDate(user.getDob()));
		preparedStatement.setInt(7, user.getPhone_No());
		preparedStatement.setInt(8, user.getAlternate_No());
		isUserAdded = preparedStatement.execute();
		
		
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			closeAll();	
		}
		return isUserAdded;
	}
	
	
	private User resultSetIterator(ResultSet resultSet)  {
		try{			
		while(resultSet.next()){
			user.setUser_id(resultSet.getInt("USER_ID"));
			user.setFirst_Name(resultSet.getString("FIRST_NAME"));
			user.setLast_Name(resultSet.getString("LAST_NAME"));
			user.setGender(resultSet.getString("GENDER"));
			user.setPhone_No(resultSet.getInt("PHONE_NO"));
			user.setStatus(resultSet.getString("STATUS"));
			user.setEmail_Id(resultSet.getString("EMAIL_ID"));
			user.setDob(DatabaseUtils.sqlToJavaDate(resultSet.getDate("DOB")));
						
		}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return user;
		
	}
	
	
	



	private void closeAll() {
		DatabaseUtils.close(resultSet);
		DatabaseUtils.close(preparedStatement);
		DatabaseUtils.close(connection);
		
	}

}
