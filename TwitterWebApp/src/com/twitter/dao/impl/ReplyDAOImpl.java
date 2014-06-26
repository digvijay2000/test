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

	 
	
	private static final String SQL_RETRIEVE_ALL_REPLIES = "SELECT * FROM REPLIES WHERE TWEET_ID = ?";
	private static final String SQL_RETRIEVE_BY_ID ="SELECT * FROM REPLIES WHERE REPLY_ID = ?";
	private static final String SQL_INSERT_REPLY = "INSERT INTO  REPLIES(USER_ID,REPLY_TEXT,TWEET_ID ) "
			+ "VALUES(?,?,?)";
	private static final String SQL_DELETE_REPLY ="DELETE FROM REPLIES WHERE REPLY_ID =?";

	public List<Integer> getAllReplies(int tweetId){
		Connection connection = null;
		 PreparedStatement preparedStatement = null;
		 ResultSet resultSet = null;
		 List<Integer> listReplyId = null;
		try{
			connection = ConnectionManager.getConnection();
			preparedStatement = connection.prepareStatement(SQL_RETRIEVE_ALL_REPLIES);
			preparedStatement.setInt(1, tweetId);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()){
				if(listReplyId==null)
				listReplyId = new ArrayList<Integer>();
				listReplyId.add(resultSet.getInt("REPLY_ID"));
			}

		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			closeAll(connection, preparedStatement, resultSet);
		}

		return listReplyId;
	}


	public Reply findById(int replyId){
		Connection connection = null;
		 PreparedStatement preparedStatement = null;
		 ResultSet resultSet = null;
		Reply reply = null;
		try{
			connection = ConnectionManager.getConnection();
			preparedStatement = connection.prepareStatement(SQL_RETRIEVE_BY_ID);
			preparedStatement.setInt(1, replyId);
			resultSet=preparedStatement.executeQuery();
		
			while(resultSet.next()){
				reply = new Reply();
				reply.setReplyText(resultSet.getString("REPLY_TEXT"));
				reply.setReplyId(resultSet.getInt("REPLY_ID"));
			}

		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			closeAll(connection,preparedStatement,resultSet);
		}

		return reply;

	}

	public int addReply(int userId, String replyText, int tweetId){
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
			replyAddedCount = preparedStatement.executeUpdate();
			connection.commit();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			closeAll(connection, preparedStatement, resultSet);
		}
		return replyAddedCount;
	}




	@Override
	public int deleteReply(int replyId) {
		Connection connection = null;
		 PreparedStatement preparedStatement = null;
		 
		int replyDeletedCount = 0;
		try{
			connection = ConnectionManager.getConnection();
			preparedStatement = connection.prepareStatement(SQL_DELETE_REPLY);
			preparedStatement.setInt(1, replyId);
			replyDeletedCount = preparedStatement.executeUpdate();
			connection.commit();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			closeAll(connection, preparedStatement);
		}
		return replyDeletedCount;
	}


	private void closeAll(Connection connection,
			PreparedStatement preparedStatement, ResultSet resultSet) {
		DatabaseUtils.close(resultSet);
		DatabaseUtils.close(preparedStatement);
		DatabaseUtils.close(connection);

	}

	private void closeAll(Connection connection,
			PreparedStatement preparedStatement) {

		DatabaseUtils.close(preparedStatement);
		DatabaseUtils.close(connection);

	}

}
