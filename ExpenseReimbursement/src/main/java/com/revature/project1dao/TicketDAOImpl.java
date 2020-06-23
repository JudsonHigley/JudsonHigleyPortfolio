package com.revature.project1dao;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Ticket;

public class TicketDAOImpl implements TicketDAO {
	
	// Explicit loading of the driver necessary because of Maven dependency issues.
	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void addTicket(BigDecimal amount, String elaboration, byte[] image, String firstname,
			String lastname, String rtype) {
		String sql = "{? = call add_newticket(?,?,?,?,?,?)}";
		try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				CallableStatement cs = conn.prepareCall(sql);) {
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setBigDecimal(2, amount);
			cs.setString(3, elaboration);
			cs.setBytes(4, image);
			cs.setString(5, firstname);
			cs.setString(6, lastname);
			cs.setString(7, rtype);
			cs.execute();
		} catch (SQLException e) {
			logger.warn(e);
		}
	}

	@Override
	public void approveTicket(String username, String firstname, String lastname) {
		String sql = "{? = call approve_ticket(?,?,?)}";
		try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				CallableStatement cs = conn.prepareCall(sql);) {
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setString(2, username);
			cs.setString(3, firstname);
			cs.setString(4, lastname);
			cs.execute();
		} catch (SQLException e) {
			logger.warn(e);
		}

	}

	@Override
	public void denyTicket(String username, String firstname, String lastname) {
		String sql = "{? = call deny_ticket(?,?,?)}";
		try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				CallableStatement cs = conn.prepareCall(sql);) {
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setString(2, username);
			cs.setString(3, firstname);
			cs.setString(4, lastname);
			cs.execute();
		} catch (SQLException e) {
			logger.warn(e);
		}

	}

	@Override
	public String getTicketStatus(int id) {
		String sql = "{? = call retrieve_status(?)}";
		String status = "";
		try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				CallableStatement cs = conn.prepareCall(sql)) {
			
			
			
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setInt(2, id);
			cs.execute();
			status = cs.getString(1);
		} catch (SQLException e) {
			logger.warn(e);
		}
		return status;
		
	}

	@Override
	public String getTicketType(int id) {
		String sql = "{? = call retrieve_type(?)}";
		String type = "";
		try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				CallableStatement cs = conn.prepareCall(sql)) {
			
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setInt(2, id);
			cs.execute();
			type = cs.getString(1);
		} catch (SQLException e) {
			logger.warn(e);
		}
		return type;
	}

	@Override
	public String getUserById(int id) {
		String sql = "{? = call retrieve_user(?)}";
		String user = "";
		try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				CallableStatement cs = conn.prepareCall(sql)) {
			
			
			
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setInt(2, id);
			cs.execute();
			user = cs.getString(1);
		} catch (SQLException e) {
			logger.warn(e);
		}
		return user;
		
	}

	@Override
	public List<Ticket> getAllTickets() {
		List<Ticket> ticketList = new ArrayList<>();
		String sql = "select*from tickets";
		try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				Statement s = conn.createStatement();
				ResultSet rs = s.executeQuery(sql);) {
			
			while(rs.next()) {
				int id = rs.getInt(1);
				BigDecimal amount = rs.getBigDecimal(2);
				LocalDateTime subDateTime = rs.getObject(3, LocalDateTime.class);
				LocalDateTime resDateTime = rs.getObject(4, LocalDateTime.class);
				String elab = rs.getString(5);
				byte[] image = rs.getBytes(6);
				String owner = getUserById(rs.getInt(7));
				String evaluator = getUserById(rs.getInt(8));
				String status = getTicketStatus(rs.getInt(9));
				String type = getTicketType(rs.getInt(10));
				ticketList.add(new Ticket(id, amount, subDateTime, resDateTime, elab, image, owner, evaluator, status, type));
			}
			
		} catch (SQLException e1) {
			logger.warn(e1);
		}
		if(ticketList.isEmpty()) {
			return ticketList;
		}
		System.out.println(ticketList);
		return ticketList;
	}

}
