<%@page import="com.webshoes.dao.WishlistDao"%>
<%@page import="com.webshoes.dao.ProductDao"%>
<%@page import="com.webshoes.beans.Product"%>
<%@page errorPage="error_exception.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%
int productId = Integer.parseInt(request.getParameter("pid"));
ProductDao productDao = new ProductDao(ConnectionProvider.getConnection());
Product product = (Product) productDao.getProductsByProductId(productId);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Xem sản phẩm</title>
<%@include file="Components/common_css_js.jsp"%>
<style type="text/css">
.real-price {
	font-size: 26px !important;
	font-weight: 600;
}

.product-price {
	font-size: 18px !important;
	text-decoration: line-through;
}

.product-discount {
	font-size: 16px !important;
	color: #027a3e;
}
</style>
</head>
<body>

	<!--navbar -->
	<%@include file="Components/navbar.jsp"%>

	<div class="container mt-5">
			<%@include file="Components/alert_message.jsp"%>
		<div class="row border border-3">
			<div class="col-md-6">
				<div class="container-fluid text-end my-3">
					<img src="Product_imgs\<%=product.getProductImages()%>"
						class="card-img-top"
						style="max-width: 100%; max-height: 500px; width: auto;">
				</div>
			</div>
			<div class="col-md-6">
				<div class="container-fluid my-5">
					<h4><%=product.getProductName()%></h4>
					<span class="fs-5"><b>Mô tả</b></span><br> <span><%=product.getProductDescription()%></span><br>
					<span class="real-price"><%=product.getProductPriceAfterDiscount()%>&#8363;</span>&ensp;
					<span class="product-price"><%=product.getProductPrice()%>&#8363;</span>&ensp;
					<span class="product-discount"><%=product.getProductDiscount()%>&#37;off</span><br>
					<span class="fs-5"><b>Tình trạng : </b></span> <span id="availability">
						<%
						if (product.getProductQunatity() > 0) {
							out.println("Có sẵn");
						} else {
							out.println("Hiện tại đã hết hàng");
						}
						%>
					</span><br> <span class="fs-5"><b>Hãng : </b></span> <span><%=catDao.getCategoryName(product.getCategoryId())%></span>
					<form method="post">
						<div class="container-fluid text-center mt-3">
							<%
							if (user == null) {
							%>
							<button type="button" onclick="window.open('login.jsp', '_self')"
								class="btn btn-primary text-white btn-lg">Thêm vào giỏ hàng</button>
							&emsp;
							<button type="button" onclick="window.open('login.jsp', '_self')"
								class="btn btn-info text-white btn-lg">Mua ngay bây giờ</button>
							<%
							} else {
							%>
							<button type="submit"
								formaction="./AddToCartServlet?uid=<%=user.getUserId()%>&pid=<%=product.getProductId()%>"
								class="btn btn-primary text-white btn-lg">Thêm vào giỏ hàng</button>
							&emsp; <a
								href="checkout.jsp" id="buy-btn"
								class="btn btn-info text-white btn-lg" role="button"
								aria-disabled="true">Mua ngay bây giờ</a> 
							<%
							}
							%>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script>
		$(document).ready(function() {
			if ($('#availability').text().trim() == "Hiện tại đã hết hàng") {
				$('#availability').css('color', 'red');
				$('.btn').addClass('disabled');
			}
			$('#buy-btn').click(function(){
				<%
				session.setAttribute("pid", productId);
				session.setAttribute("from", "buy");
				%>	
				});
		});
	</script>
</body>
</html>