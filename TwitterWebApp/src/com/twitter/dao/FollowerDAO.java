package com.twitter.dao;

import java.util.List;

public interface FollowerDAO extends DAO{

	public List<Integer> getAllFollowers(int userId);
	public int addFollower(int userId, int followerId);
	public int deleteFollower(int userId, int followerId);
}
