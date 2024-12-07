<%@page import="com.shop.model.Message"%>
<%@page import="com.shop.dao.Impl.UserDaoImpl"%>
<%@page errorPage="error_exception.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
Admin activeAdmin = (Admin) session.getAttribute("activeAdmin");
if (activeAdmin == null) {
	Message message = new Message("Bạn chưa đăng nhập! Đăng nhập trước!!", "error", "alert-danger");
	session.setAttribute("message", message);
	response.sendRedirect("adminlogin.jsp");
	return;
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Xem người dùng</title>
<%@include file="Components/common_css_js.jsp"%>
</head>
<body>
	<!--navbar -->
	<%@include file="Components/navbar.jsp"%>

	<div class="container-fluid px-5 py-3">
		<table class="table table-hover">
			<tr class="text-center table-primary" style="font-size: 20px;">
				<th>Tên tài khoản</th>
				<th>Email</th>
				<th>Số điện thoại</th>
				<th>Giới tính</th>
				<th>Địa chỉ</th>
				<th>Ngày đăng ký</th>
				<th>Action</th>
			</tr>
			<%
			UserDaoImpl userDao = new UserDaoImpl(DatabaseConnection.getConnection());
					List<User> userList = userDao.getAllUser();
					for (User u : userList) {
			%>
			<tr>
				<td><%=u.getUserName()%></td>
				<td><%=u.getUserEmail()%></td>
				<td><%=u.getUserPhone()%></td>
				<td><%=u.getUserGender()%></td>
				<td><%=userDao.getUserAddress(u.getUserId())%></td>
				<td><%=u.getDateTime()%></td>
				<td><a href="UpdateUserController?operation=deleteUser&uid=<%=u.getUserId()%>" role="button" class="btn btn-danger">Xóa</a></td>
			</tr>
			<%
			}
			%>
		</table>
	</div>
</body>
</html>