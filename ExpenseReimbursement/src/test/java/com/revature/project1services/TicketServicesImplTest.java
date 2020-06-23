package com.revature.project1services;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.revature.model.Ticket;
import com.revature.project1dao.DAO;

public class TicketServicesImplTest implements DAO{
	
	public static final TicketServicesImpl tsi = new TicketServicesImpl();
	
	@Before
	public void setUp() throws Exception {
		BufferedReader br = new BufferedReader(
				(new FileReader("C:\\Users\\judso\\OneDrive\\Documents\\Revature\\Project1DAOTest.sql")));
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			Statement statement = conn.createStatement();

			while (br.ready()) {
				statement.addBatch(br.readLine());
			}
			statement.executeBatch();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			br.close();
		}
	}

	@After
	public void tearDown() throws Exception {
		BufferedReader br = new BufferedReader(
				(new FileReader("C:\\Users\\judso\\OneDrive\\Documents\\Revature\\ClearProj1Tickets.sql")));
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			Statement statement = conn.createStatement();

			while (br.ready()) {
				statement.addBatch(br.readLine());
			}
			statement.executeBatch();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			br.close();
		}
	}

	@Test
	public void testGetAllOpenTickets() {
		List<Ticket> ticketList = tsi.getAllOpenTickets();
		String verify = "";
		for (Ticket t : ticketList) {
			verify = t.getTicketOwner();
		}
		assertEquals("jeebus", verify);
	}
	
	@Test
	public void testGetSingleUserTicketsAllThreeCases() {
		List<Ticket> ticketList = tsi.getSingleUserTickets("jimdangle69");
		String verify = "";
		for (Ticket t : ticketList) {
			verify = verify + t.getTicketType();
		}
		assertEquals("LodgingFoodOther", verify);
	}
	
	@Test
	public void testGetSingleOpenTicket() {
		List<Ticket> ticketList = tsi.getAllOpenTickets();
		int targetTicketId = 0;
		for(Ticket t:ticketList) {
			if(t.getTicketOwner().contentEquals("jeebus")) {
				targetTicketId = t.getTicketId();
			}
		}
		Ticket returnTicket = tsi.getSingleOpenTicket(targetTicketId);
		assertEquals(targetTicketId, returnTicket.getTicketId());
	}
	
	/*
	 * Note: Testing of the approve, deny, and add methods in TicketServicesImpl has been
	 * foregone, owing to the fact that the methods are simply placeholders to allow the 
	 * calling of the associated DAO functions using only services.
	 */
}
