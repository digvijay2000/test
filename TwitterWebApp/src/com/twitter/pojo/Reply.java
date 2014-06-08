package com.twitter.pojo;

import java.util.Date;

public class Reply {
	
	private int reply_Id;
	private String reply_Text;
	private Date reply_Time;
	private int user_Id;
	private int tweet_Id;
	
	
	public int getReply_Id() {
		return reply_Id;
	}
	public void setReply_Id(int reply_Id) {
		this.reply_Id = reply_Id;
	}
	public String getReply_Text() {
		return reply_Text;
	}
	public void setReply_Text(String reply_Text) {
		this.reply_Text = reply_Text;
	}
	public Date getReply_Time() {
		return reply_Time;
	}
	public void setReply_Time(Date reply_Time) {
		this.reply_Time = reply_Time;
	}
	public int getUser_Id() {
		return user_Id;
	}
	public void setUser_Id(int user_Id) {
		this.user_Id = user_Id;
	}
	public int getTweet_Id() {
		return tweet_Id;
	}
	public void setTweet_Id(int tweet_Id) {
		this.tweet_Id = tweet_Id;
	}
	
	
	

}
