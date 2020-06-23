package com.revature.project1servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.project1controller.P1NewTicketController;
import com.revature.project1controller.P1StatusChangeController;
import com.revature.project1controller.P1UserTicketsController;
import com.revature.project1services.TicketServicesImpl;

public class P1JSONRequestHelper {
	
	public static final TicketServicesImpl tsi = new TicketServicesImpl();
	
	public static void process(HttpServletRequest req, HttpServletResponse res) throws IOException {
		switch(req.getRequestURI()) {
		case "/Project1BackendWAR/getuserhistory.json":
			System.out.println("In employee json helper");
			P1UserTicketsController.getUserTickets(req, res);
			break;
		case "/Project1BackendWAR/getnotsessionuserhistory.json":
			System.out.println("In customer history json helper");
			P1UserTicketsController.getUserTicketsNonSession(req, res);
			break;
		case "/Project1BackendWAR/getallopen.json":
			System.out.println("In fa json helper");
			P1UserTicketsController.getOpenTickets(req, res);
			break;
		case "/Project1BackendWAR/newticket.json":
			System.out.println("In new ticket helper");
			P1NewTicketController.insertTicket(req, res);
			break;
		case "/Project1BackendWAR/statuschange.json":
			System.out.println("In statuschange helper");
			P1StatusChangeController.changeStatus(req, res);
			break;
		case "/Project1BackendWAR/getusers.json":
			System.out.println("in get users helper");
			P1UserTicketsController.getAllUsers(req, res);
			break;
		default:
			P1UserTicketsController.getUserTickets(req, res);
		}
	}
}
