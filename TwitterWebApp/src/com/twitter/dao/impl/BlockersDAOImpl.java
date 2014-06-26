package com.twitter.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;

import com.twitter.connectionManager.ConnectionManager;
import com.twitter.dao.BlockerDAO;

import com.twitter.utils.DatabaseUtils;

public class BlockersDAOImpl implements BlockerDAO{
	
	

	private static final String SQL_INSERT_BLOCKER = "INSERT INTO  USER_BLOCKERS(USER_ID, BLOCKER_ID) "
			+ "VALUES(?,?)";
	private static final String SQL_DELETE_BLOCKER = "DELETE FROM USER_BLOCKERS WHERE USER_ID = ? AND BLOCKER_ID = ?";
	
	public int addBlocker(int userId, int blockerId){
		 Connection connection = null;
		 PreparedStatement preparedStatement = null;
		
		int userBlocked = 0;
		try{
		connection =ConnectionManager.getConnection();
		preparedStatement = connection.prepareStatement(SQL_INSERT_BLOCKER);
		preparedStatement.setInt(1, userId);
		preparedStatement.setInt(2, blockerId);
		userBlocked = preparedStatement.executeUpdate();
		connection.commit();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			closeAll(connection,preparedStatement);
		}
		return userBlocked;
	}
     
	

	@Override
	public int deleteBlocker(int userId, int blockerId) {
		 Connection connection = null;
		 PreparedStatement preparedStatement = null;
	
		int userUnblocked =0;
		try{
			connection=ConnectionManager.getConnection();
			
			preparedStatement = connection.prepareStatement(SQL_DELETE_BLOCKER);
			preparedStatement.setInt(1, userId);
			preparedStatement.setInt(2, blockerId);
			userUnblocked = preparedStatement.executeUpdate();
			connection.commit();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			closeAll(connection,preparedStatement);
		}
		return userUnblocked;
	}
	
	
	private void closeAll(Connection connection, PreparedStatement preparedStatement) {
	
		DatabaseUtils.close(preparedStatement);
		DatabaseUtils.close(connection);
		
	
        }
}
