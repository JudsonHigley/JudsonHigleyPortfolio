package com.revature.project1controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.model.User;
import com.revature.project1services.TicketServicesImpl;
import com.revature.project1services.UserServicesImpl;

public class P1StatusChangeController {
	
public static TicketServicesImpl tsi = new TicketServicesImpl();
public static UserServicesImpl usi = new UserServicesImpl();
	
	public static void changeStatus(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String type = req.getParameter("actionRadios");
		int id = Integer.parseInt(req.getParameter("ticketId"));
		User u = (User) req.getSession().getAttribute("currentuser");
		String[] temp = usi.getFirstAndLastName(id);
		String firstname = temp[0];
		String lastname = temp[1];
		if(type.contentEquals("Approve")) {
			tsi.approveTicket(u.getUsername(), firstname, lastname);
		} else {
			tsi.denyTicket(u.getUsername(), firstname, lastname);
		}
	}
}
