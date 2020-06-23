package com.revature.project1servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class P1MasterServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3015393363189883047L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		System.out.println("in doGet");
		req.getRequestDispatcher(P1RequestHelper.process(req)).forward(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		System.out.println("in doPost");
		req.getRequestDispatcher(P1RequestHelper.process(req)).forward(req, res);
	}
}
