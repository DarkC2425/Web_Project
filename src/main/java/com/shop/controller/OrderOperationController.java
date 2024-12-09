package com.shop.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.shop.model.Cart;
import com.shop.model.Order;
import com.shop.model.OrderedProduct;
import com.shop.model.Product;
import com.shop.model.User;
import com.shop.service.ICartService;
import com.shop.service.IOrderService;
import com.shop.service.IOrderedProductService;
import com.shop.service.IProductService;
import com.shop.service.Impl.CartServiceImpl;
import com.shop.service.Impl.OrderServiceImpl;
import com.shop.service.Impl.OrderedProductServiceImpl;
import com.shop.service.Impl.ProductServiceImpl;
import com.shop.util.JWTAuthentication;
import com.shop.util.MailMessenger;
import com.shop.util.OrderIdGenerator;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class OrderOperationController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (JWTAuthentication.Authorized(request, response, "user")) {
		} else {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String from = (String) session.getAttribute("from");
		String paymentType = request.getParameter("paymentMode");
		User user = (User) session.getAttribute("activeUser");
		String orderId = OrderIdGenerator.getOrderId();
		String status = "Đã đặt hàng";
		LocalDateTime orderDateTime = LocalDateTime.now();

		if (from.trim().equals("cart")) {
			try {
				IOrderService orderService = new OrderServiceImpl();
				Order order = new Order(orderId, status, java.sql.Timestamp.valueOf(orderDateTime), paymentType,
						user.getUserId());
				orderService.insertOrder(order);

				ICartService cartService = new CartServiceImpl();
				List<Cart> listOfCart = cartService.getCartListByUserId(user.getUserId());

				IOrderedProductService orderedProductService = new OrderedProductServiceImpl();
				IProductService productService = new ProductServiceImpl();

				for (Cart item : listOfCart) {
					Product prod = productService.getProductsByProductId(item.getProductId());
					String prodName = prod.getProductName();
					int prodQty = item.getQuantity();
					float price = prod.getProductPriceAfterDiscount();
					String image = prod.getProductImages();

					OrderedProduct orderedProduct = new OrderedProduct(prodName, prodQty, price, image, orderId);
					orderedProductService.insertOrderedProduct(orderedProduct);
				}

				session.removeAttribute("from");
				session.removeAttribute("totalPrice");

				// Removing all products from cart after successful order
				cartService.removeAllProduct();

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (from.trim().equals("buy")) {
			try {
				int pid = (int) session.getAttribute("pid");
				IOrderService orderService = new OrderServiceImpl();
				Order order = new Order(orderId, status, java.sql.Timestamp.valueOf(orderDateTime), paymentType,
						user.getUserId());
				orderService.insertOrder(order);

				IOrderedProductService orderedProductService = new OrderedProductServiceImpl();
				IProductService productService = new ProductServiceImpl();

				Product prod = productService.getProductsByProductId(pid);
				String prodName = prod.getProductName();
				int prodQty = 1;
				float price = prod.getProductPriceAfterDiscount();
				String image = prod.getProductImages();

				OrderedProduct orderedProduct = new OrderedProduct(prodName, prodQty, price, image, orderId);
				orderedProductService.insertOrderedProduct(orderedProduct);

				// Updating (decreasing) quantity of product in database
				productService.updateQuantity(pid, productService.getProductQuantityById(pid) - 1);

				session.removeAttribute("from");
				session.removeAttribute("pid");

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		session.setAttribute("order", "success");
		MailMessenger.successfullyOrderPlaced(user.getUserName(), user.getUserEmail(), orderId, new Date().toString());
		response.sendRedirect("views/index.jsp");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
