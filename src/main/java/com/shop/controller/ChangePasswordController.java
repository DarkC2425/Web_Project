package com.shop.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import com.shop.dao.IUserDao;
import com.shop.dao.Impl.UserDaoImpl;
import com.shop.model.Message;
import com.shop.util.DatabaseConnection;
import com.shop.util.MailMessenger;

public class ChangePasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String referrer = request.getHeader("referer");
		IUserDao userDao = new UserDaoImpl(DatabaseConnection.getConnection());
		HttpSession session = request.getSession();
		
		if(referrer.contains("forgot_password")) {
			String email = request.getParameter("email").trim();
			List<String> list = userDao.getAllEmail();
			if(list.contains(email)) {
				Random rand = new Random();
				int max = 99999, min = 10000;
				int otp = rand.nextInt(max - min + 1) + min;
				//System.out.println(otp);
				session.setAttribute("otp", otp);
				session.setAttribute("email", email);
				MailMessenger.sendOtp(email, otp);
				
				Message message = new Message("Chúng tôi đã gửi mã đặt lại mật khẩu tới "+email, "success", "alert-success");
				session.setAttribute("message", message);
				response.sendRedirect("otp_code.jsp");
			}else {
				Message message = new Message("Email không tồn tại! Thử lại với Email khác!", "error", "alert-danger");
				session.setAttribute("message", message);
				response.sendRedirect("forgot_password.jsp");
				return;
			}
		}else if(referrer.contains("otp_code")) {
			int code = Integer.parseInt(request.getParameter("code"));
			int otp = (int)session.getAttribute("otp");
                        System.out.println("OTP: " + otp);
			if(code == otp) {
				session.removeAttribute("otp");
				response.sendRedirect("change_password.jsp");
			}else {
				Message message = new Message("Mã xác minh đã nhập không hợp lệ!", "error", "alert-danger");
				session.setAttribute("message", message);
				response.sendRedirect("otp_code.jsp");
				return;
			}
		}else if(referrer.contains("change_password")) {
			String password = request.getParameter("password");
			String email =(String)session.getAttribute("email");
			userDao.updateUserPasswordByEmail(password, email);
			session.removeAttribute("email");
			
			Message message = new Message("Mật khẩu đã được cập nhật thành công!", "error", "alert-success");
			session.setAttribute("message", message);
			response.sendRedirect("login.jsp");
		}
	}

}
