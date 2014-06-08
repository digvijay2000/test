package com.twitter.pojo;

import java.util.Date;

public class Tweet {
	
	private int tweet_Id;
	private String tweet_Text;
	private Date tweet_Time;
	private int user_Id;
	
	public int getTweet_Id() {
		return tweet_Id;
	}
	public void setTweet_Id(int tweet_Id) {
		this.tweet_Id = tweet_Id;
	}
	public String getTweet_Text() {
		return tweet_Text;
	}
	public void setTweet_Text(String tweet_Text) {
		this.tweet_Text = tweet_Text;
	}
	public Date getTweet_Time() {
		return tweet_Time;
	}
	public void setTweet_Time(Date tweet_Time) {
		this.tweet_Time = tweet_Time;
	}
	public int getUser_Id() {
		return user_Id;
	}
	public void setUser_Id(int user_Id) {
		this.user_Id = user_Id;
	}
	
	

}
