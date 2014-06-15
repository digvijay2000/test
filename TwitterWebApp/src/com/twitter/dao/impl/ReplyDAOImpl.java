package com.twitter.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
	private static final String SQL_RETRIEVE_ALL_REPLIES = "SELECT * FROM REPLIES WHERE TWEET_ID = ?";
	private static final String SQL_RETRIEVE_BY_ID ="SELECT * FROM REPLIES WHERE REPLY_ID = ?";
	private static final String SQL_INSERT_REPLY = "INSERT INTO  REPLIES(USER_ID,REPLY_TEXT,TWEET_ID ) "
			+ "VALUES(?,?,?)";
	private static final String SQL_DELETE_REPLY ="DELETE FROM REPLIES WHERE REPLY_ID =?";
	
	public List<Integer> getAllReplies(int tweetId){
		
		try{
		connection = ConnectionManager.getConnection();
		preparedStatement = connection.prepareStatement(SQL_RETRIEVE_ALL_REPLIES);
		preparedStatement.setInt(1, tweetId);
		resultSet = preparedStatement.executeQuery();
		listReplyId = new ArrayList<Integer>();
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
			reply = new Reply();
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
	connection.commit();
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
	public boolean deleteReply(int replyId) {
		try{
			connection = ConnectionManager.getConnection();
			preparedStatement = connection.prepareStatement(SQL_DELETE_REPLY);
			preparedStatement.setInt(1, replyId);
			preparedStatement.execute();
			connection.commit();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			closeAll();
		}
		return false;
	}



}
