package com.shop.controller;

import java.io.IOException;

import com.shop.model.Cart;
import com.shop.model.Message;
import com.shop.service.IProductService;
import com.shop.service.Impl.CartServiceImpl;
import com.shop.service.Impl.ProductServiceImpl;
import com.shop.util.JWTAuthentication;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AddToCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (JWTAuthentication.Authorized(req, resp, "user")) {
		} else {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		int uid = Integer.parseInt(req.getParameter("uid"));
		int pid = Integer.parseInt(req.getParameter("pid"));

		CartServiceImpl cartServiceImpl = new CartServiceImpl();
		int qty = cartServiceImpl.getQuantity(uid, pid);
		HttpSession session = req.getSession();
		Message message = null;

		if (qty == 0) {
			Cart cart = new Cart(uid, pid, qty + 1);
			cartServiceImpl.addToCart(cart);
			message = new Message("Sản phẩm đã được thêm đến giỏ hàng thành công!", "success", "alert-success");

		} else {
			int cid = cartServiceImpl.getIdByUserIdAndProductId(uid, pid);
			cartServiceImpl.updateQuantity(cid, qty + 1);
			message = new Message("Số lượng sản phẩm đã tăng lên!", "success", "alert-success");
		}
		// updating quantity of product in database
		IProductService productServiceImpl = new ProductServiceImpl();
		productServiceImpl.updateQuantity(pid, productServiceImpl.getProductQuantityById(pid) - 1);
		session.setAttribute("message", message);
		resp.sendRedirect("views/viewProduct.jsp?pid=" + pid);
	}

}
