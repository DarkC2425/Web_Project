package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.User;
import services.IUserService;
import services.impl.UserServiceImpl;

public class Function {

	public static String hashWithSHA256(String input) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] encodedHash = digest.digest(input.getBytes());
			return bytesToHex(encodedHash);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	private static String bytesToHex(byte[] hash) {
		StringBuilder hexString = new StringBuilder(2 * hash.length);
		for (byte b : hash) {
			String hex = Integer.toHexString(0xff & b);
			if (hex.length() == 1)
				hexString.append('0');
			hexString.append(hex);
		}
		return hexString.toString();
	}

	public static Boolean Authorized(HttpServletRequest request, HttpServletResponse response) {
		String jwt = null;
		IUserService iUserService = new UserServiceImpl();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("account");
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
				Algorithm algorithm = Algorithm.HMAC256("HCMUTE");
				JWTVerifier verifier = JWT.require(algorithm).withIssuer("your-issuer").build();
				DecodedJWT decodedJWT = verifier.verify(jwt);
				String email = decodedJWT.getSubject();
				String role = decodedJWT.getClaim("role").asString();
				if (user.getEmail().trim().equals(email) && user.getRole().trim().equals(role)
						&& iUserService.findByUID(user.getUID()) != null) {
					return true;
				}
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
