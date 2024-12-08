package com.shop.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

import com.shop.dao.IUserDao;
import com.shop.dao.Impl.AdminDaoImpl;
import com.shop.dao.Impl.UserDaoImpl;
import com.shop.model.Admin;
import com.shop.model.Message;
import com.shop.model.User;
import com.shop.util.DatabaseConnection;

public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String login = request.getParameter("login");
		if (login.trim().equals("user")) {
			try {
				String userEmail = request.getParameter("user_email");
				String userPassword = request.getParameter("user_password");

				// getting user through entered email and passsword
				IUserDao userDao = new UserDaoImpl(DatabaseConnection.getConnection());
				User user = userDao.getUserByEmailPassword(userEmail, userPassword);

				// storing current user in session
				HttpSession session = request.getSession();
				if (user != null) {
					session.setAttribute("activeUser", user);
					response.sendRedirect("index.jsp");
				} else {
					Message message = new Message("Tài khoản hoặc mật khẩu sai! Thử lại!!", "error", "alert-danger");
					session.setAttribute("message", message);
					response.sendRedirect("views/login.jsp");
					return;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (login.trim().equals("admin")) {
			try {
				String userName = request.getParameter("email");
				String password = request.getParameter("password");

				AdminDaoImpl adminDaoImpl = new AdminDaoImpl(DatabaseConnection.getConnection());
				Admin admin = adminDaoImpl.getAdminByEmailPassword(userName, password);
				
				HttpSession session = request.getSession();
				if (admin != null) {
					session.setAttribute("activeAdmin", admin);
					response.sendRedirect("views/admin/admin.jsp");
				} else {
					Message message = new Message("Tài khoản hoặc mật khẩu sai! Thử lại!!", "error", "alert-danger");
					session.setAttribute("message", message);
					response.sendRedirect("views/admin/admin.jsp");
					return;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
