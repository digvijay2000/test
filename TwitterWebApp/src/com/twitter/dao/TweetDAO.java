package com.twitter.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.twitter.connectionManager.ConnectionManager;
import com.twitter.pojo.Tweet;

import com.twitter.utils.DatabaseUtils;

public class TweetDAO {
	private Tweet tweet;
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	private List<Integer> listTweetId;
	
	public List<Integer> getAllTweets(int userId){
		String selectQuery = "select TWEET_ID from TWEETS where USER_ID = ?";
		try{
		connection = ConnectionManager.getConnection();
		connection.prepareStatement(selectQuery);
		preparedStatement.setInt(1, userId);
		resultSet = preparedStatement.executeQuery();
		while(resultSet.next()){
			listTweetId.add(resultSet.getInt("TWEET_ID"));
		}
		
		
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			closeAll();	
		}
		
		return listTweetId;
	}
	
	public Tweet findById(int tweetId){
		
		String selectQuery ="select TWEET_TEXT from TWEETS where TWEET_ID = ?";
			 try{
			 connection = ConnectionManager.getConnection();
			 preparedStatement = connection.prepareStatement(selectQuery);
			 preparedStatement.setInt(1, tweetId);
			 resultSet=preparedStatement.executeQuery();
			
			 while(resultSet.next()){
				tweet.setTweetText(resultSet.getString("TWEET_TEXT"));
				 tweet.setTweetId(resultSet.getInt("TWEET_ID"));
			 }
			 
			 }catch(SQLException e){
				 e.printStackTrace();
			 }finally{
				 closeAll();	
			 }
			
			return tweet;
	
	}
	
	

public boolean addTweet(int userId, String tweetText){
	String insertQuery = "INSERT INTO TABLE TWEETS (TWEET_TEXT,USER_ID ) "
			+ "VALUES(?,?)";
	boolean isTweetAdded = false;
	try{
	connection =ConnectionManager.getConnection();
	preparedStatement = connection.prepareStatement(insertQuery);
	preparedStatement.setInt(1, userId);
	preparedStatement.setString(2, tweetText);
	isTweetAdded = preparedStatement.execute();
	
	}catch(SQLException e){
		e.printStackTrace();
	}finally{
		closeAll();	
	}
	return isTweetAdded;
}


	private void closeAll() {
		DatabaseUtils.close(resultSet);
		DatabaseUtils.close(preparedStatement);
		DatabaseUtils.close(connection);
		
	}

}
