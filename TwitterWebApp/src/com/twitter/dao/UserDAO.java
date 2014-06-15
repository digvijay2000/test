package com.twitter.dao;

import com.twitter.pojo.User;

public interface UserDAO extends DAO{
	public User findById(int userId);
	public User findByName(String string);
	public boolean addUser(User user);
	public boolean deleteUser(int UserId);
	public boolean updateUser(User user);

}
