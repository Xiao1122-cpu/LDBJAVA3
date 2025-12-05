package com.j3news.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.j3news.dao.NewsDAO;
import com.j3news.entity.News;
import com.j3news.entity.User;

/**
 * Servlet implementation class AdminNewsServlet
 */
@WebServlet({"/admin/news", "/admin/news/duyet", "/admin/news/xoa", "/admin/news/khoa"})
public class AdminNewsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminNewsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User user = (User) request.getSession().getAttribute("user");
		if (user == null || !user.isRole()) {
			response.sendRedirect(request.getContextPath() + "/dang-nhap");
			return;
		}
		String uriString = request.getRequestURI();
		if (uriString.contains("xoa")) {
			String idString = request.getParameter("id");
			int id = Integer.parseInt(idString);
			NewsDAO.delete(id);
			response.sendRedirect(request.getContextPath() + "/admin/news");
		}else if (uriString.contains("duyet")) {
			String idString = request.getParameter("id");
			int id = Integer.parseInt(idString);
			News news = NewsDAO.findById(id);
			news.setHome(true);
			NewsDAO.update(news);
			response.sendRedirect(request.getContextPath() + "/admin/news");
		}else if (uriString.contains("khoa")) {
			String idString = request.getParameter("id");
			int id = Integer.parseInt(idString);
			News news = NewsDAO.findById(id);
			news.setHome(false);
			NewsDAO.update(news);
			response.sendRedirect(request.getContextPath() + "/admin/news");
		}else {
			List<News> list = NewsDAO.findAll();
			request.setAttribute("list", list);
			request.getRequestDispatcher("/views/admin/news/list.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
