package com.rsi.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rsi.bean.Total;
import com.rsi.daoutil.DAOUtil;

public class TotalReportDao {
	static TotalReportDao totalreport = null;

	public static TotalReportDao getInstance() {
		if (totalreport == null) {
			totalreport = new TotalReportDao();
		}
		return totalreport;
	}

	public static Total totalreport() throws ClassNotFoundException, SQLException {
		
		Connection con = DAOUtil.getDBConnection();
		Total total=null;
		try {
		long millis=System.currentTimeMillis();  
        Date cuurentdate=new Date(millis);
        Date oldDate = new Date(millis-7*24*60*60*1000);
        System.out.println(cuurentdate+"''"+oldDate);
		int activeuser = activeUser(oldDate,cuurentdate,con);
		int newuser = newUser(oldDate,cuurentdate,con);
		int totaltask = totalTask(oldDate,cuurentdate,con);
		total = new Total(activeuser, newuser, totaltask);
		
		}
		finally{
			try {
		
			if (con != null) {
				con.close();
				con = null;
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}
		return total;

	}

	public static int activeUser(Date oldDate,Date currentdate,Connection con) {
		int activeuser = 0;

		
		PreparedStatement s = null;
		ResultSet rs = null;
		try {
			
			String sql = "SELECT count(*) as count FROM users where activedate between ? and ?";
		
			s = con.prepareStatement(sql);
			s.setDate(1, oldDate);
			s.setDate(2, currentdate);
			
		
			rs = s.executeQuery();
			
			
			while (rs.next()) {
				activeuser = rs.getInt("count");
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
				if (s != null) {
					s.close();
				}
			} catch (SQLException se2) {
			}
			
		}

		return activeuser;
	}
	public static int newUser(Date oldDate,Date currentdate,Connection con) {
		int newuser = 0;

		
		PreparedStatement s = null;
		ResultSet rs = null;
		try {
			
			String sql = "SELECT count(*) as count FROM users where dateoflogin between ? and ?";
		
			s = con.prepareStatement(sql);
			s.setDate(1, oldDate);
			s.setDate(2, currentdate);
			
		
			rs = s.executeQuery();
			
			
			while (rs.next()) {
				newuser = rs.getInt("count");
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
				if (s != null) {
					s.close();
				}
			} catch (SQLException se2) {
			}
			
		}

		return newuser;
	}
	public static int totalTask(Date oldDate,Date currentdate,Connection con) {
		int totaltask = 0;

		
		PreparedStatement s = null;
		ResultSet rs = null;
		try {
			
			String sql = "SELECT count(*) as count FROM task where tasKdate between ? and ?";
		
			s = con.prepareStatement(sql);
			s.setDate(1, oldDate);
			s.setDate(2, currentdate);
			
		
			rs = s.executeQuery();
			
			
			while (rs.next()) {
				totaltask = rs.getInt("count");
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
				if (s != null) {
					s.close();
				}
			} catch (SQLException se2) {
			}
			
		}

		return totaltask;
	}

}



