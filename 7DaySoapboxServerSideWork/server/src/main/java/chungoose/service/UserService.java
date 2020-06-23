package chungoose.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import chungoose.dao.UserDao;
import chungoose.model.User;

@Service
public class UserService {

	
	private UserDao userDao;
	
	public UserService() {
		// No args
	}
	
	@Autowired
	public UserService(UserDao userDao) {
		super();
		this.userDao = userDao;
	}

	//verifies user login, returns user if correct, returns null if incorrect
	public User verifyLogin(String username, String password) {
		List<User> allUsers = userDao.selectAll();
		for (User current: allUsers) {
			if (current.getUsername().equals(username) && current.getPassword().equals(password)) {
				return current;
			}
		}
		return userDao.selectByLogin(username, password); 
	}
	
	//creates a new user account, returns false if account already exists, returns true if correctly created and inserted into DB
	public boolean register(String uname, String password, String email) {
		List<User> allUsers = userDao.selectAll();
		for (User u: allUsers) {
			if(u.getUsername().equals(uname)) {
				return false;
			}
		}
		User user = new User();
		user.setUsername(uname);
		user.setPassword(password);
		user.setEmail(email);
		userDao.insert(user);
		return true;
	}

}
