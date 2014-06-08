package com.twitter.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;

import com.twitter.connectionManager.ConnectionManager;
import com.twitter.pojo.User;
import com.twitter.utils.DatabaseUtils;


public class FollowersDAO {
	
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	List<Integer> listFollowerId;
	
	public List<Integer> getAllFollowers(int userId){
		String selectQuery = "select FOLLOWER_ID from USER_FOLLOWERS where USER_ID = ?";
		try{
		connection = ConnectionManager.getConnection();
		connection.prepareStatement(selectQuery);
		preparedStatement.setInt(1, userId);
		resultSet = preparedStatement.executeQuery();
		while(resultSet.next()){
			listFollowerId.add(resultSet.getInt("FOLLOWER_ID"));
		}
		
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			closeAll();
		}
		
		return listFollowerId;
		
		
	}

	public boolean addFollower(int userId, int followerId){
		String InsertQuery = "INSERT INTO TABLE USER_FOLLOWERS(USER_ID, FOLLOWER_ID) "
				+ "VALUES(?,?)";
		boolean isFollowerAdded = false;
		try{
		connection =ConnectionManager.getConnection();
		preparedStatement = connection.prepareStatement(InsertQuery);

//		followerId is the current user 
//		userId is the user that current user wants to follow
		preparedStatement.setInt(2, userId);
		preparedStatement.setInt(1, followerId);
		isFollowerAdded = preparedStatement.execute();
		
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			closeAll();
		}
		return isFollowerAdded;
	}
	
	private void closeAll() {
		DatabaseUtils.close(resultSet);
		DatabaseUtils.close(preparedStatement);
		DatabaseUtils.close(connection);
		
	}

	

}
