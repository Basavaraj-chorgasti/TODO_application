package com.rsi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.rsi.daoutil.DAOUtil;

public class DAOUpdatestatus {
	static DAOUpdatestatus updatestatus = null;

	public static DAOUpdatestatus getInstance() 
	{
		if (updatestatus == null) 
		{
			updatestatus = new DAOUpdatestatus();
		}
		return updatestatus;
	}

	public static String updatestatus(int id) throws ClassNotFoundException, SQLException {

		Connection con = DAOUtil.getDBConnection();
		String result = update(id,con);
		return result;

	}

	private static String update(int id,Connection con) {

		int status =0;
		PreparedStatement preparedStatement=null;
		String result = null;
		String sql = "UPDATE task SET tasks=? WHERE id=?  " ;
		 
		try {
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, "Complete");
			preparedStatement.setInt(2, id);
		
			
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



