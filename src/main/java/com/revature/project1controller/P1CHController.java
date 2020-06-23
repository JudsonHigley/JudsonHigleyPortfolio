package com.revature.project1controller;

import javax.servlet.http.HttpServletRequest;

public class P1CHController {

	public static String customerHist(HttpServletRequest req){
		req.getSession().setAttribute("historycust", req.getParameter("historycust"));
		return "resources/html/customerhistory.html";
	}
}
