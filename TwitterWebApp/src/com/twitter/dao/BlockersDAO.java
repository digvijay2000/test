package com.twitter.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.twitter.connectionManager.ConnectionManager;
import com.twitter.pojo.User;
import com.twitter.utils.DbClose;

public class BlockersDAO {
	
	Connection connection;
	PreparedStatement preparedStatement;
	ResultSet resultSet;
//	ArrayList<Integer> blockersList
// may be no need for this DAO
	
	
	public boolean addBlocker(User user, int blockerId){
		String InsertQuery = "INSERT INTO TABLE USER_BLOCKERS(USER_ID, BLOCKER_ID) "
				+ "VALUES(?,?)";
		boolean flag = false;
		try{
		connection =ConnectionManager.getConnection();
		preparedStatement = connection.prepareStatement(InsertQuery);
		preparedStatement.setInt(1, user.getUser_id());
		preparedStatement.setInt(2, blockerId);
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
