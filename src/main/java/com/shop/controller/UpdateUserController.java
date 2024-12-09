package com.shop.controller;

import java.io.IOException;

import com.shop.model.Message;
import com.shop.model.User;
import com.shop.service.IUserService;
import com.shop.service.Impl.UserServiceImpl;
import com.shop.util.JWTAuthentication;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class UpdateUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String op = request.getParameter("operation");
		HttpSession session = request.getSession();
		User oldUser = (User) session.getAttribute("activeUser");

		// Using IUserService for user operations
		IUserService userService = new UserServiceImpl();

		if (op.trim().equals("changeAddress")) {
			try {
				if (JWTAuthentication.Authorized(request, response, "user")) {
				} else {
					response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					return;
				}
				String userAddress = request.getParameter("user_address");
				String userCity = request.getParameter("city");

				User user = new User();
				user.setUserId(oldUser.getUserId());
				user.setUserName(oldUser.getUserName());
				user.setUserEmail(oldUser.getUserEmail());
				user.setUserPassword(oldUser.getUserPassword());
				user.setUserPhone(oldUser.getUserPhone());
				user.setUserGender(oldUser.getUserGender());
				user.setDateTime(oldUser.getDateTime());
				user.setUserAddress(userAddress);
				user.setUserCity(userCity);

				userService.updateUserAddresss(user);
				session.setAttribute("activeUser", user);
				response.sendRedirect("views/user/checkout.jsp");

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (op.trim().equals("updateUser")) {
			try {
				if (JWTAuthentication.Authorized(request, response, "user")) {
				} else {
					response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					return;
				}
				String userName = request.getParameter("name");
				String userEmail = request.getParameter("email");
				String userPhone = request.getParameter("mobile_no");
				String userGender = request.getParameter("gender");
				String userAddress = request.getParameter("address");
				String userCity = request.getParameter("city");

				User user = new User(userName, userEmail, userPhone, userGender, userAddress, userCity);
				user.setUserId(oldUser.getUserId());
				user.setUserPassword(oldUser.getUserPassword());
				user.setDateTime(oldUser.getDateTime());

				userService.updateUser(user);
				session.setAttribute("activeUser", user);
				Message message = new Message("Thông tin người dùng đã được cập nhật thành công!!", "success",
						"alert-success");
				session.setAttribute("message", message);
				response.sendRedirect("views/user/profile.jsp");

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (op.trim().equals("deleteUser")) {
			if (JWTAuthentication.Authorized(request, response, "admin")) {
			} else {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				return;
			}
			int uid = Integer.parseInt(request.getParameter("uid"));
			userService.deleteUser(uid);
			response.sendRedirect("views/admin/display_users.jsp");
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}
