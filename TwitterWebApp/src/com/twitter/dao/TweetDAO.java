package com.twitter.dao;

import java.util.List;

import com.twitter.pojo.Tweet;


public interface TweetDAO extends DAO{
	
	public Tweet findById(int tweetId);
	public List<Integer> getAllTweets(int UserId);
	public int addTweet(int UserId, String tweetText);
	public int deleteTweet(int UserId, int tweetId);
	

}
