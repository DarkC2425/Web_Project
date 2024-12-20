<%@page import="com.shop.model.Message"%>
<%@page import="com.shop.service.Impl.UserServiceImpl"%>
<%@page errorPage="error_exception.jsp"%>
<%@page import="com.shop.model.Admin"%>
<%@page import="com.shop.model.Cart"%>
<%@page import="com.shop.service.Impl.CartServiceImpl"%>
<%@page import="com.shop.model.User"%>
<%@page import="java.util.List"%>
<%@page import="com.shop.model.Category"%>
<%@page import="com.shop.service.Impl.CategoryServiceImpl"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
User user = (User) session.getAttribute("activeUser");
    Admin admin = (Admin) session.getAttribute("activeAdmin");

    CategoryServiceImpl catService = new CategoryServiceImpl();
    List<Category> categoryList = catService.getAllCategories();
%>
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
<%@include file="../../Components/common_css_js.jsp"%>
</head>
<body>


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
			UserServiceImpl userService = new UserServiceImpl();
					List<User> userList = userService.getAllUser();
					for (User u : userList) {
			%>
			<tr>
				<td><%=u.getUserName()%></td>
				<td><%=u.getUserEmail()%></td>
				<td><%=u.getUserPhone()%></td>
				<td><%=u.getUserGender()%></td>
				<td><%=userService.getUserAddress(u.getUserId())%></td>
				<td><%=u.getDateTime()%></td>
				<td><a href="../../UpdateUserController?operation=deleteUser&uid=<%=u.getUserId()%>" role="button" class="btn btn-danger">Xóa</a></td>
			</tr>
			<%
			}
			%>
		</table>
	</div>
</body>
</html>