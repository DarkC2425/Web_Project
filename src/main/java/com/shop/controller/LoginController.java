package com.shop.controller;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.shop.model.Admin;
import com.shop.model.Message;
import com.shop.model.User;
import com.shop.service.IAdminService;
import com.shop.service.IUserService;
import com.shop.service.Impl.AdminServiceImpl;
import com.shop.service.Impl.UserServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String login = request.getParameter("login");
		if (login.trim().equals("user")) {
			try {
				String userEmail = request.getParameter("user_email");
				String userPassword = request.getParameter("user_password");

				// getting user through entered email and password
				IUserService userService = new UserServiceImpl();
				User user = userService.getUserByEmailPassword(userEmail, userPassword);

				// storing current user in session
				HttpSession session = request.getSession();
				if (user != null) {
					Algorithm algorithm = Algorithm.HMAC256("HCMUTE");

					String jwt = JWT.create().withIssuer("2NT").withSubject(user.getUserEmail()).withAudience("user")
							.withIssuedAt(new Date()).withExpiresAt(new Date(System.currentTimeMillis() + 3600 * 1000))
							.withNotBefore(new Date(System.currentTimeMillis() + 1000))
							.withJWTId(UUID.randomUUID().toString()).sign(algorithm);
					Cookie jwtCookie = new Cookie("jwt", jwt);
					jwtCookie.setHttpOnly(true);
					jwtCookie.setPath("/");
					jwtCookie.setMaxAge(3600);
					response.addCookie(jwtCookie);
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

				IAdminService adminService = new AdminServiceImpl();
				Admin admin = adminService.getAdminByEmailPassword(userName, password);

				HttpSession session = request.getSession();
				if (admin != null) {
					Algorithm algorithm = Algorithm.HMAC256("HCMUTE");

					String jwt = JWT.create().withIssuer("2NT").withSubject(admin.getEmail()).withAudience("admin")
							.withIssuedAt(new Date()).withExpiresAt(new Date(System.currentTimeMillis() + 3600 * 1000))
							.withNotBefore(new Date(System.currentTimeMillis() + 1000))
							.withJWTId(UUID.randomUUID().toString()).sign(algorithm);
					Cookie jwtCookie = new Cookie("jwt", jwt);
					jwtCookie.setHttpOnly(true);
					jwtCookie.setPath("/");
					jwtCookie.setMaxAge(3600);
					response.addCookie(jwtCookie);
					session.setAttribute("activeAdmin", admin);
					response.sendRedirect("views/admin/admin.jsp");
				} else {
					Message message = new Message("Tài khoản hoặc mật khẩu sai! Thử lại!!", "error", "alert-danger");
					session.setAttribute("message", message);
					response.sendRedirect("views/admin/adminlogin.jsp");
					return;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
