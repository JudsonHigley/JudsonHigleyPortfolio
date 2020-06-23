package com.revature.project1dao;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.revature.model.Ticket;
import com.revature.project1services.TicketServicesImpl;

public class TicketDAOImplTest implements DAO {
	
	public static final TicketDAOImpl tdi = new TicketDAOImpl();
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
	public void testGetAllTickets() {
		List<Ticket> ticketList = tdi.getAllTickets();
		assertEquals(4, ticketList.size());
		
	}
	
	@Test
	public void testApproveTicket() {
		tdi.approveTicket("judsonhigley", "Jerret", "Maass");
		List<Ticket> ticketList = tsi.getSingleUserTickets("jeebus");
		for(Ticket t: ticketList) {
			assertEquals("Approved", t.getTicketStatus());
		}
		
	}

	@Test
	public void testDenyTicket() {
		tdi.denyTicket("judsonhigley", "Jerret", "Maass");
		List<Ticket> ticketList = tsi.getSingleUserTickets("jeebus");
		for(Ticket t: ticketList) {
			assertEquals("Denied", t.getTicketStatus());
		}
	}
	
	@Test
	public void testAddTicket() {
		tdi.addTicket(BigDecimal.valueOf(3223.43), "This is some elaboration", null, "Eric", "Something", "Food");
		List<Ticket> checkList = tsi.getSingleUserTickets("javascriptmaster420");
		for (Ticket t: checkList) {
			assertEquals("Not Yet Evaluated", t.getTicketStatus());
		}
	}
	
	@Test
	public void testGetTicketStatus() {
		assertEquals("Not Yet Evaluated", tdi.getTicketStatus(12));
	}

	@Test
	public void testGetUserById() {
		assertEquals("jimdangle69", tdi.getUserById(7));
	}

	@Test
	public void testGetTicketType() {
		assertEquals("Lodging", tdi.getTicketType(9));
	}
}
