package com.twitter.pojo;

import java.util.Date;

public class Tweet {
	
	private int tweetId;
	private String tweetText;
	private Date tweetTime;
	private User user;
	
	public int getTweetId() {
		return tweetId;
	}
	public void setTweetId(int tweetId) {
		this.tweetId = tweetId;
	}
	public String getTweetText() {
		return tweetText;
	}
	public void setTweetText(String tweetText) {
		this.tweetText = tweetText;
	}
	public Date getTweetTime() {
		return tweetTime;
	}
	public void setTweetTime(Date tweetTime) {
		this.tweetTime = tweetTime;
	}
	public User getUser() {
		return user;
	}
	public void setUserId(User user) {
		this.user = user;
	}
	
	

}
