package com.twitter.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.twitter.connectionManager.ConnectionManager;
import com.twitter.dao.TweetDAO;
import com.twitter.pojo.Tweet;
import com.twitter.utils.DatabaseUtils;

public class TweetDAOImpl implements TweetDAO {
	private Tweet tweet;
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	private List<Integer> listTweetId;
	private static final String SQL_RETRIEVE_ALL_TWEETS_ = "select TWEET_ID from TWEETS where USER_ID = ?";
	private static final String SQL_RETRIEVE_BY_ID ="select TWEET_TEXT from TWEETS where TWEET_ID = ?";
	private static final String SQL_INSERT_TWEET = "INSERT INTO TABLE TWEETS (TWEET_TEXT,USER_ID ) "
			+ "VALUES(?,?)";
	public List<Integer> getAllTweets(int userId){
		
		try{
		connection = ConnectionManager.getConnection();
		connection.prepareStatement(SQL_RETRIEVE_ALL_TWEETS_);
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
		
		
			 try{
			 connection = ConnectionManager.getConnection();
			 preparedStatement = connection.prepareStatement(SQL_RETRIEVE_BY_ID);
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
	
	boolean isTweetAdded = false;
	try{
	connection =ConnectionManager.getConnection();
	preparedStatement = connection.prepareStatement(SQL_INSERT_TWEET);
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

	@Override
	public boolean deleteTweet(int UserId, int tweetId) {
		// TODO Auto-generated method stub
		return false;
	}

}
