<%@page import="com.shop.model.Message"%>
<%@page errorPage="error_exception.jsp"%>
<%@page import="com.shop.model.Admin"%>
<%@page import="com.shop.model.Cart"%>
<%@page import="com.shop.service.Impl.CartServiceImpl"%>
<%@page import="com.shop.model.User"%>
<%@page import="java.util.List"%>
<%@page import="com.shop.model.Category"%>
<%@page import="com.shop.service.Impl.CategoryServiceImpl"%>
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
<%
User user = (User) session.getAttribute("activeUser");
    Admin admin = (Admin) session.getAttribute("activeAdmin");

    CategoryServiceImpl catService = new CategoryServiceImpl();
    List<Category> categoryList = catService.getAllCategories();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Xem danh mục</title>
<%@include file="../../Components/common_css_js.jsp"%>
</head>
<body>
	<!-- Category -->
	<div class="container mt-3">
	<%@include file="../../Components/alert_message.jsp"%>
		<table class="table table-hover">
			<tr class="table-primary text-center" style="font-size: 20px;">
				<th>Ảnh</th>
				<th>Tên danh mục</th>
				<th>Action</th>
			</tr>
			<%
			for (Category c : categoryList) {
			%>
			<tr class="text-center">
				<td><img src="../../Product_imgs\<%=c.getCategoryImage()%>"
					style="width: 60px; height: 60px; width: auto;"></td>
				<td><%=c.getCategoryName()%></td>
				<td><a href="update_category.jsp?cid=<%=c.getCategoryId()%>" role="button" class="btn btn-secondary">Cập nhật</a>&emsp;<a
					href="../../AddOperationController?cid=<%=c.getCategoryId()%>&operation=deleteCategory"
					class="btn btn-danger" role="button">Xóa</a></td>
			</tr>
			<%
			}
			%>
		</table>
	</div>

</body>
</html>
