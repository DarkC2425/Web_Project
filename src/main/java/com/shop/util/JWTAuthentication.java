package com.shop.util;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.shop.model.Admin;
import com.shop.model.User;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class JWTAuthentication {
	public static Boolean Authorized(HttpServletRequest request, HttpServletResponse response, String role) {
		String jwt = null;

		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("jwt".equals(cookie.getName())) {
					jwt = cookie.getValue();
					break;
				}
			}
		}

		if (jwt != null) {
			try {
				HttpSession session = request.getSession();
				Algorithm algorithm = Algorithm.HMAC256("HCMUTE");
				JWTVerifier verifier = JWT.require(algorithm).withIssuer("2NT").withAudience(role).build();
				DecodedJWT decodedJWT = verifier.verify(jwt);
				String email = decodedJWT.getSubject();
				Date dateExpires = decodedJWT.getExpiresAt();
				String validString = null;
				if (role.trim().equals("admin")) {
					Admin admin = (Admin) session.getAttribute("activeAdmin");
					validString = admin.getEmail();
				} else if (role.trim().equals("user")) {
					User user = (User) session.getAttribute("activeUser");
					validString = user.getUserEmail();
				}
				if (email.trim().equals(validString.trim()) && dateExpires.after(new Date())) {
					return true;
				}
				System.out.print(email + " " + dateExpires);
				return false;
			} catch (JWTVerificationException e) {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				return false;
			}
		} else {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		return false;
	}
}
