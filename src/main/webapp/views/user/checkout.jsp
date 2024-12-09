<%@page import="com.shop.model.Message"%>
<%@page import="com.shop.service.Impl.ProductServiceImpl"%>
<%@page import="com.shop.service.Impl.CartServiceImpl"%>
<%@page errorPage="error_exception.jsp"%>
<%@page import="com.shop.model.Admin"%>
<%@page import="com.shop.model.Cart"%>
<%@page import="com.shop.model.User"%>
<%@page import="java.util.List"%>
<%@page import="com.shop.model.Category"%>
<%@page import="com.shop.service.Impl.CategoryServiceImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
User activeUser = (User) session.getAttribute("activeUser");
if (activeUser == null) {
	Message message = new Message("Bạn chưa đăng nhập! Đăng nhập trước!!", "error", "alert-danger");
	session.setAttribute("message", message);
	response.sendRedirect("../login.jsp");
	return;
}
String from = (String) session.getAttribute("from");
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
<title>Thanh toán</title>
<%@include file="../../Components/common_css_js.jsp"%>
</head>
<body>


	<div class="container mt-5" style="font-size: 17px;">
		<div class="row">

			<!-- left column -->
			<div class="col-md-8">
				<div class="card">
					<div class="container px-3 py-3">
						<div class="card">
							<div class="container-fluid text-white"
								style="background-color: #389aeb;">
								<h4>Địa chỉ vận chuyển</h4>
							</div>
						</div>
						<div class="mt-3 mb-3">
							<h5><%=user.getUserName()%>
								&nbsp;
								<%=user.getUserPhone()%></h5>
							<%
							StringBuilder str = new StringBuilder();
							str.append(user.getUserAddress() + ", ");
							str.append(user.getUserCity());
							out.println(str);
							%>
							<br>
							<div class="text-end">
								<button type="button" class="btn btn-outline-primary"
									data-bs-toggle="modal" data-bs-target="#exampleModal">
									Thay đổi địa chỉ</button>
							</div>
						</div>
						<hr>
						<div class="card">
							<div class="container-fluid text-white"
								style="background-color: #389aeb;">
								<h4>Phương thức thanh toán</h4>
							</div>
						</div>
						<form action="../../OrderOperationController" method="post">
							<div class="form-check mt-2">

								<input class="form-check-input" type="radio" name="paymentMode"
									value="Tiền mặt"><label class="form-check-label">Tiền
									mặt</label>
							</div>
							<div id="paymentErrorMessage" class="mt-2"></div>
							<div class="text-end">
								<button type="submit"
									class="btn btn-lg btn-outline-primary mt-3"
									onclick="validatePayment(event)">Đặt hàng</button>
							</div>
						</form>
					</div>
				</div>
			</div>
			<!-- end of column -->

			<!-- right column -->
			<div class="col-md-4">
				<div class="card">
					<div class="container px-3 py-3">
						<h4>Chi tiết giá</h4>
						<hr>
						<%
						if (from.trim().equals("cart")) {
							CartServiceImpl cartService = new CartServiceImpl();
							//                                    int totalProduct = cartService.getCartCountByUserId(user.getUserId());
							int totalProduct = (int) session.getAttribute("totalQuantity");
							float totalPrice = (float) session.getAttribute("totalPrice");
						%>
						<table class="table table-borderless">
							<tr>
								<td>Tổng sản phẩm</td>
								<td><%=totalProduct%></td>
							</tr>
							<tr>
								<td>Tổng tiền</td>
								<td><%=totalPrice%>&#8363;</td>
							</tr>
							<tr>
								<td>Phí vận chuyển</td>
								<td>20000&#8363;</td>
							</tr>
							<tr>
								<td>Phí đóng gói</td>
								<td>9000&#8363;</td>
							</tr>
							<tr>
								<td><h5>Số tiền phải trả :</h5></td>
								<td><h5><%=totalPrice + 29000%>&#8363;
									</h5></td>
							</tr>
						</table>
						<%
						} else {
						ProductServiceImpl productService = new ProductServiceImpl();
						int pid = (int) session.getAttribute("pid");
						float price = productService.getProductPriceById(pid);
						%>
						<table class="table table-borderless">
							<tr>
								<td>Tổng sản phẩm</td>
								<td>1</td>
							</tr>
							<tr>
								<td>Tổng tiền</td>
								<td><%=price%>&#8363;</td>
							</tr>
							<tr>
								<td>Phí vận chuyển</td>
								<td>20000&#8363;</td>
							</tr>
							<tr>
								<td>Phí đóng gói</td>
								<td>9000&#8363;</td>
							</tr>
							<tr>
								<td><h5>Số tiền phải trả :</h5></td>
								<td><h5><%=price + 29000%>&#8363;
									</h5></td>
							</tr>
						</table>
						<%
						}
						%>
					</div>
				</div>
			</div>
			<!-- end of column -->
		</div>
	</div>


	<!--Change Address Modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h1 class="modal-title fs-5" id="exampleModalLabel">Thay đổi
						địa chỉ</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<form action="../../UpdateUserController" method="post">
					<input type="hidden" name="operation" value="changeAddress">
					<div class="modal-body mx-3">
						<div class="mt-2">
							<label class="form-label fw-bold">Địa chỉ</label>
							<textarea name="user_address" rows="3"
								placeholder="Nhập địa chỉ (Thành phố hoặc khu vực)"
								class="form-control" required></textarea>
						</div>
						<div class="mt-2">
							<label class="form-label fw-bold">Thành phố</label> <input
								class="form-control" type="text" name="city"
								placeholder="Thành phố/Tỉnh/Thị trấn" required>
						</div>

					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-bs-dismiss="modal">Đóng</button>
						<button type="submit" class="btn btn-primary">Lưu</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- end modal -->

	<script>
		function validatePayment(event) {
			const paymentMethods = document.getElementsByName("paymentMode");
			let isSelected = false;

			for (let i = 0; i < paymentMethods.length; i++) {
				if (paymentMethods[i].checked) {
					isSelected = true;
					break;
				}
			}

			const errorMessage = document.getElementById('paymentErrorMessage');
			if (!isSelected) {
				event.preventDefault();
				errorMessage.textContent = "Bạn vui lòng chọn phương thức thanh toán trước";
				errorMessage.style.color = "red";
			} else {
				errorMessage.textContent = "";
			}
		}
	</script>

</body>
</html>