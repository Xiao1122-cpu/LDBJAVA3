package com.j3news.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.j3news.dao.CategoryDAO;
import com.j3news.dao.NewsDAO;
import com.j3news.entity.Category;
import com.j3news.entity.News;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/trang-chu")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Category> categories = CategoryDAO.findAll();
		request.setAttribute("categories", categories);
		
		List<News> list = NewsDAO.findByHome();
		List<News> listTop5 = NewsDAO.findTop5News();
		List<News> listTop5Views = NewsDAO.findTop5NewsViews();
		request.setAttribute("list", list);
		request.setAttribute("listTop5", listTop5);
		request.setAttribute("listTop5Views", listTop5Views);
		request.getRequestDispatcher("/views/client/home.jsp").forward(request, response);;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
