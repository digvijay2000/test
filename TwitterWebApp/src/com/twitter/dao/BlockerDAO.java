package com.twitter.dao;

public interface BlockerDAO extends DAO{
	
	public boolean addBlocker(int userId, int blockerId);
	public boolean deleteBlocker(int userId, int blockerId);

}
