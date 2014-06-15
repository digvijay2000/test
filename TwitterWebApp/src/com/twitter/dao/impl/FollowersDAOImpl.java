package com.twitter.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.twitter.connectionManager.ConnectionManager;
import com.twitter.dao.FollowerDAO;
import com.twitter.utils.DatabaseUtils;


public class FollowersDAOImpl implements FollowerDAO{
	
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	private List<Integer> listFollowerId;
	private static final String SQL_RETRIEVE_ALLFOLLOWERS = "SELECT USER_ID FROM USER_FOLLOWERS WHERE FOLLOWER_ID = ?";
	private static final String SQL_INSERT_FOLLOWER = "INSERT INTO USER_FOLLOWERS(USER_ID, FOLLOWER_ID) VALUES(?,?) ";
		
	private static final String SQL_DELETE_UNFLOLLOW =" DELETE FROM USER_FOLLOWERS WHERE USER_ID = ? AND FOLLOWER_ID = ?";
	public List<Integer> getAllFollowers(int followerId){
		
		try{
		connection = ConnectionManager.getConnection();
		connection.setAutoCommit(false);
		preparedStatement = connection.prepareStatement(SQL_RETRIEVE_ALLFOLLOWERS);
		preparedStatement.setInt(1, followerId);
		resultSet = preparedStatement.executeQuery();
		listFollowerId = new ArrayList<Integer>();
		while(resultSet.next()){
			listFollowerId.add(resultSet.getInt("USER_ID"));
		}
		connection.commit();
		
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
		connection.setAutoCommit(false);
		preparedStatement = connection.prepareStatement(SQL_INSERT_FOLLOWER);

//		followerId is the current user 
//		userId is the user that current user wants to follow
		preparedStatement.setInt(1, userId);
		preparedStatement.setInt(2, followerId);
		isFollowerAdded = preparedStatement.execute();
		connection.commit();
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
		try{
			connection = ConnectionManager.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(SQL_DELETE_UNFLOLLOW);
			preparedStatement.setInt(1, userId);
			preparedStatement.setInt(2, followerId);
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
