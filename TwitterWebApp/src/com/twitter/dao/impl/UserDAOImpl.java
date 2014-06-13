package com.twitter.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



import com.twitter.connectionManager.ConnectionManager;
import com.twitter.dao.UserDAO;
import com.twitter.pojo.User;
import com.twitter.utils.DatabaseUtils;

public class UserDAOImpl implements UserDAO {
	
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet =null;
	private User user;
	private static final  String SQL_RETRIEVE_BY_EMAILID = "SELECT * FROM USERS WHERE EMAIL_ID = ?";
	private static final  String SQL_RETRIEVE_BY_USERID ="select * from USERS where USER_ID = ? ";
	private static final  String SQL_INSERT_USER = "INSERT INTO USERS(EMAILD_ID,PASSWORD,FIRST_NAME,LAST_NAME,"
			+ "GENDER,DOB,PHONE_NO,ALTERNATE_NO, CREATED_TIME) VALUES(?,?,?,?,?,?,?,?)";

	
	public User findById(int userId) {
		 
		 try{
		 connection = ConnectionManager.getConnection();
		 preparedStatement = connection.prepareStatement(SQL_RETRIEVE_BY_USERID);
		 preparedStatement.setInt(1, userId);
		 resultSet=preparedStatement.executeQuery();
		 user= fetchFromResultSet(resultSet);
		 }catch(SQLException e){
			 e.printStackTrace();
		 }finally{
			 closeAll();
		 }
		
		return user;
	}
	
	


	public User findByName(String emailAddress){
		
		try{
		connection =ConnectionManager.getConnection();
		preparedStatement = connection.prepareStatement(SQL_RETRIEVE_BY_EMAILID);
		preparedStatement.setString(1, emailAddress);
		resultSet = preparedStatement.executeQuery();
		user =fetchFromResultSet(resultSet);
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
		closeAll();
		}
		return user;
	}
	
	
	public boolean addUser(User user){
		
		boolean isUserAdded = false;
		try{
		connection = ConnectionManager.getConnection();
		preparedStatement = connection.prepareStatement(SQL_INSERT_USER);
		preparedStatement.setString(1, user.getEmail_Id());
		preparedStatement.setString(2, user.getPassword());
		preparedStatement.setString(3, user.getFirst_Name());
		preparedStatement.setString(4, user.getLast_Name());
		preparedStatement.setString(5, user.getGender());
		preparedStatement.setDate(6, DatabaseUtils.javaToSqlDate(user.getDob()));
		preparedStatement.setInt(7, user.getPhone_No());
		preparedStatement.setInt(8, user.getAlternate_No());
		preparedStatement.setTimestamp(9, DatabaseUtils.javatToSqlTimeStamp(user.getCreatedTime()));
		isUserAdded = preparedStatement.execute();
		
		
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			closeAll();	
		}
		return isUserAdded;
	}
	
	
	private User fetchFromResultSet(ResultSet resultSet)  {
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




	@Override
	public boolean deleteUser(int UserId) {
		
		return false;
	}




	@Override
	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

}