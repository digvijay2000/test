package com.twitter.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.twitter.connectionManager.ConnectionManager;
import com.twitter.pojo.Reply;
import com.twitter.pojo.Tweet;
import com.twitter.pojo.User;
import com.twitter.utils.DbClose;

public class ReplyDAO {
	Reply reply;
	Connection connection;
	PreparedStatement preparedStatement;
	ResultSet resultSet;
	ArrayList<Integer> listOfReplyId;
	
	public ArrayList<Integer> getAllReplies(int userId,int tweetId){
		String selectQuery = "select Reply_Id from Replies where USER_ID = ? and TWEET_ID = ?";
		try{
		connection = ConnectionManager.getConnection();
		connection.prepareStatement(selectQuery);
		preparedStatement.setInt(1, userId);
		preparedStatement.setInt(2, tweetId);
		resultSet = preparedStatement.executeQuery();
		while(resultSet.next()){
			listOfReplyId.add(resultSet.getInt("REPLY_ID"));
		}
				
		}catch(SQLException e){
			e.printStackTrace();
		}
		closeAll();
		return listOfReplyId;
	}
	
	
public Reply findById(int replyId){
		
		String selectQuery ="select REPLY_TEXT from TWEETS where REPLY_ID = ?";
			 try{
			 connection = ConnectionManager.getConnection();
			 preparedStatement = connection.prepareStatement(selectQuery);
			 preparedStatement.setInt(1, replyId);
			 resultSet=preparedStatement.executeQuery();
			
			 while(resultSet.next()){
				 reply.setReply_Text(resultSet.getString("REPLY_TEXT"));
				 reply.setReply_Id(resultSet.getInt("REPLY_ID"));
			 }
			 
			 }catch(SQLException e){
				 e.printStackTrace();
			 }
			
			return reply;
	
	}

public boolean addReply(User user, String replyText, int tweetId){
	String InsertQuery = "INSERT INTO TABLE REPLIES(REPLY_TEXT,USER_ID,TWEET_ID ) "
			+ "VALUES(?,?,?)";
	boolean flag = false;
	try{
	connection =ConnectionManager.getConnection();
	preparedStatement = connection.prepareStatement(InsertQuery);
	preparedStatement.setInt(1, user.getUser_id());
	preparedStatement.setString(2, replyText);
	preparedStatement.setInt(3, tweetId);
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
