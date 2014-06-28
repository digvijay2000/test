package com.twitter.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.twitter.connectionManager.ConnectionManager;
import com.twitter.pojo.User;

public class DatabaseTestHelper {
	
	
	
	private static final String SQL_INSERT_USER = "INSERT INTO USERS(EMAIL_ID,USER_PASSWORD,FIRST_NAME,LAST_NAME,"
			+ "GENDER,DOB,PHONE_NO,ALTERNATE_NO, CREATED_TIME,USER_ID) VALUES(?,?,?,?,?,?,?,?,?,?)";
	
	private static final String SQL_INSERT_TWEET = "INSERT INTO TWEETS (USER_ID, TWEET_TEXT,TWEET_ID ) "
			+ "VALUES(?,?,?)";
	private static final String SQL_INSERT_REPLY = "INSERT INTO  REPLIES(USER_ID,REPLY_TEXT,TWEET_ID,REPLY_ID ) "
			+ "VALUES(?,?,?,?)";
	public int addUser(User user)  {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int isUserAdded = 0;
		try {
			connection = ConnectionManager.getConnection();

			preparedStatement = connection.prepareStatement(SQL_INSERT_USER);
			preparedStatement.setString(1, user.getEmailAddress());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getFirstName());
			preparedStatement.setString(4, user.getLastName());
			preparedStatement.setString(5, user.getGender());
			preparedStatement.setDate(6,
					DatabaseUtils.javaToSqlDate(user.getDob()));
			preparedStatement.setInt(7, user.getPhoneNo());
			preparedStatement.setInt(8, user.getAlternateNo());
			preparedStatement.setTimestamp(9,
					DatabaseUtils.javaToSqlTimeStamp(new Date()));
			preparedStatement.setInt(10,user.getUserId());
			isUserAdded = preparedStatement.executeUpdate();
			connection.commit();

		} catch (SQLException e) {
//			throw new SQLException("User not created");
		} finally {
			DatabaseUtils.close(preparedStatement);
			DatabaseUtils.close(connection);
		}
		return isUserAdded;
	}

	
	public int addTweet(int userId, String tweetText, int tweetId) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int isTweetAdded = 0;
		try {
			connection = ConnectionManager.getConnection();
			preparedStatement = connection.prepareStatement(SQL_INSERT_TWEET);
			preparedStatement.setInt(1, userId);
			preparedStatement.setString(2, tweetText);
			preparedStatement.setInt(3, tweetId);
			isTweetAdded = preparedStatement.executeUpdate();
			connection.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseUtils.close(resultSet);
			DatabaseUtils.close(preparedStatement);
			DatabaseUtils.close(connection);
		}
		return isTweetAdded;
	}

	
	public int addReply(int userId, String replyText, int tweetId, int replyId){
		Connection connection = null;
		 PreparedStatement preparedStatement = null;
		 ResultSet resultSet = null;
		int replyAddedCount = 0;
		try{
			connection =ConnectionManager.getConnection();
			preparedStatement = connection.prepareStatement(SQL_INSERT_REPLY);
			preparedStatement.setInt(1, userId);
			preparedStatement.setString(2, replyText);
			preparedStatement.setInt(3, tweetId);
			preparedStatement.setInt(4, replyId);
			replyAddedCount = preparedStatement.executeUpdate();
			connection.commit();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DatabaseUtils.close(resultSet);
			DatabaseUtils.close(preparedStatement);
			DatabaseUtils.close(connection);
		}
		return replyAddedCount;
	}
	

	public User defineUser(){
		User user = new User();
		user.setAlternateNo(1234567890);
		user.setEmailAddress("abc@gmail.com");
		user.setGender("M");
		user.setLastName("ronaldoa");
		user.setFirstName("Cristiano");
		user.setPassword("password");
		Date dob = DatabaseUtils.stringToDate("23-02-1991", "dd-mm-yyyy");
		user.setDob(dob);
		user.setPhoneNo(1234567890);
		user.setUserId(1);
		return user;
		
	}
	 
}
