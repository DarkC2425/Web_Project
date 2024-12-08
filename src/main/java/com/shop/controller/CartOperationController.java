package com.shop.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.shop.dao.Impl.CartDaoImpl;
import com.shop.dao.Impl.ProductDaoImpl;
import com.shop.model.Message;
import com.shop.util.DatabaseConnection;

public class CartOperationController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CartDaoImpl cartDaoImpl = new CartDaoImpl(DatabaseConnection.getConnection());
		ProductDaoImpl productDaoImpl = new ProductDaoImpl(DatabaseConnection.getConnection());
		int cid =Integer.parseInt(request.getParameter("cid"));
		int opt =Integer.parseInt(request.getParameter("opt"));
		
		int qty = cartDaoImpl.getQuantityById(cid);
		int pid = cartDaoImpl.getProductId(cid);
		int quantity = productDaoImpl.getProductQuantityById(pid);	
		
		if(opt == 1) {
			if(quantity > 0) {
				cartDaoImpl.updateQuantity(cid, qty+1);
				//updating(decreasing) quantity of product in database
				productDaoImpl.updateQuantity(pid, productDaoImpl.getProductQuantityById(pid) - 1);
				response.sendRedirect("views/user/cart.jsp");
				
			}else {
				HttpSession session = request.getSession();
				Message message = new Message("Sản phẩm đã hết hàng!", "error", "alert-danger");
				session.setAttribute("message", message);
				response.sendRedirect("views/user/cart.jsp");
			}
			
		}else if(opt == 2) {
			cartDaoImpl.updateQuantity(cid, qty-1);
			
			//updating(increasing) quantity of product in database
			productDaoImpl.updateQuantity(pid, productDaoImpl.getProductQuantityById(pid) + 1);
			response.sendRedirect("views/user/cart.jsp");
			
		}else if(opt == 3) {
			cartDaoImpl.removeProduct(cid);
			HttpSession session = request.getSession();
			Message message = new Message("Sản phẩm đã được xóa khỏi giỏ hàng!", "success", "alert-success");
			session.setAttribute("message", message);
			
			//updating quantity of product in database
			//adding all the product qty back to database
			productDaoImpl.updateQuantity(pid, productDaoImpl.getProductQuantityById(pid) + qty);
			response.sendRedirect("views/user/cart.jsp");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
