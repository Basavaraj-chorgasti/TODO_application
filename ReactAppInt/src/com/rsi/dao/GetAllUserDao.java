package com.rsi.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

import com.rsi.bean.user;
import com.rsi.daoutil.DAOUtil;

public class GetAllUserDao {

	static GetAllUserDao getAllUserDao = null;

	public static GetAllUserDao getInstance() {
		if (getAllUserDao == null) {
			getAllUserDao = new GetAllUserDao();
		}
		return getAllUserDao;
	}

	public static List<user> GetAllUserDao() throws ClassNotFoundException, SQLException {

		Connection con = DAOUtil.getDBConnection();
		List<user> result = getAllUser(con);
		return result;

	}

	public static List<user> getAllUser(Connection con) {
		user tempUserObj = null;
		List<user> userList = null;

		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			

			String sql = "SELECT * FROM users";
		
			stmt = con.prepareStatement(sql);
		
			rs = stmt.executeQuery();
			
			userList = new ArrayList<>();
			while (rs.next()) {
				int id = rs.getInt("id");
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastname");
				String email = rs.getString("email");
				Date dob = rs.getDate("dob");
				String gender = rs.getNString("gender");
				String password = rs.getString("password");
				int isactivate = rs.getInt("isactivate");
				Date dateoflogin = rs.getDate("dateoflogin");
				Date activedate = rs.getDate("activedate");
				tempUserObj = new user(id,firstName,lastName,email,dob,gender,password,isactivate,dateoflogin,activedate);
				userList.add(tempUserObj);
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException se2) {
			}
			try {
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		return userList;
	}
}
