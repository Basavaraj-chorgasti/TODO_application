package com.rsi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rsi.bean.user;
import com.rsi.daoutil.DAOUtil;

public class DeleteSingleTaskDao {
	static DeleteSingleTaskDao deleteSingleTaskDao = null;

	public static DeleteSingleTaskDao getInstance() {
		if (deleteSingleTaskDao == null) {
			deleteSingleTaskDao = new DeleteSingleTaskDao();
		}
		return deleteSingleTaskDao;
	}

	public static int deleteSingleTaskDao(int id) throws ClassNotFoundException, SQLException {

		Connection con = DAOUtil.getDBConnection();
		int result = 0;
		
		try {
			con.setAutoCommit(false);
			 result = deleteSingletask(id, con);
			con.commit();
		} catch (Exception se) {
			se.printStackTrace();
			con.rollback();
			
		} finally {
			try {
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return result;

	}

	public static int deleteSingletask(int id, Connection con) {

		PreparedStatement stmt = null;
		int row = 0;
		
		final String SQL_DELETE = "DELETE FROM TASK WHERE ID=?";
		try {

			stmt = con.prepareStatement(SQL_DELETE);
			stmt.setInt(1, id);
			row = stmt.executeUpdate();

			// rows affected
			System.out.println(row);
			if(row>0) {
				
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
			
		}

		return row;
	}

}

