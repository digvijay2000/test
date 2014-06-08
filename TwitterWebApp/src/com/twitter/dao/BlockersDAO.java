package com.twitter.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.twitter.connectionManager.ConnectionManager;
import com.twitter.pojo.User;
import com.twitter.utils.DatabaseUtils;

public class BlockersDAO {
	
	Connection connection;
	PreparedStatement preparedStatement;
	ResultSet resultSet;

	
	
	public boolean addBlocker(User user, int blockerId){
		String InsertQuery = "INSERT INTO TABLE USER_BLOCKERS(USER_ID, BLOCKER_ID) "
				+ "VALUES(?,?)";
		boolean flag = false;
		try{
		connection =ConnectionManager.getConnection();
		preparedStatement = connection.prepareStatement(InsertQuery);
		preparedStatement.setInt(1, user.getUserId());
		preparedStatement.setInt(2, blockerId);
		flag = preparedStatement.execute();
		
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			closeAll();
		}
		return flag;
	}
     
	private void closeAll() {
		DatabaseUtils.close(resultSet);
		DatabaseUtils.close(preparedStatement);
		DatabaseUtils.close(connection);
		
	
        }
}
