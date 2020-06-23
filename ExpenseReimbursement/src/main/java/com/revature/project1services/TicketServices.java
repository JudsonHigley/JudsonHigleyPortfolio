package com.revature.project1services;

import java.math.BigDecimal;
import java.util.List;

import com.revature.model.Ticket;

public interface TicketServices {
	
	List<Ticket> getAllOpenTickets();
	
	List<Ticket> getSingleUserTickets(String username);
	
	Ticket getSingleOpenTicket(int ticketId);

	void denyTicket(String username, String firstname, String lastname);

	void approveTicket(String username, String firstname, String lastname);

	void addTicket(BigDecimal amount, String elaboration, byte[] image, String firstname, String lastname,
			String rtype);
}
