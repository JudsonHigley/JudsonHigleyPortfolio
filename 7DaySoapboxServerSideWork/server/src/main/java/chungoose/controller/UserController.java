package chungoose.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import chungoose.dao.UserDao;
import chungoose.model.User;
import chungoose.service.UserService;

@RestController
@RequestMapping(path="/user") 
@CrossOrigin(origins="*")
public class UserController {
	
	private UserDao userDao;
	private UserService userServ;
	
	public UserController() {
		// No-args
	}
	
	@Autowired
	public UserController(UserDao userDao, UserService userServ) {
		super();
		this.userDao = userDao;
		this.userServ = userServ;
	}

	@GetMapping("/get.goose")
	public User getUserById(int id) {
		return userDao.selectById(id);
	}
	
	@GetMapping("/all.goose")
	public List<User> getAllUsers() {
		System.out.println("in user controller");
		return userDao.selectAll();
	}

	@PostMapping("/create.goose")
	public void createUser(User user) {
		userDao.insert(user);
	}

	
	@PostMapping("/update.goose")
	public void updateUser(User user) {
		userDao.update(user);
	}
	
	@PostMapping(value="/login.goose")
	public User login(@RequestBody Map<String,String> user) {
		return userServ.verifyLogin(user.get("username"), user.get("password"));
	}
	
	@PostMapping(value="/register.goose")
	public boolean register(@RequestBody Map<String,String> user) {
		return userServ.register(user.get("username"), user.get("password"), user.get("email"));
	}

}
