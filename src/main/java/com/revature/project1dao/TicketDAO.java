package com.revature.project1dao;

import java.math.BigDecimal;
import java.util.List;

import com.revature.model.Ticket;

public interface TicketDAO extends DAO {
	
	List<Ticket> getAllTickets();
	
	String getTicketStatus(int id);
	
	String getTicketType(int id);
	
	String getUserById(int id);

	void addTicket(BigDecimal amount, String elaboration, byte[] image, String firstname, String lastname,
			String rtype);

	void approveTicket(String username, String firstname, String lastname);

	void denyTicket(String username, String firstname, String lastname);
	
}
