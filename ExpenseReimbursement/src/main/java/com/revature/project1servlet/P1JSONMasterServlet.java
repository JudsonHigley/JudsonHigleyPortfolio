package com.revature.project1servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class P1JSONMasterServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -967547238672135745L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
			P1JSONRequestHelper.process(req, res);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
			P1JSONRequestHelper.process(req, res);
	}
}
