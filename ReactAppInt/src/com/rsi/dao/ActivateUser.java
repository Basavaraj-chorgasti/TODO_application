package com.rsi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.rsi.daoutil.DAOUtil;

public class ActivateUser {
	static ActivateUser activateuser = null;

	public static ActivateUser getInstance() {
		if (activateuser == null) {
			activateuser = new ActivateUser();
		}
		return activateuser;
	}

	public static String activateuser(int userId) throws ClassNotFoundException, SQLException {

		Connection con = DAOUtil.getDBConnection();
		String result = updateUser(userId, con);
		return result;

	}

	private static String updateUser(int userId, Connection con) {

		int status =0;
		PreparedStatement preparedStatement=null;
		String result = null;
		String sql = "UPDATE users SET isactivate=? WHERE id=?  " ;
		 
		try {
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setInt(1,1);
			preparedStatement.setInt(2, userId);
			status=preparedStatement.executeUpdate(); 

			if (status>0) {
				System.out.println("update Successfully");
				result = "user updated Successfully";
			} 

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

					

			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return result;
	

}}
