package com.j3news.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.j3news.entity.News;
import com.j3news.util.JdbcUtil;

public class NewsDAO {
	//Thêm mới
	public static int insert(News news) {
		int rs = 0;
		String sql = "insert into news(title, content, image, posteddate, author, viewcount, category_id, home) values(?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			rs = JdbcUtil.executeUpdate(sql, news.getTitle(), news.getContent(), news.getImage(), news.getPostedDate(), news.getAuthor(), news.getViewCount(), news.getCategoryId(), news.isHome());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return rs;
	}
	//Cập nhật
	public static int update(News news) {
		int rs = 0;
		String sql = "update news set title = ?, content = ?, image = ?, posteddate = ?, author = ?, viewcount = ?, category_id = ?, home  = ? where id = ?";
		try {
			rs = JdbcUtil.executeUpdate(sql, news.getTitle(), news.getContent(), news.getImage(), news.getPostedDate(), news.getAuthor(), news.getViewCount(), news.getCategoryId(), news.isHome(), news.getId());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return rs;
	}
	
	//xóa
	public static int delete(int id) {
		int rs = 0;
		String sql = "delete from news where id = ?";
		try {
			rs = JdbcUtil.executeUpdate(sql, id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return rs;
	}
	
	//lay danh sách theo userId
	public static List<News> findByAuthor(int author) {
		List<News> list = new ArrayList<News>();
		String sql = "select * from news where author=? order by id desc";
		try {
			ResultSet rs = JdbcUtil.executeQuery(sql, author);
			while(rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String image = rs.getString("image");
				Date postedDate = rs.getDate("posteddate");
				int viewCount = rs.getInt("viewcount");
				int categoryId = rs.getInt("category_id");
				boolean home = rs.getBoolean("home");
				News news = new News(id, title, content, image, postedDate, author, viewCount, categoryId, home);
				list.add(news);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	
	//lấy theo id
	public static News findById(int id) {
		News news = null;
		String sql = "select * from news where id=?";
		try {
			ResultSet rs = JdbcUtil.executeQuery(sql, id);
			while(rs.next()) {
				int author = rs.getInt("author");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String image = rs.getString("image");
				Date postedDate = rs.getDate("posteddate");
				int viewCount = rs.getInt("viewcount");
				int categoryId = rs.getInt("category_id");
				boolean home = rs.getBoolean("home");
				news = new News(id, title, content, image, postedDate, author, viewCount, categoryId, home);
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return news;
		
	}
	
	//lay all news
	public static List<News> findAll() {
		List<News> list = new ArrayList<News>();
		String sql = "select * from news order by id desc, home desc";
		try {
			ResultSet rs = JdbcUtil.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("id");
				int author = rs.getInt("author");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String image = rs.getString("image");
				Date postedDate = rs.getDate("posteddate");
				int viewCount = rs.getInt("viewcount");
				int categoryId = rs.getInt("category_id");
				boolean home = rs.getBoolean("home");
				News news = new News(id, title, content, image, postedDate, author, viewCount, categoryId, home);
				list.add(news);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	//lay danh tin tuc trang
	public static List<News> findByHome() {
		List<News> list = new ArrayList<News>();
		String sql = "select * from news where home=1 order by id desc";
		try {
			ResultSet rs = JdbcUtil.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("id");
				int author = rs.getInt("author");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String image = rs.getString("image");
				Date postedDate = rs.getDate("posteddate");
				int viewCount = rs.getInt("viewcount");
				int categoryId = rs.getInt("category_id");
				boolean home = rs.getBoolean("home");
				News news = new News(id, title, content, image, postedDate, author, viewCount, categoryId, home);
				list.add(news);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	
	//lay 5 tin mới nhât
	public static List<News> findTop5News() {
		List<News> list = new ArrayList<News>();
		String sql = "select top 5 * from news where home=1 order by id desc";
		try {
			ResultSet rs = JdbcUtil.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("id");
				int author = rs.getInt("author");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String image = rs.getString("image");
				Date postedDate = rs.getDate("posteddate");
				int viewCount = rs.getInt("viewcount");
				int categoryId = rs.getInt("category_id");
				boolean home = rs.getBoolean("home");
				News news = new News(id, title, content, image, postedDate, author, viewCount, categoryId, home);
				list.add(news);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	//lay 5 tin có lượt view nhiều nhất
		public static List<News> findTop5NewsViews() {
			List<News> list = new ArrayList<News>();
			String sql = "select top 5 * from news where home=1 order by viewcount desc";
			try {
				ResultSet rs = JdbcUtil.executeQuery(sql);
				while(rs.next()) {
					int id = rs.getInt("id");
					int author = rs.getInt("author");
					String title = rs.getString("title");
					String content = rs.getString("content");
					String image = rs.getString("image");
					Date postedDate = rs.getDate("posteddate");
					int viewCount = rs.getInt("viewcount");
					int categoryId = rs.getInt("category_id");
					boolean home = rs.getBoolean("home");
					News news = new News(id, title, content, image, postedDate, author, viewCount, categoryId, home);
					list.add(news);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return list;
		}
}
