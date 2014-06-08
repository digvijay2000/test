import com.twitter.dao.UserDAO;



public class TestDAO {

	public static void main(String[] args) {
		
		UserDAO dao = new UserDAO();
		dao.findById(1);

	}

}
