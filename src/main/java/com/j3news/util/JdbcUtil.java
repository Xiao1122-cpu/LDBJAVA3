package com.j3news.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcUtil {
	static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	static String dburl = "jdbc:sqlserver://localhost:1433;database=j3news;encrypt=false";
	static String username = "sa";
	static String password = "123";
	
	static{
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(dburl, username, password);
	}
	
	//Xu ly insert, update, delete
	public static int executeUpdate(String sql, Object ... values) throws SQLException {
		Connection connection = getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		for (int i = 0; i < values.length; i++) {
			preparedStatement.setObject(i+1, values[i]);
		}
		return preparedStatement.executeUpdate();
	}
	//xử lý câu select
	public static ResultSet executeQuery(String sql, Object ... values) throws SQLException {
		Connection connection = getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		for (int i = 0; i < values.length; i++) {
			preparedStatement.setObject(i+1, values[i]);
		}
		return preparedStatement.executeQuery();
	}
}
