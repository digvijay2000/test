package com.twitter.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.twitter.connectionManager.ConnectionManager;
import com.twitter.dao.ReplyDAO;
import com.twitter.pojo.Reply;
import com.twitter.utils.DatabaseUtils;

public class ReplyDAOImpl implements ReplyDAO{
	private Reply reply;
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	private List<Integer> listReplyId;
	private static final String SQL_RETRIEVE_ALL_REPLIES = "select Reply_Id from Replies where USER_ID = ? and TWEET_ID = ?";
	private static final String SQL_RETRIEVE_BY_ID ="select REPLY_TEXT from TWEETS where REPLY_ID = ?";
	private static final String SQL_INSERT_REPLY = "INSERT INTO TABLE REPLIES(REPLY_TEXT,USER_ID,TWEET_ID ) "
			+ "VALUES(?,?,?)";
	
	public List<Integer> getAllReplies(int userId,int tweetId){
		
		try{
		connection = ConnectionManager.getConnection();
		connection.prepareStatement(SQL_RETRIEVE_ALL_REPLIES);
		preparedStatement.setInt(1, userId);
		preparedStatement.setInt(2, tweetId);
		resultSet = preparedStatement.executeQuery();
		while(resultSet.next()){
			listReplyId.add(resultSet.getInt("REPLY_ID"));
		}
				
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			closeAll();
		}
		
		return listReplyId;
	}
	
	
public Reply findById(int replyId){
		
			 try{
			 connection = ConnectionManager.getConnection();
			 preparedStatement = connection.prepareStatement(SQL_RETRIEVE_BY_ID);
			 preparedStatement.setInt(1, replyId);
			 resultSet=preparedStatement.executeQuery();
			
			 while(resultSet.next()){
				 reply.setReplyText(resultSet.getString("REPLY_TEXT"));
				 reply.setReplyId(resultSet.getInt("REPLY_ID"));
			 }
			 
			 }catch(SQLException e){
				 e.printStackTrace();
			 }finally{
					closeAll();
				}
			
			return reply;
	
	}

public boolean addReply(int userId, String replyText, int tweetId){
	boolean isReplyAdded = false;
	try{
	connection =ConnectionManager.getConnection();
	preparedStatement = connection.prepareStatement(SQL_INSERT_REPLY);
	preparedStatement.setInt(1, userId);
	preparedStatement.setString(2, replyText);
	preparedStatement.setInt(3, tweetId);
	isReplyAdded = preparedStatement.execute();
	
	}catch(SQLException e){
		e.printStackTrace();
	}finally{
		closeAll();
	}
	return isReplyAdded;
}

	private void closeAll() {
		DatabaseUtils.close(resultSet);
		DatabaseUtils.close(preparedStatement);
		DatabaseUtils.close(connection);
		
	}


	@Override
	public List<Integer> getAllReplies(int UserId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean addReply(int UserId, String tweetText) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean deleteReply(int UserId, int tweetId) {
		// TODO Auto-generated method stub
		return false;
	}

}
