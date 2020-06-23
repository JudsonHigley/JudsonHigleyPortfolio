package com.revature.project1dao;

import org.apache.log4j.Logger;

public interface DAO {
	static String URL = System.getenv("Project1Url");
	static String USERNAME = System.getenv("BankAppUsername");
	static String PASSWORD = System.getenv("BankAppPassword");
	
	final static Logger logger = Logger.getLogger(DAO.class);
}
