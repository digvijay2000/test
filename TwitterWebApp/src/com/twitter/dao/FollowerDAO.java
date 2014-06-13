package com.twitter.dao;

import java.util.List;

public interface FollowerDAO extends DAO{

	public List<Integer> getAllFollowers(int userId);
	public boolean addFollower(int userId, int followerId);
	public boolean deleteFollower(int userId, int followerId);
}
