

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.twitter.dao.UserDAO;
import com.twitter.dao.impl.UserDAOImpl;
import com.twitter.pojo.User;



public class TestDAO {

	public static void main(String[] args) throws ParseException {
		
		String test = "test";
		
		UserDAO userDAO = new UserDAOImpl();
		User user = new User();
		user.setFirst_Name("abc");
		user.setPassword("qasd");
		String dobString = "12-02-1990";
		SimpleDateFormat format = new SimpleDateFormat("dd-mm-yyyy");
		Date dob = format.parse(dobString);
			user.setDob(dob);
			
		user.setEmail_Id("dafwdsf");
		user.setFirst_Name("dfsd");
		user.setLast_Name(test);
		user.setGender("M");
		user.setPhone_No(987879898);
		user.setStatus("heya whatsup");
		
		
		userDAO.addUser(user);
	

	}

}
