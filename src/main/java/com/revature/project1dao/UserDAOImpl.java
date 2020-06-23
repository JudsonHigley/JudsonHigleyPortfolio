package com.revature.project1dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.User;

public class UserDAOImpl implements UserDAO {
	
	@Override
	public List<User> getAllUsers() {
		List<User> userList = new ArrayList<>();
		String sql = "select*from users";
		try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				Statement s = conn.createStatement();
				ResultSet rs = s.executeQuery(sql);) {
			
			while(rs.next()) {
				int userId = rs.getInt(1);
				String username = rs.getString(2);
				String password = rs.getString(3);
				String firstName = rs.getString(4);
				String lastName = rs.getString(5);
				String email = rs.getString(6);
				String userType = getUserRole(rs.getInt(7));
				userList.add(new User(userId, username, password, firstName, lastName, email, userType));
			}
			
		} catch (SQLException e1) {
			logger.warn(e1);
		}
		return userList;
	}

	@Override
	public String getUserRole(int id) {
		String sql = "{? = call retrieve_role(?)}";
		String role = "";
		try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				CallableStatement cs = conn.prepareCall(sql)) {
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setInt(2, id);
			cs.execute();
			role = cs.getString(1);
		} catch (SQLException e) {
			logger.warn(e);
		}
		return role;
		
	}

}
