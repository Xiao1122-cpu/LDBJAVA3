package com.j3news.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.StyledEditorKit.BoldAction;

import com.j3news.dao.UserDAO;
import com.j3news.entity.User;

/**
 * Servlet implementation class AuthServlet
 */
@WebServlet({"/dang-nhap", "/dang-ky", "/dang-xuat"})
public class AuthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     * mssv
     * hoten
     */
    public AuthServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uriString = request.getRequestURI();
		System.out.println("uriString: " + uriString);
		
		if (uriString.contains("dang-nhap")) {
			request.getRequestDispatcher("/views/client/login.jsp").forward(request, response);
		}else if (uriString.contains("dang-xuat")) {
			request.getSession().removeAttribute("user");
			response.sendRedirect(request.getContextPath() + "/trang-chu");
		}else {
			request.getRequestDispatcher("/views/client/register.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String uriString = request.getRequestURI();
		if (uriString.contains("dang-nhap")) {
			System.out.println("dang-nhap: " + uriString);
			//xu lý đăng nhập
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			User user = UserDAO.findByEmail(email);
			if (user == null || !user.getPassword().equals(password)) {
				request.setAttribute("message", "Sai thông tin đăng nhập");
				request.getRequestDispatcher("/views/client/login.jsp").forward(request, response);
			}else {
				request.getSession().setAttribute("user", user);
				response.sendRedirect(request.getContextPath() + "/trang-chu");
			}
			
		}else {
			System.out.println("dang-ky: " + uriString);
			//xử lý dang ký
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String repeatPassword = request.getParameter("repeatPassword");
			String fullName = request.getParameter("fullname");
			String genderString = request.getParameter("gender");
			boolean gender = Boolean.parseBoolean(genderString);
			String phone = request.getParameter("phone");
			if (!password.equals(repeatPassword)) {
				request.setAttribute("error", "Mật khẩu không khớp");
				request.getRequestDispatcher("/views/client/register.jsp").forward(request, response);
			}else {
				User user = new User();
				user.setEmail(email);
				user.setPassword(password);
				user.setFullName(fullName);
				user.setGender(gender);
				user.setPhone(phone);
				int rs = UserDAO.insert(user);
				if (rs > 0) {
					response.sendRedirect(request.getContextPath() + "/dang-nhap");
				}else {
					request.setAttribute("message", "Lỗi đăng ký tài khoản");
					request.getRequestDispatcher("/views/client/register.jsp").forward(request, response);
				}
			}
		}
		
	}

}
