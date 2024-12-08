<%@page import="java.io.Console"%>
<%@page import="com.shop.model.Message"%>
<%@page import="com.shop.model.Product"%>
<%@page import="com.shop.dao.Impl.ProductDaoImpl"%>
<%@page import="com.shop.model.Wishlist"%>
<%@page import="java.util.List"%>
<%@page errorPage="error_exception.jsp"%>
<%@page import="com.shop.model.User"%>
<%@page import="com.shop.util.DatabaseConnection"%>
<%@page import="com.shop.dao.Impl.WishlistDaoImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%
User u1 = (User) session.getAttribute("activeUser");
if (u1 == null) {
	Message message = new Message("Bạn chưa đăng nhập! Đăng nhập trước!!", "error", "alert-danger");
	session.setAttribute("message", message);
	response.sendRedirect("login.jsp");
	return;  
}
WishlistDaoImpl wishListDao = new WishlistDaoImpl(DatabaseConnection.getConnection());
List<Wishlist> wlist = wishListDao.getListByUserId(u1.getUserId());
log("wishlist = " + wlist.size());
ProductDaoImpl pDao = new ProductDaoImpl(DatabaseConnection.getConnection());
%>
<div class="container px-3 py-3">
	<%
	if (wlist == null || wlist.size() == 0) {
	%>
	<div class="container mt-5 mb-5 text-center">
		<img src="Images/wishlist.png" style="max-width: 200px;"
			class="img-fluid">
		<h4 class="mt-3">Danh sách yêu thích trống</h4>
		Bạn chưa có món hàng nào trong danh sách yêu thích. Hãy thêm sản phẩm!
	</div>
	<%
	} else {
	%>
	<h4>
		Đơn hàng yêu thích (<%=wlist.size()%>)
	</h4>
	<hr>
	<div class="container">
		<table class="table table-hover">
			<%
			for (Wishlist w : wlist) {
				Product p = pDao.getProductsByProductId(w.getProductId());
			%>
			<tr class="text-center">
				<td><img src="https://<%=p.getProductImages()%>"
					style="width: 50px; height: 50px; width: auto;"></td>
				<td class="text-start"><%=p.getProductName()%></td>
				<td><%=p.getProductPriceAfterDiscount()%>&#8363;</td>
				<td><a
					href="WishlistController?uid=<%=u1.getUserId()%>&pid=<%=p.getProductId()%>&op=delete"
					class="btn btn-secondary" role="button">Xóa bỏ</a></td>
			</tr>
			<%
			}
			%>
		</table>
	</div>
	<%
	}
	%>
</div>
