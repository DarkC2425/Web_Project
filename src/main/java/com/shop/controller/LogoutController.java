package com.shop.controller;

import java.io.IOException;

import com.shop.model.Message;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String user = request.getParameter("user");
		HttpSession session = request.getSession();

		if (user.trim().equals("user")) {
			session.removeAttribute("activeUser");
			Cookie cookie = null;
			Cookie[] cookies = null;
			// Get an array of Cookies associated with this domain
			cookies = request.getCookies();
			// Set response content type
			if (cookies != null) {
				for (Cookie element : cookies) {
					cookie = element;
					if ((cookie.getName()).compareTo("jwt") == 0) {
						// delete cookie
						cookie.setMaxAge(0);
						response.addCookie(cookie);
					}
				}
			}
			Message message = new Message("Đăng xuất thành công!!", "success", "alert-success");
			session.setAttribute("message", message);
			response.sendRedirect("views/login.jsp");
		} else if (user.trim().equals("admin")) {
			session.removeAttribute("activeAdmin");
			Cookie cookie = null;
			Cookie[] cookies = null;
			// Get an array of Cookies associated with this domain
			cookies = request.getCookies();
			// Set response content type
			if (cookies != null) {
				for (Cookie element : cookies) {
					cookie = element;
					if ((cookie.getName()).compareTo("jwt") == 0) {
						// delete cookie
						cookie.setMaxAge(0);
						response.addCookie(cookie);
					}
				}
			}
			Message message = new Message("Đăng xuất thành công!!", "success", "alert-success");
			session.setAttribute("message", message);
			response.sendRedirect("views/admin/adminlogin.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
