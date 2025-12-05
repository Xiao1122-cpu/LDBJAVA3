package com.j3news.dao;

import java.sql.ResultSet;
import java.util.Date;

import com.j3news.entity.User;
import com.j3news.util.JdbcUtil;

public class UserDAO {
	//Them user
	public static int insert(User user) {
		int rs = 0;
		String sql = "insert into users(email, password, fullname, birthday, gender, phone, role) "
				+ "values(?, ?, ?, ?, ?, ?, ?)";
		try {
			rs = JdbcUtil.executeUpdate(sql, user.getEmail(), 
											user.getPassword(), 
											user.getFullName(), 
											user.getBirthDay(), 
											user.isGender(),
											user.getPhone(), 
											user.isRole());
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return rs;
	}
	
	//lay thong user theo email
	public static User findByEmail(String email) {
		User user = null;
		String sql = "select * from users where email = ?";
		try {
			ResultSet rs =  JdbcUtil.executeQuery(sql, email);
			while(rs.next()) {
				int id = rs.getInt("id");
				String password = rs.getString("password");
				String fullName = rs.getString("fullname");
				Date birthDay = rs.getDate("birthday");
				boolean gender = rs.getBoolean("gender");
				String phone = rs.getString("phone");
				boolean role = rs.getBoolean("role");
				user = new User(id, email, password, fullName, birthDay, gender, phone, role);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return user;
	}
}
