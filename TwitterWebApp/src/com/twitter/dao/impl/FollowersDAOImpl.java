package com.twitter.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.twitter.connectionManager.ConnectionManager;
import com.twitter.dao.FollowerDAO;

import com.twitter.utils.DatabaseUtils;


public class FollowersDAOImpl implements FollowerDAO{
	
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	private List<Integer> listFollowerId;
	private static final String SQL_RETRIEVE_ALLFOLLOWERS = "select FOLLOWER_ID from USER_FOLLOWERS where USER_ID = ?";
	private static final String SQL_INSERT_FOLLOWER = "INSERT INTO TABLE USER_FOLLOWERS(USER_ID, FOLLOWER_ID) "
			+ "VALUES(?,?)";
	public List<Integer> getAllFollowers(int userId){
		
		try{
		connection = ConnectionManager.getConnection();
		connection.prepareStatement(SQL_RETRIEVE_ALLFOLLOWERS);
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
		
		boolean isFollowerAdded = false;
		try{
		connection =ConnectionManager.getConnection();
		preparedStatement = connection.prepareStatement(SQL_INSERT_FOLLOWER);

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

	

	@Override
	public boolean deleteFollower(int userId, int followerId) {
		// TODO Auto-generated method stub
		return false;
	}

	



	

}
