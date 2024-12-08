<%@page import="com.shop.model.Message"%>
<%@page import="com.shop.dao.Impl.UserDaoImpl"%>
<%@page errorPage="error_exception.jsp"%>
<%@page import="com.shop.model.Admin"%>
<%@page import="com.shop.model.Cart"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.shop.model.OrderedProduct"%>
<%@page import="com.shop.model.Order"%>
<%@page import="java.util.List"%>
<%@page import="com.shop.dao.Impl.OrderedProductDaoImpl"%>
<%@page import="com.shop.dao.Impl.OrderDaoImpl"%>
<%@page import="com.shop.util.DatabaseConnection"%>

<%
Admin activeAdmin = (Admin) session.getAttribute("activeAdmin");
if (activeAdmin == null) {
	Message message = new Message("Bạn chưa đăng nhập! Đăng nhập trước!!", "error", "alert-danger");
	session.setAttribute("message", message);
	response.sendRedirect("adminlogin.jsp");
	return;
}
OrderDaoImpl orderDao = new OrderDaoImpl(DatabaseConnection.getConnection());
OrderedProductDaoImpl ordProdDao = new OrderedProductDaoImpl(DatabaseConnection.getConnection());
List<Order> orderList = orderDao.getAllOrder();
UserDaoImpl userDao = new UserDaoImpl(DatabaseConnection.getConnection());
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Xem đơn hàng</title>
<%@include file="Components/common_css_js.jsp"%>
</head>
<body>
	<!-- order details -->

	<div class="container-fluid px-3 py-3">
		<%
		if (orderList == null || orderList.size() == 0) {
		%>
		<div class="container mt-5 mb-5 text-center">
			<img src="Images/empty-cart.png" style="max-width: 200px;"
				class="img-fluid">
			<h4 class="mt-3">Không tìm thấy đơn hàng</h4>
		</div>
		<%
		} else {
		%>
		<div class="container-fluid">
			<table class="table table-hover">
				<tr class="table-primary" style="font-size: 18px;">
					<th class="text-center">Sản phẩm</th>
					<th>ID Đơn hàng</th>
					<th>Chi tiết sản phẩm</th>
					<th>Địa chỉ vận chuyển</th>
					<th>Ngày & Giờ</th>
					<th>Thanh toán</th>
					<th>Tình trạng</th>
					<th colspan="2" class="text-center">Action</th>
				</tr>
				<%
				for (Order order : orderList) {
					List<OrderedProduct> ordProdList = ordProdDao.getAllOrderedProduct(order.getId());
					for (OrderedProduct orderProduct : ordProdList) {
				%>
				<form action="UpdateOrderController?oid=<%=order.getId()%>" method="post">
			<tr>
					<td class="text-center"><img
						src="Product_imgs\<%=orderProduct.getImage()%>"
						style="width: 50px; height: 50px; width: auto;"></td>
					<td><%=order.getOrderId()%></td>
					<td><%=orderProduct.getName()%><br>Số lượng: <%=orderProduct.getQuantity()%><br>Tổng tiền:
                                            <%=orderProduct.getPrice() * orderProduct.getQuantity()%>&#8363;</td>
					<td><%=userDao.getUserName(order.getUserId())%><br>Số điện thoại: <%=userDao.getUserPhone(order.getUserId())%><br><%=userDao.getUserAddress(order.getUserId())%></td>
					<td><%=order.getDate()%></td>
					<td><%=order.getPayementType()%></td>
					<td><%=order.getStatus()%></td>
					<td><select id="operation" name="status" class="form-select">
							<option>--Chọn hoạt động--</option>
							<option value="Đã đặt hàng">Đã đặt hàng</option>
							<option value="Đã vận chuyển">Đã vận chuyển</option>
							<option value="Hủy bỏ đơn hàng">Hủy bỏ đơn hàng</option>
							<option value="Đã giao hàng">Đã giao hàng</option>
					</select></td>
					<td>
						<%
						if (order.getStatus().equals("Đã giao hàng")) {
						%>
						<button type="submit" class="btn btn-success disabled">Cập nhật</button>
						<%
						} else {
						%>
						<button type="submit" class="btn btn-secondary">Cập nhật</button> 
						<%
						 }
						 %>
					</td>
				</tr>
				</form>
				<%
				}
				}
				%>
			</table>

		</div>
		<%
		}
		%>
	</div>
</body>
</html>