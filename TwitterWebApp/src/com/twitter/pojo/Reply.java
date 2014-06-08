package com.twitter.pojo;

import java.util.Date;

public class Reply {
	
	private int replyId;
	private String replyText;
	private Date replyTime;
	private User user;
	private Tweet tweet;
	
	
	public int getReplyId() {
		return replyId;
	}
	public void setReplyId(int reply_Id) {
		this.replyId = reply_Id;
	}
	public String getReplyText() {
		return replyText;
	}
	public void setReplyText(String reply_Text) {
		this.replyText = reply_Text;
	}
	public Date getReplyTime() {
		return replyTime;
	}
	public void setReplyTime(Date reply_Time) {
		this.replyTime = reply_Time;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Tweet getTweet() {
		return tweet;
	}
	public void setTweet(Tweet tweet) {
		this.tweet = tweet;
	}
	
	
	

}
