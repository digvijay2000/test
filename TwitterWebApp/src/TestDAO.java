

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import com.twitter.dao.BlockerDAO;
import com.twitter.dao.FollowerDAO;
import com.twitter.dao.ReplyDAO;
import com.twitter.dao.TweetDAO;
import com.twitter.dao.UserDAO;
import com.twitter.dao.impl.BlockersDAOImpl;
import com.twitter.dao.impl.FollowersDAOImpl;
import com.twitter.dao.impl.ReplyDAOImpl;
import com.twitter.dao.impl.TweetDAOImpl;
import com.twitter.dao.impl.UserDAOImpl;
import com.twitter.pojo.Reply;
import com.twitter.pojo.Tweet;
import com.twitter.pojo.User;
import com.twitter.utils.DatabaseUtils;



public class TestDAO {

	public static void main(String[] args) throws ParseException {

		String test = "test";
//		User user = new User();
		UserDAO userDAO = new UserDAOImpl();
		User user =userDAO.findById(3);
		System.out.println(user.getEmail_Id());
		//		User user = new User();
		//		user.setFirst_Name("htf");
		//		user.setPassword("lkn");
		////		String dobString = "12-02-1990";
		////		SimpleDateFormat format = new SimpleDateFormat("dd-mm-yyyy");
		////		Date dob = format.parse(dobString);
		//			
		//		Date dob =	DatabaseUtils.stringToDate("10-12-1990", "dd-mm-yyyy");
		//			user.setDob(dob);
		//		user.setEmail_Id("kugh");
		//		user.setFirst_Name("kjjbjl");
		//		user.setLast_Name(test);
		//		user.setGender("M");
		//		user.setPhone_No(899879878);
		//		user.setStatus("heya whatsup");
		//		userDAO.addUser(user);
		//		User user2 =userDAO.findByName("kugh");
		//		System.out.println(user2.getFirst_Name());
		//		System.out.println(userDAO.deleteUser(2));

		//		 user = userDAO.findById(4);
		//		 userDAO.deleteUser(4);
		//		user.setStatus("let the game begin");
		//		user.setPassword("password");

		//	    userDAO.updateUser(user);

//		FollowerDAO followerDao = new FollowersDAOImpl();

		//		followerDao.addFollower(5, 3);
		//		followerDao.addFollower(6, 5);
//		List<Integer> list;
//		list = followerDao.getAllFollowers(3);
		

//		ListIterator<Integer> iterator = list.listIterator();
//
//		while(iterator.hasNext()){
//			Integer element = iterator.next();
//			System.out.println(element);
//		}
//
//		followerDao.deleteFollower(6, 3);
//		
		

//		System.out.println("after deleting");
//		
//	
//
//		while(iterator.hasPrevious()){
//			Integer element = iterator.previous();
//			System.out.println(element);
//		}

		//		followerDao.deleteFollower(3, 5);




//		BlockerDAO blockerDAO = new BlockersDAOImpl();
////		blockerDAO.addBlocker(3, 5);
//		blockerDAO.deleteBlocker(3, 5);
//		Tweet tweet = new Tweet();

//		TweetDAO tweetDAO = new TweetDAOImpl(tweet);
//		tweetDAO.addTweet(3, "game is on");
//		List<Integer> list = new ArrayList<Integer>();
//		list = tweetDAO.getAllTweets(3);
//		ListIterator<Integer> iterator = list.listIterator();
//		while(iterator.hasNext()){
//			tweet = tweetDAO.findById(iterator.next());
//			System.out.println(tweet.getTweetText());
//		}
		
		Reply reply = new Reply();
		ReplyDAO replyDAO = new ReplyDAOImpl();
//		replyDAO.addReply(5, "they will bounce back", 1);
		
//		Reply reply = replyDAO.findById(1);
//		System.out.println(reply.getReplyText());
		
		List<Integer> list = new ArrayList<Integer>();
		replyDAO.deleteReply(1);
		list=replyDAO.getAllReplies(1);
		
		ListIterator<Integer> iterator = list.listIterator();
		while(iterator.hasNext()){
			reply = replyDAO.findById(iterator.next());
			System.out.println(reply.getReplyText());
		}
		
		
		
		
		


	}

}
