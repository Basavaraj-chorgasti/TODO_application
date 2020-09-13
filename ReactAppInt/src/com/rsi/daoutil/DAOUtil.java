package com.rsi.daoutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOUtil {

	public static Connection getDBConnection() throws SQLException, ClassNotFoundException {
		
		//Database Connection
		final String  dbDriver = "com.mysql.cj.jdbc.Driver";
		final String dbURL = "jdbc:mysql://localhost:3306/todo";
		
		//Database name to access
		System.out.println("Inside db");
		final String dbUsername = "root";
		final String dbPassword = "root";

		Class.forName(dbDriver);
		Connection con = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
		return con;
	}

}
