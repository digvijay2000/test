package com.twitter.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.twitter.connectionManager.ConnectionManager;
import com.twitter.dao.TweetDAO;
import com.twitter.pojo.Tweet;
import com.twitter.utils.DatabaseUtils;

public class TweetDAOImpl implements TweetDAO {

	private static final String SQL_RETRIEVE_ALL_TWEETS_ = "select * from TWEETS where USER_ID = ?";
	private static final String SQL_RETRIEVE_BY_ID = "select * from TWEETS where TWEET_ID = ?";
	private static final String SQL_INSERT_TWEET = "INSERT INTO TWEETS (USER_ID, TWEET_TEXT ) "
			+ "VALUES(?,?)";
	private static final String SQL_DELETE_TWEET = "DELETE FROM TWEETS WHERE TWEET_ID = ?";

	public List<Integer> getAllTweets(int userId) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		List<Integer> listTweetId = null;
		try {
			connection = ConnectionManager.getConnection();
			preparedStatement = connection
					.prepareStatement(SQL_RETRIEVE_ALL_TWEETS_);
			preparedStatement.setInt(1, userId);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				if (listTweetId == null)
					listTweetId = new ArrayList<Integer>();
				listTweetId.add(resultSet.getInt("TWEET_ID"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(connection, preparedStatement, resultSet);
		}

		return listTweetId;
	}

	public Tweet findById(int tweetId) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Tweet tweet = null;

		try {
			connection = ConnectionManager.getConnection();
			preparedStatement = connection.prepareStatement(SQL_RETRIEVE_BY_ID);
			preparedStatement.setInt(1, tweetId);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				tweet = new Tweet();
				tweet.setTweetText(resultSet.getString("TWEET_TEXT"));
				tweet.setTweetId(resultSet.getInt("TWEET_ID"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(connection, preparedStatement, resultSet);
		}

		return tweet;

	}

	public int addTweet(int userId, String tweetText) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int isTweetAdded = 0;
		try {
			connection = ConnectionManager.getConnection();
			preparedStatement = connection.prepareStatement(SQL_INSERT_TWEET);
			preparedStatement.setInt(1, userId);
			preparedStatement.setString(2, tweetText);
			isTweetAdded = preparedStatement.executeUpdate();
			connection.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(connection, preparedStatement, resultSet);
		}
		return isTweetAdded;
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

	@Override
	public int deleteTweet(int UserId, int tweetId) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int isTweetDeleted = 0;
		try {
			connection = ConnectionManager.getConnection();
			preparedStatement = connection.prepareStatement(SQL_DELETE_TWEET);
			preparedStatement.setInt(1, tweetId);
			isTweetDeleted = preparedStatement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(connection, preparedStatement);
		}
		return isTweetDeleted;
	}

}
