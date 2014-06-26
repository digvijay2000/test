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
	
	 

	private static final String SQL_RETRIEVE_ALLFOLLOWERS = "SELECT USER_ID FROM USER_FOLLOWERS WHERE FOLLOWER_ID = ?";
	private static final String SQL_INSERT_FOLLOWER = "INSERT INTO USER_FOLLOWERS(USER_ID, FOLLOWER_ID) VALUES(?,?) ";
		
	private static final String SQL_DELETE_UNFLOLLOW =" DELETE FROM USER_FOLLOWERS WHERE USER_ID = ? AND FOLLOWER_ID = ?";
	public List<Integer> getAllFollowers(int followerId){
		Connection connection = null;
		 PreparedStatement preparedStatement = null;
		 ResultSet resultSet = null;
		List<Integer> listFollowerId = null;
		try{
		connection = ConnectionManager.getConnection();
		connection.setAutoCommit(false);
		preparedStatement = connection.prepareStatement(SQL_RETRIEVE_ALLFOLLOWERS);
		preparedStatement.setInt(1, followerId);
		resultSet = preparedStatement.executeQuery();
		
		while(resultSet.next()){
			if (listFollowerId == null)
				listFollowerId = new ArrayList<Integer>();
			listFollowerId.add(resultSet.getInt("USER_ID"));
		}
		connection.commit();
		
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			closeAll(connection,preparedStatement,resultSet);
		}
		
		return listFollowerId;
		
		
	}

	public int addFollower(int userId, int followerId){
		Connection connection = null;
		 PreparedStatement preparedStatement = null;
		 ResultSet resultSet = null;
		int isFollowerAdded = 0;
		try{
		connection =ConnectionManager.getConnection();
		connection.setAutoCommit(false);
		preparedStatement = connection.prepareStatement(SQL_INSERT_FOLLOWER);

//		followerId is the current user 
//		userId is the user that current user wants to follow
		preparedStatement.setInt(1, userId);
		preparedStatement.setInt(2, followerId);
		isFollowerAdded = preparedStatement.executeUpdate();
		connection.commit();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			closeAll(connection,preparedStatement,resultSet);
		}
		return isFollowerAdded;
	}
	


	

	@Override
	public int deleteFollower(int userId, int followerId) {
		Connection connection = null;
		 PreparedStatement preparedStatement = null;
		 
		int isUserDeleted = 0;
		try{
			connection = ConnectionManager.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(SQL_DELETE_UNFLOLLOW);
			preparedStatement.setInt(1, userId);
			preparedStatement.setInt(2, followerId);
			isUserDeleted = preparedStatement.executeUpdate();
			connection.commit();
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			closeAll(connection, preparedStatement);
		}
		return isUserDeleted;
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
