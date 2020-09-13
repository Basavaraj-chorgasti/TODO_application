package com.rsi.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rsi.bean.Task;
import com.rsi.bean.user;
import com.rsi.daoutil.DAOUtil;

public class DAOeditTask {
	static DAOeditTask editTask = null;

	public static DAOeditTask getInstance() {
		if (editTask == null) {
			editTask = new DAOeditTask();
		}
		return editTask;
	}

	public static Task editTask(int id) throws ClassNotFoundException, SQLException {

		Connection con = DAOUtil.getDBConnection();
		Task result = etask(id,con);
		return result;

	}

	public static Task etask(int id,Connection con) {
		Task TaskObj = null;
		

		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			
			final String SQL_EDIT = "SELECT *  FROM TASK WHERE ID=?";


			stmt = con.prepareStatement(SQL_EDIT);
			stmt.setInt(1, id);
		
			rs = stmt.executeQuery();
			while (rs.next()) {
				int id1 = rs.getInt("id");
				String taskname = rs.getString("taskname");
				String taskd = rs.getString("taskd");
				String tasks = rs.getString("tasks");
				Date tasKdate = rs.getDate("tasKdate");
				int uid = rs.getInt("uid");
			TaskObj = new Task(id1,taskname,taskd,tasks,uid,tasKdate);


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

		return TaskObj;
	}
}