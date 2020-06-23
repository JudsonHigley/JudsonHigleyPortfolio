package com.revature.project1services;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.revature.model.User;
import com.revature.project1dao.UserDAOImpl;

public class UserServicesImplTest {
	
	public static final UserServicesImpl usi = new UserServicesImpl();
	public static final UserDAOImpl udi = new UserDAOImpl();
	
	@Test
	public void testUserVerified() {
		assertTrue(usi.loginVerify("jimdangle69", "MONSTERTRUCKS"));
	}
	
	@Test
	public void testUserNotVerified() {
		assertFalse(usi.loginVerify("jimdangle69", "lotsofstuff"));
	}
	
	@Test
	public void testGetSingleUser() {
		List<User> userList = udi.getAllUsers();
		User correctUser = null;
		for(User u: userList) {
			if(u.getUsername().contentEquals("jimdangle69")) {
				correctUser = u;
			}
		}
		assertEquals(correctUser, usi.getSingleUser("jimdangle69", "MONSTERTRUCKS"));
	}
	
	@Test
	public void testGetSingleUserNullResponse() {
		assertNull(usi.getSingleUser("jimdangle69", "balls"));
	}

}
