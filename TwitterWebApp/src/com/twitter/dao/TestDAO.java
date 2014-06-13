package com.twitter.dao;

import com.twitter.dao.impl.UserDAOImpl;

public class TestDAO {

	public static void main(String[] args) {
		
		UserDAOImpl dao = new UserDAOImpl();
		dao.findById(1);

	}

}
