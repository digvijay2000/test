package com.twitter.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.twitter.connectionManager.ConnectionManager;
import com.twitter.dao.UserDAO;
import com.twitter.pojo.User;
import com.twitter.utils.DatabaseUtils;

public class UserDAOImpl implements UserDAO {

	private User user;
	private static final String SQL_RETRIEVE_BY_EMAILID = "SELECT * FROM USERS WHERE EMAIL_ID = ?";
	private static final String SQL_RETRIEVE_BY_USERID = "SELECT * FROM USERS WHERE USER_ID = ? ";
	private static final String SQL_INSERT_USER = "INSERT INTO USERS(EMAIL_ID,USER_PASSWORD,FIRST_NAME,LAST_NAME,"
			+ "GENDER,DOB,PHONE_NO,ALTERNATE_NO, CREATED_TIME) VALUES(?,?,?,?,?,?,?,?,?)";
	private static final String SQL_DELETE_USER = "DELETE FROM USERS WHERE USER_ID = ?";
	private static final String SQL_UPDATE_USER = "UPDATE  USERS "
			+ "SET FIRST_NAME =?, LAST_NAME =?, PHONE_NO = ?, ALTERNATE_NO =?, USER_PASSWORD = ?, CURRENT_STATUS = ? "
			+ "WHERE USER_ID=? ";

	public User findById(int userId) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = ConnectionManager.getConnection();
			preparedStatement = connection
					.prepareStatement(SQL_RETRIEVE_BY_USERID);
			preparedStatement.setInt(1, userId);
			resultSet = preparedStatement.executeQuery();

			user = fetchFromResultSet(resultSet);
		} catch (SQLException e) {
//			throw new SQLException();
		} finally {
			closeAll(connection, preparedStatement);
		}

		return user;
	}

	public User findByName(String emailAddress) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = ConnectionManager.getConnection();
			preparedStatement = connection
					.prepareStatement(SQL_RETRIEVE_BY_EMAILID);
			preparedStatement.setString(1, emailAddress);
			resultSet = preparedStatement.executeQuery();
			user = fetchFromResultSet(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(connection, preparedStatement, resultSet);
		}
		return user;
	}

	public int addUser(User user)  {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int isUserAdded = 0;
		try {
			connection = ConnectionManager.getConnection();

			preparedStatement = connection.prepareStatement(SQL_INSERT_USER);
			preparedStatement.setString(1, user.getEmailAddress());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getFirstName());
			preparedStatement.setString(4, user.getLastName());
			preparedStatement.setString(5, user.getGender());
			preparedStatement.setDate(6,
					DatabaseUtils.javaToSqlDate(user.getDob()));
			preparedStatement.setInt(7, user.getPhoneNo());
			preparedStatement.setInt(8, user.getAlternateNo());
			preparedStatement.setTimestamp(9,
					DatabaseUtils.javaToSqlTimeStamp(new Date()));
			isUserAdded = preparedStatement.executeUpdate();
			connection.commit();

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			closeAll(connection, preparedStatement);
		}
		return isUserAdded;
	}

	private User fetchFromResultSet(ResultSet resultSet) {
		try {
			while (resultSet.next()) {
				if (user == null)
					user = new User();
				user.setUserId(resultSet.getInt("USER_ID"));
				user.setFirstName(resultSet.getString("FIRST_NAME"));
				user.setLastName(resultSet.getString("LAST_NAME"));
				user.setGender(resultSet.getString("GENDER"));
				user.setPhoneNo(resultSet.getInt("PHONE_NO"));
				user.setStatus(resultSet.getString("CURRENT_STATUS"));
				user.setEmailAddress(resultSet.getString("EMAIL_ID"));
				user.setDob(DatabaseUtils.sqlToJavaDate(resultSet
						.getDate("DOB")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}



	@Override
	public int deleteUser(int userId) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int isUserDeleted = 0;
		try {

			connection = ConnectionManager.getConnection();

			preparedStatement = connection.prepareStatement(SQL_DELETE_USER);
			preparedStatement.setInt(1, userId);
			isUserDeleted = preparedStatement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(connection, preparedStatement);
		}

		return isUserDeleted;
	}

	@Override
	public int updateUser(User user) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int isUserUpdated = 0;
		try {
			connection = ConnectionManager.getConnection();

			preparedStatement = connection.prepareStatement(SQL_UPDATE_USER);
			preparedStatement.setString(1, user.getFirstName());
			preparedStatement.setString(2, user.getLastName());
			preparedStatement.setInt(3, user.getPhoneNo());
			preparedStatement.setInt(4, user.getAlternateNo());
			preparedStatement.setString(5, user.getPassword());
			preparedStatement.setString(6, user.getStatus());
			preparedStatement.setInt(7, user.getUserId());

			isUserUpdated = preparedStatement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(connection, preparedStatement);
		}
		return isUserUpdated;
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
