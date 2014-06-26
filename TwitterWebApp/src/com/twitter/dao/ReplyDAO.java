package com.twitter.dao;

import java.util.List;

import com.twitter.pojo.Reply;

public interface ReplyDAO extends DAO{
	public Reply findById(int replyId);
	public List<Integer> getAllReplies(int tweetId);
	public int addReply(int UserId, String replyText, int tweetId);
	public int deleteReply(int replyId);
}
