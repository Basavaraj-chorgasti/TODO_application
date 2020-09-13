package com.rsi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;

import com.rsi.daoutil.DAOUtil;

public class DAOLogin {

	static DAOLogin loginDao = null;

	public static DAOLogin getInstance() {
		if (loginDao == null) {
			loginDao = new DAOLogin();
		}
		return loginDao;
	}

	public static String daoLogin(String firstName, String lastName,String email, Date dob, String gender, String password,int isactivate,Date dateoflogin,Date activedate) throws ClassNotFoundException, SQLException {

		Connection con = DAOUtil.getDBConnection();
		String result = checkUserNamePass(firstName,lastName,email,dob,gender,password,isactivate,dateoflogin,activedate, con);
		return result;

	}

	private static String checkUserNamePass(String firstName, String lastName,String email, Date dob, String gender, String password,int isactivate,Date dateoflogin,Date activedate,Connection con) {

		int rs =0;
		PreparedStatement preparedStatement=null;
		String result = null;
		String sql = "INSERT INTO TODO.users (firstName,lastName,email,dob,gender,password,isactivate,dateoflogin,activedate)" +
		        "VALUES (?,?,?,?,?,?,?,?,?)";


		try {
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, firstName);
			preparedStatement.setString(2, lastName);
			preparedStatement.setString(3, email);
			preparedStatement.setDate(4,dob);
			preparedStatement.setString(5, gender);
			preparedStatement.setString(6, password);
			preparedStatement.setInt(7, isactivate);
			preparedStatement.setDate(8, dateoflogin);
			preparedStatement.setDate(9, activedate);
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
