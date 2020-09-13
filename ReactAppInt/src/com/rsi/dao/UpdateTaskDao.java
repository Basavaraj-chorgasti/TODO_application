package com.rsi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.rsi.daoutil.DAOUtil;

public class UpdateTaskDao 
{
	static UpdateTaskDao updateTaskDao = null;

	public static UpdateTaskDao getInstance() 
	{
		if (updateTaskDao == null) 
		{
			updateTaskDao = new UpdateTaskDao();
		}
		return updateTaskDao;
	}

	public static String updateTaskDao(int id,String taskname, String taskd,int uid) throws ClassNotFoundException, SQLException {

		Connection con = DAOUtil.getDBConnection();
		String result = updateTask(id,taskname,taskd,uid,con);
		return result;

	}

	private static String updateTask(int id,String taskname, String taskd,int uid,Connection con) {

		int status =0;
		PreparedStatement preparedStatement=null;
		String result = null;
		String sql = "UPDATE task SET taskname=?,taskd=? WHERE id=?  " ;
		 
		try {
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, taskname);
			preparedStatement.setString(2, taskd);
		
			preparedStatement.setInt(3, id);
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

