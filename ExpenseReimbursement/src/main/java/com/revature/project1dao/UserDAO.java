package com.revature.project1dao;

import java.util.List;

import com.revature.model.User;

public interface UserDAO extends DAO {
	
	List<User> getAllUsers();
	
	String getUserRole(int id);
}
