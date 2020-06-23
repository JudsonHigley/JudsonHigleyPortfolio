package com.revature.project1dao;

import static org.junit.Assert.*;

import java.util.List;
import org.junit.Test;

import com.revature.model.User;

public class UserDAOImplTest implements DAO{
	
	public static final UserDAOImpl udi = new UserDAOImpl();

	@Test
	public void testGetAllUsers() {
		List<User> userList = udi.getAllUsers();
		System.out.println(userList);
		assertEquals(5, userList.size());
	}
	
	@Test
	public void testGetUserRole() {
		assertEquals("Employee", udi.getUserRole(5));
	}

}
