package com.j3news.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.j3news.dao.CategoryDAO;
import com.j3news.entity.Category;
import com.j3news.entity.User;

/**
 * Servlet implementation class AdminCategoryServlet
 */
@WebServlet({"/admin/categories","/admin/categories/add", "/admin/categories/edit", "/admin/categories/delete"})
public class AdminCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminCategoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uriString = request.getRequestURI();
		User user = (User) request.getSession().getAttribute("user");
		if (user == null || !user.isRole()) {
			response.sendRedirect(request.getContextPath() + "/dang-nhap");
			return;
		}
		if (uriString.contains("add")) {
			
			request.getRequestDispatcher("/views/admin/categories/add.jsp").forward(request, response);
		}else if (uriString.contains("edit")) {
			String idString = request.getParameter("id");
			int id = Integer.parseInt(idString);
			//Lay category dua id
			Category category = CategoryDAO.findById(id);
			request.setAttribute("category", category);
			request.getRequestDispatcher("/views/admin/categories/edit.jsp").forward(request, response);
		}else if (uriString.contains("delete")) {
			//thực hiện công việc xóa
			String idString = request.getParameter("id");
			int id = Integer.parseInt(idString);
			//xử lý xóa
			int rs = CategoryDAO.delete(id);
			response.sendRedirect(request.getContextPath() + "/admin/categories");
			
		}else {
			List<Category> list = CategoryDAO.findAll();
			request.setAttribute("list", list);
			request.getRequestDispatcher("/views/admin/categories/list.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String uriString = request.getRequestURI();
		if (uriString.contains("add")) {
			System.out.println("Thêm mới");
			String name = request.getParameter("name");
			int rs = CategoryDAO.insert(name);
			if (rs > 0) {
				request.setAttribute("message", "Thêm mới thành công");
			}else {
				request.setAttribute("error", "Thêm mới thất bại");
			}
			request.getRequestDispatcher("/views/admin/categories/add.jsp").forward(request, response);
		}else {
			System.out.println("Cập nhật");
			//xu lu update
			String name = request.getParameter("name");
			String idString = request.getParameter("id");
			int id = Integer.parseInt(idString);
			int rs = CategoryDAO.update(id, name);
			if (rs > 0) {
				request.setAttribute("message", "Cập nhật thành công");
			}else {
				request.setAttribute("error", "Cập nhật thất bại");
			}
			request.getRequestDispatcher("/views/admin/categories/edit.jsp").forward(request, response);
		}
	}

}
