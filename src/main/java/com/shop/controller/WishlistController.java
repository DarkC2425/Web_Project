package com.shop.controller;

import java.io.IOException;

import com.shop.model.Wishlist;
import com.shop.service.IWishlistService;
import com.shop.service.Impl.WishlistServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class WishlistController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		int uid = Integer.parseInt(request.getParameter("uid"));
		int pid = Integer.parseInt(request.getParameter("pid"));
		String op = request.getParameter("op");

		// Using WishlistService to handle business logic
		IWishlistService wishlistService = new WishlistServiceImpl();

		if (op.trim().equals("add")) {
			Wishlist wishlist = new Wishlist(uid, pid);
			wishlistService.addToWishlist(wishlist);
			response.sendRedirect("views/products.jsp");
		} else if (op.trim().equals("remove")) {
			wishlistService.deleteWishlist(uid, pid);
			response.sendRedirect("views/products.jsp");
		} else if (op.trim().equals("delete")) {
			wishlistService.deleteWishlist(uid, pid);
			response.sendRedirect("views/user/profile.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
