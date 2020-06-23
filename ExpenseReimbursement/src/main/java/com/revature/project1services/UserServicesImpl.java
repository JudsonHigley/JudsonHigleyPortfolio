package com.revature.project1services;

import java.util.List;

import com.revature.model.Ticket;
import com.revature.model.User;
import com.revature.project1dao.UserDAOImpl;

public class UserServicesImpl implements UserServices {
	
	public static final UserDAOImpl udi = new UserDAOImpl();
	public static final TicketServicesImpl tsi = new TicketServicesImpl();
	
	@Override
	public boolean loginVerify(String uname, String pword) {
		List<User> userList = udi.getAllUsers();
		boolean verified = false;
		for(User u: userList) {
			if(u.getUsername().equals(uname)&& u.getPassword().equals(pword)) {
				verified = true;
			}
		}
		return verified;
	}

	@Override
	public User getSingleUser(String uname, String pword) {
		if(loginVerify(uname, pword)) {
			List<User> userList = udi.getAllUsers();
			for(User u: userList) {
				if(u.getUsername().contentEquals(uname)) {
					return u;
				}
			}
		}
		return null;
	}
	
	@Override
	public List<User> getAllUsers() {
		return udi.getAllUsers();
	}
	
	@Override
	public String[] getFirstAndLastName(int id) {
		List<User> userList = udi.getAllUsers();
		Ticket t = tsi.getSingleOpenTicket(id);
		String uname = t.getTicketOwner();
		for(User u: userList) {
			if(u.getUsername().contentEquals(uname)) {
				String temp = u.getFirstName() + " " + u.getLastName();
				return temp.split(" ");
			}
		}
		return new String[0];
	}

}
