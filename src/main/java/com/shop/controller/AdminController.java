package com.shop.controller;

import java.io.IOException;

import com.shop.model.Admin;
import com.shop.model.Message;
import com.shop.service.IAdminService;
import com.shop.service.Impl.AdminServiceImpl;
import com.shop.util.JWTAuthentication;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (JWTAuthentication.Authorized(request, response, "admin")) {
		} else {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String operation = request.getParameter("operation");
		IAdminService adminService = new AdminServiceImpl();
		HttpSession session = request.getSession();
		Message message = null;

		if (operation.trim().equals("save")) {

			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String phone = request.getParameter("phone");

			Admin admin = new Admin(name, email, phone, password);
			boolean flag = adminService.saveAdmin(admin);

			if (flag) {
				message = new Message("Admin mới đã đăng ký thành công!", "success", "alert-success");
			} else {
				message = new Message("Xin lỗi! Xảy ra lỗi", "error", "alert-danger");
			}

		} else if (operation.trim().equals("delete")) {

			int id = Integer.parseInt(request.getParameter("id"));
			boolean flag = adminService.deleteAdmin(id);
			if (flag) {
				message = new Message("Admin đã được xóa thành công!", "success", "alert-success");
			} else {
				message = new Message("Xin lỗi! Xảy ra lỗi", "error", "alert-danger");
			}
		}
		session.setAttribute("message", message);
		response.sendRedirect("views/admin/display_admin.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
