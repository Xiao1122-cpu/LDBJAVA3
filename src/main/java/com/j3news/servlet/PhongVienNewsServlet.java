package com.j3news.servlet;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.j3news.dao.CategoryDAO;
import com.j3news.dao.NewsDAO;
import com.j3news.entity.Category;
import com.j3news.entity.News;
import com.j3news.entity.User;

/**
 * Servlet implementation class PhongVienNewsServlet
 */
@MultipartConfig()
@WebServlet({"/phong-vien/tin-tuc", "/phong-vien/tin-tuc/them-moi", "/phong-vien/tin-tuc/chinh-sua", "/phong-vien/tin-tuc/xoa"})
public class PhongVienNewsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PhongVienNewsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			response.sendRedirect(request.getContextPath() + "/dang-nhap");
			return;
		}
		String uriString = request.getRequestURI();
		if (uriString.contains("them-moi")) {
			List<Category>categories = CategoryDAO.findAll();
			request.setAttribute("categories", categories);
			request.getRequestDispatcher("/views/client/news/add.jsp").forward(request, response);
		}else if (uriString.contains("chinh-sua")) {
			String idString = request.getParameter("id");
			int id = Integer.parseInt(idString);
			News news = NewsDAO.findById(id);
			request.setAttribute("news", news);
			List<Category>categories = CategoryDAO.findAll();
			request.setAttribute("categories", categories);
 			request.getRequestDispatcher("/views/client/news/edit.jsp").forward(request, response);
		}else if (uriString.contains("xoa")) {
			String idString = request.getParameter("id");
			int id = Integer.parseInt(idString);
			NewsDAO.delete(id);
			response.sendRedirect(request.getContextPath() + "/phong-vien/tin-tuc");
			
		}else {
			List<News> list = NewsDAO.findByAuthor(user.getId());
			request.setAttribute("list", list);
			request.getRequestDispatcher("/views/client/news/list.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			response.sendRedirect(request.getContextPath() + "/dang-nhap");
			return;
		}
		String uriString = request.getRequestURI();
		if (uriString.contains("them-moi")) {
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String categoryId = request.getParameter("categoryId");
			Part part = request.getPart("image");
			String fileName = part.getSubmittedFileName();
			String realPath = request.getServletContext().getRealPath("/images/" + fileName);
			if (!Files.exists(Path.of(realPath))) {
				Files.createDirectories(Path.of(realPath));
			}
			part.write(realPath);
			
			News news = new News();
			news.setTitle(title);
			news.setContent(content);
			news.setImage(fileName);
			news.setCategoryId(Integer.parseInt(categoryId));
			news.setAuthor(user.getId());
			news.setPostedDate(new Date());
			int rs = NewsDAO.insert(news);
			if (rs > 0) {
				request.setAttribute("message", "Thêm mới thành công");
			}else {
				request.setAttribute("error", "Thêm mới thất bại");
			}
			request.getRequestDispatcher("/views/client/news/add.jsp").forward(request, response);
		}else {
			//xử lý update
			String idString = request.getParameter("id");
			int id = Integer.parseInt(idString);
			News news = NewsDAO.findById(id);
 			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String categoryId = request.getParameter("categoryId");
			news.setTitle(title);
			news.setCategoryId(Integer.parseInt(categoryId));
			news.setContent(content);
			news.setPostedDate(new Date());
			news.setHome(false);
			
			Part part = request.getPart("image");
			String fileName = part.getSubmittedFileName();
			if (!fileName.isEmpty()) {
				String realPath = request.getServletContext().getRealPath("/images/" + fileName);
				if (!Files.exists(Path.of(realPath))) {
					Files.createDirectories(Path.of(realPath));
				}
				part.write(realPath);
				news.setImage(fileName);
			}
			int rs = NewsDAO.update(news);
			if (rs > 0) {
				request.setAttribute("message", "Cập nhật thành công");
			}else {
				request.setAttribute("error", "Cập nhật thất bại");
			}
			request.setAttribute("news", news);
			List<Category>categories = CategoryDAO.findAll();
			request.setAttribute("categories", categories);
			request.getRequestDispatcher("/views/client/news/edit.jsp").forward(request, response);
			
			
		}
	}

}
