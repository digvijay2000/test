package com.twitter.dao;

public interface BlockerDAO extends DAO{
	
	public int addBlocker(int userId, int blockerId);
	public int deleteBlocker(int userId, int blockerId);

}
