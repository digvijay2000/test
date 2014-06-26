package com.twitter.dao;

import com.twitter.pojo.User;

public interface UserDAO extends DAO{
	public User findById(int userId);
	public User findByName(String string);
	public int addUser(User user);
	public int deleteUser(int UserId);
	public int updateUser(User user);

}
