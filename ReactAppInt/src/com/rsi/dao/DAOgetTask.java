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

public class DAOgetTask {

	static DAOgetTask daogettask = null;

	public static DAOgetTask getInstance() {
		if (daogettask == null) {
			daogettask = new DAOgetTask();
		}
		return daogettask;
	}

	public static List<Task> gettask(int uid) throws ClassNotFoundException, SQLException {

		Connection con = DAOUtil.getDBConnection();
		List<Task> result = getAlltask(uid,con);
		return result;

	}

	public static List<Task> getAlltask(int uid,Connection con) {
		Task temptaskObj = null;
		List<Task> TaskList = null;

		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			

			String sql = "SELECT * FROM task where uid=?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, uid);
		
			rs = stmt.executeQuery();
			TaskList = new ArrayList<>();
			while (rs.next()) {
				int id = rs.getInt("id");
				String taskname = rs.getString("taskname");
				String taskd = rs.getString("taskd");
				String tasks = rs.getString("tasks");
				Date tasKdate = rs.getDate("taskdate");
				int Uid = rs.getInt("uid");
				temptaskObj = new Task(id,taskname,taskd,tasks,Uid,tasKdate);
				TaskList.add(temptaskObj);
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

		return TaskList;
	}
}

