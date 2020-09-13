package com.rsi.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rsi.bean.Login;
import com.rsi.bean.user;
import com.rsi.daoutil.DAOUtil;

public class DAOCheck {
	static DAOCheck Check = null;

	public static DAOCheck getInstance() {
		if (Check == null) {
		Check = new DAOCheck();
		}
		return Check;
	}

	public static Login daoCheck(String email,String password) throws ClassNotFoundException, SQLException {

		Connection con = DAOUtil.getDBConnection();
		Login login = checkuser(email,password,con);
			update(login,con);
		return login;

	}

	private static void update(Login login, Connection con) {
		
		// TODO Auto-generated method stub
		int id = login.getId();
		int activate = login.getIsactivate();
		PreparedStatement stmt = null;
		int rs = 0;
		try {
			if(activate==1)
			{
			final String SQL_EDIT = "UPDATE users set activedate=? where id=?";
			long millis=System.currentTimeMillis();  
	        Date activedate=new Date(millis);
			
			stmt = con.prepareStatement(SQL_EDIT);
			stmt.setDate(1, activedate);
			stmt.setInt(2, id);
		
			rs = stmt.executeUpdate();
			}

			
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
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

		
	}
		
		
		
	

	public static Login checkuser(String email,String password,Connection con) {
		Login login = null; 
		int id=0,isactivate=0;
   
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			
			final String SQL_EDIT = "SELECT id,isactivate  FROM USERS WHERE email=? and password=?";


			stmt = con.prepareStatement(SQL_EDIT);
			stmt.setString(1, email);
			stmt.setString(2, password);
		
			rs = stmt.executeQuery();
			while (rs.next()) {
				 id = rs.getInt("id");
				 isactivate = rs.getInt("isactivate");
				}
			login = new Login(id,isactivate);
			
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
			
		}

		return login;
	}
}
