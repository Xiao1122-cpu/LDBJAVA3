package com.j3news.entity;

import java.util.Date;

import com.j3news.dao.CategoryDAO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class News {
	int id;
	String title;
	String content;
	String image;
	Date postedDate;
	int author;
	int viewCount;
	int categoryId;
	boolean home;
	
	public String getCategoryName() {
		Category category = CategoryDAO.findById(this.categoryId);
		return category != null?category.getName():"";
	}
}
