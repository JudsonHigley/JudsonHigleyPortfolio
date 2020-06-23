package com.revature.project1controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.revature.model.User;
import com.revature.project1services.UserServicesImpl;

public class P1LoginController {
	
	static UserServicesImpl usi = new UserServicesImpl();
	
	public static String login(HttpServletRequest req) {

		if (!req.getMethod().equals("POST")) {
			return "resources/html/index.html";
		}

		String name = req.getParameter("username");
		String pass = req.getParameter("password");
		User u = usi.getSingleUser(name, pass);
		System.out.println(u);

		if (u == null) {
			return "wrongcreds.change";
		} else if(u.getUserType().contentEquals("Employee")){
			req.getSession().setAttribute("currentuser", u);
			System.out.println(req.getSession().getId());
			return "employee.change";
		} else {
			HttpSession currentSession = req.getSession();
			currentSession.setAttribute("currentuser", u);
			System.out.println(req.getSession().getId());
			return "fa.change";
		}
	}
}
