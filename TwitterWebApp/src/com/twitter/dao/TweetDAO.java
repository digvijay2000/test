package com.twitter.dao;

import java.util.List;

import com.twitter.pojo.Tweet;


public interface TweetDAO extends DAO{
	
	public Tweet findById(int tweetId);
	public List<Integer> getAllTweets(int UserId);
	public boolean addTweet(int UserId, String tweetText);
	public boolean deleteTweet(int UserId, int tweetId);
	

}
