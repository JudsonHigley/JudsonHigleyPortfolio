package com.revature.project1services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Ticket;
import com.revature.project1dao.TicketDAOImpl;
import com.revature.project1dao.UserDAOImpl;

public class TicketServicesImpl implements TicketServices {
	
	public static final TicketDAOImpl tdi = new TicketDAOImpl();
	public static final UserDAOImpl udi = new UserDAOImpl();

	@Override
	public List<Ticket> getAllOpenTickets() {
		List<Ticket> ticketList = tdi.getAllTickets();
		List<Ticket> openList = new ArrayList<>();
		for(Ticket t : ticketList) {
			if(t.getTicketStatus().contentEquals("Not Yet Evaluated")) {
				openList.add(t);
			}
		}
		return openList;
	}

	@Override
	public List<Ticket> getSingleUserTickets(String username) {
		List<Ticket> userTicketsList = new ArrayList<>();
		List<Ticket> ticketList = tdi.getAllTickets();
		for(Ticket t : ticketList) {
			if(t.getTicketOwner().equalsIgnoreCase(username)) {
				userTicketsList.add(t);
			}
		}
		return userTicketsList;
	}

	@Override
	public void denyTicket(String username, String firstname, String lastname) {
		tdi.denyTicket(username, firstname, lastname);
		
	}

	@Override
	public void approveTicket(String username, String firstname, String lastname) {
		tdi.approveTicket(username, firstname, lastname);
		
	}

	@Override
	public void addTicket(BigDecimal amount, String elaboration, byte[] image, String firstname,
			String lastname, String rtype) {
		tdi.addTicket(amount, elaboration, image, firstname, lastname, rtype);
		
	}

	@Override
	public Ticket getSingleOpenTicket(int ticketId) {
		List<Ticket> ticketList = getAllOpenTickets();
		for(Ticket t: ticketList) {
			if(t.getTicketId() == ticketId) {
				return t;
			}
		}
		return new Ticket();
	}


}
