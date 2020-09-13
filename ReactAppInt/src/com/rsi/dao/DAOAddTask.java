package com.rsi.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.rsi.daoutil.DAOUtil;

public class DAOAddTask {

	static DAOAddTask addtask = null;

	public static DAOAddTask getInstance() {
		if (addtask == null) {
			addtask = new DAOAddTask();
		}
		return addtask;
	}

	public static String addtask(String taskname, String taskd, int uid) throws ClassNotFoundException, SQLException {

		Connection con = DAOUtil.getDBConnection();
		String result = task(taskname,taskd,uid,  con);
		return result;

	}

	private static String task(String taskname, String taskd, int uid, Connection con) {

		int rs =0;
		PreparedStatement preparedStatement=null;
		String result = null;
		String sql = "INSERT INTO TODO.task (taskname,taskd,tasks,uid,tasKdate)" +
		        "VALUES (?,?,?,?,?)";


		try {
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1,taskname);
			preparedStatement.setString(2, taskd);
			preparedStatement.setString(3,"new");
			
			long millis=System.currentTimeMillis();  
	        Date tasKdate=new Date(millis);
	        //-7 * 24 * 60 * 60 * 1000
			preparedStatement.setInt(4,uid);
			preparedStatement.setDate(5,tasKdate);
			rs=preparedStatement.executeUpdate(); 

			if (rs>=1) {
				System.out.println("insert Successfull");
				result = "insert Successfull";
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
	}

}

