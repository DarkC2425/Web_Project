<%@page import="com.shop.model.Message"%>
<%@page errorPage="error_exception.jsp"%>
<%@page import="com.shop.model.Admin"%>
<%@page import="com.shop.model.Cart"%>
<%@page import="com.shop.service.Impl.CartServiceImpl"%>
<%@page import="com.shop.model.User"%>
<%@page import="java.util.List"%>
<%@page import="com.shop.model.Category"%>
<%@page import="com.shop.service.Impl.CategoryServiceImpl"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
User user = (User) session.getAttribute("activeUser");
Admin admin = (Admin) session.getAttribute("activeAdmin");

CategoryServiceImpl catService = new CategoryServiceImpl();
List<Category> categoryList = catService.getAllCategories();
%>
<%
Admin activeAdmin = (Admin) session.getAttribute("activeAdmin");
if (activeAdmin == null) {
	Message message = new Message("You are not logged in! Login first!!", "error", "alert-danger");
	session.setAttribute("message", message);
	response.sendRedirect("adminlogin.jsp");
	return;
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cập nhật danh mục</title>
<%@include file="../../Components/common_css_js.jsp"%>
</head>
<body>

	<!-- update category -->
	<%
	int cid = Integer.parseInt(request.getParameter("cid"));
	Category category = catService.getCategoryById(cid);
	%>
	<div class="container mt-5">
		<div class="row row-cols-1 row-cols-md-1 offset-md-2">
			<div class="col">
				<div class="card w-75">
					<div class="card-header text-center">
						<h3>Chỉnh sửa danh mục</h3>
					</div>
					<form action="../../AddOperationController?cid=<%=cid%>" method="post"
						enctype="multipart/form-data">
						<div class="card-body">
							<input type="hidden" name="operation" value="updateCategory">
							<div class="mb-3">
								<label class="form-label"><b>Tên danh mục</b></label> <input
									type="text" name="category_name"
									value="<%=category.getCategoryName()%>" class="form-control">
							</div>
							<div class="mb-3">
								<label class="form-label"><b>Ảnh danh mục </b></label><input
									class="form-control" type="file" name="category_img">
							</div>
							<div class="mb-3">
								<label class="form-label"><b>Ảnh đã tải lên:&nbsp;</b></label><%=category.getCategoryImage()%>
								&emsp;<img src="../../Product_imgs\<%=category.getCategoryImage()%>"
									style="width: 80px; height: 80px; width: auto;"> <input
									type="hidden" name="image"
									value="<%=category.getCategoryImage()%>">
							</div>
						</div>
						<div class="card-footer text-center">
							<button type="submit" class="btn btn-lg btn-primary me-3">Cập
								nhật</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- end-->
</body>
</html>