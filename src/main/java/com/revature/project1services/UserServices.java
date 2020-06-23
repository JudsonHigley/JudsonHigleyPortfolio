package com.revature.project1services;

import java.util.List;

import com.revature.model.User;

public interface UserServices {
	boolean loginVerify(String uname, String pword);
	
	User getSingleUser(String uname, String pword);

	String[] getFirstAndLastName(int id);

	List<User> getAllUsers();
}
