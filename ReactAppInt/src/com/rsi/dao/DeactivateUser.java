package com.rsi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.rsi.daoutil.DAOUtil;

public class DeactivateUser {
	static DeactivateUser deactivateuser = null;

	public static DeactivateUser getInstance() {
		if (deactivateuser == null) {
			deactivateuser = new DeactivateUser();
		}
		return deactivateuser;
	}

	public static String deactivateuser(int userId) throws ClassNotFoundException, SQLException {

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
			preparedStatement.setInt(1,0);
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



