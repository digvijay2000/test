package com.twitter.dao;

import java.util.List;

import com.twitter.pojo.Reply;

public interface ReplyDAO extends DAO{
	public Reply findById(int replyId);
	public List<Integer> getAllReplies(int tweetId);
	public boolean addReply(int UserId, String replyText, int tweetId);
	public boolean deleteReply(int replyId);
}
