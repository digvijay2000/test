package com.twitter.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.twitter.connectionManager.ConnectionManager;
import com.twitter.pojo.User;
import com.twitter.utils.DbClose;


public class FollowersDAO {
	
	Connection connection;
	PreparedStatement preparedStatement;
	ResultSet resultSet;
	ArrayList<Integer> listOfFollowers;
	
	public ArrayList<Integer> getAllFollowers(int userId){
		String selectQuery = "select FOLLOWER_ID from USER_FOLLOWERS where USER_ID = ?";
		try{
		connection = ConnectionManager.getConnection();
		connection.prepareStatement(selectQuery);
		preparedStatement.setInt(1, userId);
		resultSet = preparedStatement.executeQuery();
		while(resultSet.next()){
			listOfFollowers.add(resultSet.getInt("FOLLOWER_ID"));
		}
		
		}catch(SQLException e){
			e.printStackTrace();
		}
		closeAll();
		return listOfFollowers;
		
		
	}

	public boolean addFollower(User user, int followerId){
		String InsertQuery = "INSERT INTO TABLE USER_FOLLOWERS(USER_ID, FOLLOWER_ID) "
				+ "VALUES(?,?)";
		boolean flag = false;
		try{
		connection =ConnectionManager.getConnection();
		preparedStatement = connection.prepareStatement(InsertQuery);
		preparedStatement.setInt(1, user.getUser_id());
		preparedStatement.setInt(2, followerId);
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
