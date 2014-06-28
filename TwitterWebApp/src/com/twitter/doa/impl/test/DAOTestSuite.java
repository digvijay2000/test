package com.twitter.doa.impl.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;



@RunWith(Suite.class)
@Suite.SuiteClasses({
	UserDAOImplTest.class,
//	FollowerDAOImplTest.class,
//	BlockerDAOImplTest.class,
	TweetDAOImplTest.class
//	ReplyDAOImplTest.class
})
public class DAOTestSuite {

}
