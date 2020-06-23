package com.revature.project1controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.project1services.TicketServicesImpl;

public class P1NewTicketController {
	
	public static TicketServicesImpl tsi = new TicketServicesImpl();
	
	public static void insertTicket(HttpServletRequest req, HttpServletResponse res) throws IOException {
		@SuppressWarnings("rawtypes")
		Enumeration requestParameters = req.getParameterNames();
		String[] tempList = new String[5];
		int pos = 0;
		while (requestParameters.hasMoreElements()) {
		    String element = (String) requestParameters.nextElement();
		    System.out.println(element);
		    String value = req.getParameter(element);
		    System.out.println(value);
		    tempList[pos] = value;
		    pos++;
		}
		System.out.println(Arrays.deepToString(tempList));
		String[] nameArr = tempList[0].split(" ");
		
		//order they come in: fullname, rType, amount, image, elaboration
		tsi.addTicket(
				BigDecimal.valueOf(Double.parseDouble(tempList[2])),
				tempList[4],
				tempList[3].getBytes(),
				nameArr[0],
				nameArr[1],
				tempList[1]
				
		);
	}
}
