package com.shop.controller;

import java.io.IOException;

import com.shop.model.Message;
import com.shop.service.ICartService;
import com.shop.service.IProductService;
import com.shop.service.Impl.CartServiceImpl;
import com.shop.service.Impl.ProductServiceImpl;
import com.shop.util.JWTAuthentication;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class CartOperationController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (JWTAuthentication.Authorized(request, response, "user")) {
		} else {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		ICartService cartService = new CartServiceImpl();
		IProductService productService = new ProductServiceImpl();

		int cid = Integer.parseInt(request.getParameter("cid"));
		int opt = Integer.parseInt(request.getParameter("opt"));

		int qty = cartService.getQuantityById(cid);
		int pid = cartService.getProductId(cid);
		int quantity = productService.getProductQuantityById(pid);

		if (opt == 1) {
			if (quantity > 0) {
				cartService.updateQuantity(cid, qty + 1);
				// updating (decreasing) quantity of product in database
				productService.updateQuantity(pid, productService.getProductQuantityById(pid) - 1);
				response.sendRedirect("views/user/cart.jsp");
			} else {
				HttpSession session = request.getSession();
				Message message = new Message("Sản phẩm đã hết hàng!", "error", "alert-danger");
				session.setAttribute("message", message);
				response.sendRedirect("views/user/cart.jsp");
			}

		} else if (opt == 2) {
			cartService.updateQuantity(cid, qty - 1);

			// updating (increasing) quantity of product in database
			productService.updateQuantity(pid, productService.getProductQuantityById(pid) + 1);
			response.sendRedirect("views/user/cart.jsp");

		} else if (opt == 3) {
			cartService.removeProduct(cid);
			HttpSession session = request.getSession();
			Message message = new Message("Sản phẩm đã được xóa khỏi giỏ hàng!", "success", "alert-success");
			session.setAttribute("message", message);

			// updating quantity of product in database
			// adding all the product qty back to database
			productService.updateQuantity(pid, productService.getProductQuantityById(pid) + qty);
			response.sendRedirect("views/user/cart.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}