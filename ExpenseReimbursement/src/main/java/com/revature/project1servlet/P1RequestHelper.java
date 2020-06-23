package com.revature.project1servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.revature.project1controller.P1CHController;
import com.revature.project1controller.P1EmployeeHomeController;
import com.revature.project1controller.P1FAHomeController;
import com.revature.project1controller.P1LoginController;

public class P1RequestHelper {
	
	public static String process(HttpServletRequest req) {
		System.out.println(req.getRequestURI());
		
		switch(req.getRequestURI()) {
		// cases correspond to 'action' attribute value in html page
		case "/Project1BackendWAR/login.change":
			System.out.println("in login.change rhelper");
			return P1LoginController.login(req);
		case "/Project1BackendWAR/invalidate.change":
			System.out.println("In invalidation branch");
			HttpSession sess = req.getSession();
			sess.invalidate();
			return null;
		case "/Project1BackendWAR/employee.change":
			System.out.println("in home.change rhelper");
			return P1EmployeeHomeController.employeehome(req);
		
		case "/Project1BackendWAR/fa.change":
			System.out.println("in fa.change rhelper");
			return P1FAHomeController.faHome(req);
		case "/Project1BackendWAR/ch.change":
			System.out.println("in ch.change rhelper");
			return P1CHController.customerHist(req);
		default:
				System.out.println("in default case");
				req.getSession().invalidate();
				return "resources/html/tryagain.html";
		}
	}
}