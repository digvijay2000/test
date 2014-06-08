package com.twitter.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.twitter.connectionManager.ConnectionManager;
import com.twitter.pojo.Reply;
import com.twitter.utils.DatabaseUtils;

public class ReplyDAO {
	Reply reply;
	Connection connection;
	PreparedStatement preparedStatement;
	ResultSet resultSet;
	List<Integer> listReplyId;
	
	public List<Integer> getAllReplies(int userId,int tweetId){
		String selectQuery = "select Reply_Id from Replies where USER_ID = ? and TWEET_ID = ?";
		try{
		connection = ConnectionManager.getConnection();
		connection.prepareStatement(selectQuery);
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
		
		String selectQuery ="select REPLY_TEXT from TWEETS where REPLY_ID = ?";
			 try{
			 connection = ConnectionManager.getConnection();
			 preparedStatement = connection.prepareStatement(selectQuery);
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
	String InsertQuery = "INSERT INTO TABLE REPLIES(REPLY_TEXT,USER_ID,TWEET_ID ) "
			+ "VALUES(?,?,?)";
	boolean isReplyAdded = false;
	try{
	connection =ConnectionManager.getConnection();
	preparedStatement = connection.prepareStatement(InsertQuery);
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

}
