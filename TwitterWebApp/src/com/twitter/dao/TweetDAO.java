package com.twitter.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.twitter.connectionManager.ConnectionManager;
import com.twitter.pojo.Tweet;
import com.twitter.pojo.User;
import com.twitter.utils.DbClose;

public class TweetDAO {
	Tweet tweet;
	Connection connection;
	PreparedStatement preparedStatement;
	ResultSet resultSet;
	ArrayList<Integer> listOfTweetId;
	
	public ArrayList<Integer> getAllTweets(int userId){
		String selectQuery = "select TWEET_ID from TWEETS where USER_ID = ?";
		try{
		connection = ConnectionManager.getConnection();
		connection.prepareStatement(selectQuery);
		preparedStatement.setInt(1, userId);
		resultSet = preparedStatement.executeQuery();
		while(resultSet.next()){
			listOfTweetId.add(resultSet.getInt("TWEET_ID"));
		}
		
		
		}catch(SQLException e){
			e.printStackTrace();
		}
		closeAll();
		return listOfTweetId;
	}
	
	public Tweet findById(int tweetId){
		
		String selectQuery ="select TWEET_TEXT from TWEETS where TWEET_ID = ?";
			 try{
			 connection = ConnectionManager.getConnection();
			 preparedStatement = connection.prepareStatement(selectQuery);
			 preparedStatement.setInt(1, tweetId);
			 resultSet=preparedStatement.executeQuery();
			
			 while(resultSet.next()){
				 tweet.setTweet_Text(resultSet.getString("TWEET_TEXT"));
				 tweet.setTweet_Id(resultSet.getInt("TWEET_ID"));
			 }
			 
			 }catch(SQLException e){
				 e.printStackTrace();
			 }
			
			return tweet;
	
	}
	
	

public boolean addTweet(User user, String tweetText){
	String InsertQuery = "INSERT INTO TABLE TWEETS (TWEET_TEXT,USER_ID ) "
			+ "VALUES(?,?,?)";
	boolean flag = false;
	try{
	connection =ConnectionManager.getConnection();
	preparedStatement = connection.prepareStatement(InsertQuery);
	preparedStatement.setInt(1, user.getUser_id());
	preparedStatement.setString(2, tweetText);
	flag = preparedStatement.execute();
	closeAll();
	}catch(SQLException e){
		e.printStackTrace();
	}
	return flag;
}


	private void closeAll() {
		DbClose.close(resultSet);
		DbClose.close(preparedStatement);
		DbClose.close(connection);
		
	}

}
