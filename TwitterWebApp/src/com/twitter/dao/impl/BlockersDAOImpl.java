package com.twitter.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.twitter.connectionManager.ConnectionManager;
import com.twitter.dao.BlockerDAO;
import com.twitter.pojo.User;
import com.twitter.utils.DatabaseUtils;

public class BlockersDAOImpl implements BlockerDAO{
	
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;

	private static final String SQL_INSERT_BLOCKER = "INSERT INTO  USER_BLOCKERS(USER_ID, BLOCKER_ID) "
			+ "VALUES(?,?)";
	private static final String SQL_DELETE_BLOCKER = "DELETE FROM USER_BLOCKERS WHERE USER_ID = ? AND BLOCKER_ID = ?";
	
	public boolean addBlocker(int userId, int blockerId){
		
		boolean flag = false;
		try{
		connection =ConnectionManager.getConnection();
		connection.setAutoCommit(false);
		preparedStatement = connection.prepareStatement(SQL_INSERT_BLOCKER);
		preparedStatement.setInt(1, userId);
		preparedStatement.setInt(2, blockerId);
		flag = preparedStatement.execute();
		connection.commit();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			closeAll();
		}
		return flag;
	}
     
	

	@Override
	public boolean deleteBlocker(int userId, int blockerId) {
		try{
			connection=ConnectionManager.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(SQL_DELETE_BLOCKER);
			preparedStatement.setInt(1, userId);
			preparedStatement.setInt(2, blockerId);
			preparedStatement.execute();
			connection.commit();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			closeAll();
		}
		return false;
	}
	
	
	private void closeAll() {
		DatabaseUtils.close(resultSet);
		DatabaseUtils.close(preparedStatement);
		DatabaseUtils.close(connection);
		
	
        }
}
