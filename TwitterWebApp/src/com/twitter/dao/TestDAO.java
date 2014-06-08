package com.twitter.dao;

public class TestDAO {

	public static void main(String[] args) {
		
		UserDAO dao = new UserDAO();
		dao.findById(1);

	}

}
