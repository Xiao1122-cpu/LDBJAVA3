package com.j3news.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.j3news.entity.Category;
import com.j3news.util.JdbcUtil;

public class CategoryDAO {
	//thêm mới
	public static int insert(String name) {
		int rs = 0;
		String sql = "insert into categories values(?)";
		try {
			rs = JdbcUtil.executeUpdate(sql, name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs; 
	}
	
	//chinh sưa
	public static int update(int id, String name) {
		int rs = 0;
		String sql = "update categories set name = ? where id = ?";
		try {
			rs = JdbcUtil.executeUpdate(sql, name, id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs; 
	}
	
	//Xóa
	public static int delete(int id) {
		int rs = 0;
		String sql = "delete from categories where id = ?";
		try {
			rs = JdbcUtil.executeUpdate(sql, id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs; 
	}
	//Lay tất cả danh sách
	public static List<Category> findAll() {
		List<Category> list = new ArrayList<Category>();
		String sql = "select * from categories order by id desc";
		try {
			ResultSet rs = JdbcUtil.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				Category category = new Category(id, name);
				list.add(category);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	//Lay theo id
	public static Category findById(int id) {
		Category category = null;
		String sql = "select * from categories where id = ?";
		try {
			ResultSet rs = JdbcUtil.executeQuery(sql, id);
			while(rs.next()) {
				String name = rs.getString("name");
				category = new Category(id, name);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return category;
	}
}
