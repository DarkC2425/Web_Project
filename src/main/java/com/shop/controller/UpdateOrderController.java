package com.shop.controller;

import java.io.IOException;

import com.shop.model.Order;
import com.shop.service.IOrderService;
import com.shop.service.IUserService;
import com.shop.service.Impl.OrderServiceImpl;
import com.shop.service.Impl.UserServiceImpl;
import com.shop.util.JWTAuthentication;
import com.shop.util.MailMessenger;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UpdateOrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (JWTAuthentication.Authorized(request, response, "admin")) {
		} else {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		int oid = Integer.parseInt(request.getParameter("oid"));
		String status = request.getParameter("status");

		// Using OrderService to handle order status update
		IOrderService orderService = new OrderServiceImpl();
		orderService.updateOrderStatus(oid, status);

		if (status.trim().equals("Đã vận chuyển")) {
			Order order = orderService.getOrderById(oid);

			// Using UserService to fetch user details
			IUserService userService = new UserServiceImpl();
			MailMessenger.orderShipped(userService.getUserName(order.getUserId()),
					userService.getUserEmail(order.getUserId()), order.getOrderId(), order.getDate().toString());
		}

		response.sendRedirect("views/admin/display_orders.jsp");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
