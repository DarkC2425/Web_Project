package com.shop.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

import com.shop.dao.Impl.CartDaoImpl;
import com.shop.dao.Impl.ProductDaoImpl;
import com.shop.model.Cart;
import com.shop.model.Message;
import com.shop.util.DatabaseConnection;

public class AddToCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int uid = Integer.parseInt(req.getParameter("uid"));
		int pid = Integer.parseInt(req.getParameter("pid"));

		CartDaoImpl cartDaoImpl = new CartDaoImpl(DatabaseConnection.getConnection());
		int qty = cartDaoImpl.getQuantity(uid, pid);
		HttpSession session = req.getSession();
		Message message = null;
		
		if (qty == 0) {
			Cart cart = new Cart(uid, pid, qty + 1);
			cartDaoImpl.addToCart(cart);
			message = new Message("Sản phẩm đã được thêm đến giỏ hàng thành công!", "success", "alert-success");
			
		}else {
			int cid = cartDaoImpl.getIdByUserIdAndProductId(uid, pid);
			cartDaoImpl.updateQuantity(cid, qty+1);
			message = new Message("Số lượng sản phẩm đã tăng lên!", "success", "alert-success");
		}
		//updating quantity of product in database
		ProductDaoImpl productDaoImpl = new ProductDaoImpl(DatabaseConnection.getConnection());
		productDaoImpl.updateQuantity(pid, productDaoImpl.getProductQuantityById(pid) - 1);
		session.setAttribute("message", message);
		resp.sendRedirect("views/viewProduct.jsp?pid="+pid);
	}

}
