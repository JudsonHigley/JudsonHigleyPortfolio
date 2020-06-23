package com.revature.project1controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Ticket;
import com.revature.model.User;
import com.revature.project1services.TicketServicesImpl;
import com.revature.project1services.UserServicesImpl;

public class P1UserTicketsController {
	
	public static TicketServicesImpl tsi = new TicketServicesImpl();
	public static UserServicesImpl usi = new UserServicesImpl();
	
	public static void getUserTickets(HttpServletRequest req, HttpServletResponse res) throws IOException {
		User u = (User) req.getSession().getAttribute("currentuser");
		List<Ticket> ticketList = tsi.getSingleUserTickets(u.getUsername());
		res.getWriter().write(new ObjectMapper().writeValueAsString(ticketList));
	}
	
	public static void getUserTicketsNonSession(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String username = (String) req.getSession().getAttribute("historycust");
		List<Ticket> ticketList = tsi.getSingleUserTickets(username);
		res.getWriter().write(new ObjectMapper().writeValueAsString(ticketList));
	}
	
	public static void getOpenTickets(HttpServletRequest req, HttpServletResponse res) throws IOException {
		List<Ticket> ticketList = tsi.getAllOpenTickets();
		res.getWriter().write(new ObjectMapper().writeValueAsString(ticketList));
	}
	
	public static void getAllUsers(HttpServletRequest req, HttpServletResponse res) throws IOException {
		List<User> userList = usi.getAllUsers();
		res.getWriter().write(new ObjectMapper().writeValueAsString(userList));
	}
}
