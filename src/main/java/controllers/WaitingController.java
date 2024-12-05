package controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.User;

/**
 * Servlet implementation class WaitingController
 */
@WebServlet(urlPatterns = "/waiting")
public class WaitingController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WaitingController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		if (session != null && session.getAttribute("account") != null) {
			User User = (User) session.getAttribute("account");
			request.setAttribute("email", User.getEmail());
			if (User.getRole().trim().equals("admin")) {
				response.sendRedirect(request.getContextPath() + "/admin/home");
			} else if (User.getRole().trim().equals("seller")) {
				response.sendRedirect(request.getContextPath() + "/seller/home");
			} else if (User.getRole().trim().equals("user")) {
				response.sendRedirect(request.getContextPath() + "/user/home");
			} else {
				response.sendRedirect(request.getContextPath() + "/home");
			}
		} else {
			response.sendRedirect(request.getContextPath() + "/login");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
