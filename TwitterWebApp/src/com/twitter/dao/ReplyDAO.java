package com.twitter.dao;

import java.util.List;

import com.twitter.pojo.Reply;

public interface ReplyDAO extends DAO{
	public Reply findById(int userId);
	public List<Integer> getAllReplies(int UserId);
	public boolean addReply(int UserId, String tweetText);
	public boolean deleteReply(int UserId, int tweetId);
}
