package com.shop.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.shop.dao.IUserDao;
import com.shop.dao.Impl.OrderDaoImpl;
import com.shop.dao.Impl.UserDaoImpl;
import com.shop.model.Order;
import com.shop.util.DatabaseConnection;
import com.shop.util.MailMessenger;

public class UpdateOrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int oid = Integer.parseInt(request.getParameter("oid"));
		String status = request.getParameter("status");
		OrderDaoImpl orderDaoImpl = new OrderDaoImpl(DatabaseConnection.getConnection());
		orderDaoImpl.updateOrderStatus(oid, status);
		if (status.trim().equals("Đã vận chuyển")) {
			Order order = orderDaoImpl.getOrderById(oid);
			IUserDao userDao = new UserDaoImpl(DatabaseConnection.getConnection());
			MailMessenger.orderShipped(userDao.getUserName(order.getUserId()), userDao.getUserEmail(order.getUserId()),
					order.getOrderId(), order.getDate().toString());
		}
		response.sendRedirect("views/admin/display_orders.jsp");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

}
