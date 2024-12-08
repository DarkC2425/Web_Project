<%@page import="com.shop.dao.Impl.WishlistDaoImpl"%>
<%@page import="com.shop.model.User"%>
<%@page import="com.shop.dao.Impl.CategoryDaoImpl"%>
<%@page import="com.shop.model.Product"%>
<%@page import="java.util.List"%>
<%@page import="com.shop.util.DatabaseConnection"%>
<%@page import="com.shop.dao.Impl.ProductDaoImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
User u = (User) session.getAttribute("activeUser");
WishlistDaoImpl wishlistDao = new WishlistDaoImpl(DatabaseConnection.getConnection());

String searchKey = request.getParameter("search");
String catId = request.getParameter("category");
CategoryDaoImpl categoryDao = new CategoryDaoImpl(DatabaseConnection.getConnection());
String message = "";

ProductDaoImpl productDao = new ProductDaoImpl(DatabaseConnection.getConnection());
List<Product> prodList = null;
if (searchKey != null) {
	if (!searchKey.isEmpty()) {
		message = "Hiển thị kết quả cho \"" + searchKey + "\"";
	} else {
		message = "Không tìm thấy sản phẩm!";
	}
	prodList = productDao.getAllProductsBySearchKey(searchKey);

} else if (catId != null && !(catId.trim().equals("0"))) {
	prodList = productDao.getAllProductsByCategoryId(Integer.parseInt(catId.trim()));
	message = "Hiển thị kết quả cho \"" + categoryDao.getCategoryName(Integer.parseInt(catId.trim())) + "\"";
} else {
	prodList = productDao.getAllProducts();
	message = "Tất cả hàng hóa";
}

if (prodList != null && prodList.size() == 0) {

	message = "Không có hàng hóa nào có sẵn cho \""
	+ (searchKey != null ? searchKey : categoryDao.getCategoryName(Integer.parseInt(catId.trim()))) + "\"";

	prodList = productDao.getAllProducts();
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sản phẩm</title>
<%@include file="Components/common_css_js.jsp"%>
<style>
.real-price {
	font-size: 22px !important;
	font-weight: 600;
}

.product-price {
	font-size: 17px !important;
	text-decoration: line-through;
}

.product-discount {
	font-size: 15px !important;
	color: #027a3e;
}

.wishlist-icon {
	cursor: pointer;
	position: absolute;
	right: 10px;
	top: 10px;
	width: 36px;
	height: 36px;
	border-radius: 50%;
	border: 1px solid #f0f0f0;
	box-shadow: 0 1px 4px 0 rgba(0, 0, 0, .1);
	padding-right: 40px;
	background: #fff;
}
</style>
</head>
<body style="background-color: #f0f0f0;">


	<!--show products-->
	<h4 class="text-center mt-2"><%=message%></h4>
	<div class="container-fluid my-3 px-5">
		<div class="row row-cols-1 row-cols-md-4 g-3">
			<%
			int itemsPerPage = 10;
			int totalProducts = prodList.size();
			int totalPages = (int) Math.ceil((double) totalProducts / itemsPerPage);
			int currentPage = (request.getParameter("page") != null) ? Integer.parseInt(request.getParameter("page")) : 1;
			int startIndex = (currentPage - 1) * itemsPerPage;
			int endIndex = Math.min(startIndex + itemsPerPage, totalProducts);

			List<Product> paginatedList = prodList.subList(startIndex, endIndex);
			for (Product p : paginatedList) {
			%>
			<div class="col">
				<div class="card h-100 px-2 py-2">
					<div class="container text-center">
						<img src="Product_imgs/<%=p.getProductImages()%>"
							class="card-img-top m-2"
							style="max-width: 100%; max-height: 200px; width: auto;">
						<div class="wishlist-icon">
							<%
							if (u != null) {
								if (wishlistDao.getWishlist(u.getUserId(), p.getProductId())) {
							%>
							<button
								onclick="window.open('WishlistController?uid=<%=u.getUserId()%>&pid=<%=p.getProductId()%>&op=remove', '_self')"
								class="btn btn-link" type="submit">
								<i class="fa-sharp fa-solid fa-heart" style="color: #ff0303;"></i>
							</button>
							<%
							} else {
							%>
							<button type="submit"
								onclick="window.open('WishlistController?uid=<%=u.getUserId()%>&pid=<%=p.getProductId()%>&op=add', '_self')"
								class="btn btn-link">
								<i class="fa-sharp fa-solid fa-heart" style="color: #909191;"></i>
							</button>
							<%
							}
							} else {
							%>
							<button onclick="window.open('login.jsp', '_self')"
								class="btn btn-link" type="submit">
								<i class="fa-sharp fa-solid fa-heart" style="color: #909191;"></i>
							</button>
							<%
							}
							%>
						</div>
						<h5 class="card-title text-center"><%=p.getProductName()%></h5>
						<div class="container text-center">
							<span class="real-price"><%=p.getProductPriceAfterDiscount()%>&#8363;</span>&ensp;
							<span class="product-price"><%=p.getProductPrice()%>&#8363;</span>&ensp;
							<span class="product-discount"><%=p.getProductDiscount()%>&#37;
								off</span>
						</div>
						<div class="container text-center mb-2 mt-2">
							<button type="button"
								onclick="window.open('viewProduct.jsp?pid=<%=p.getProductId()%>', '_self')"
								class="btn btn-primary text-white">Xem chi tiết</button>
						</div>
					</div>
				</div>
			</div>
			<%
			}
			%>
		</div>
		<!-- Pagination Controls -->
		<div class="container d-flex justify-content-center mt-4">
			<nav>
				<ul class="pagination">
					<%
					if (currentPage > 1) {
					%>
					<li class="page-item"><a class="page-link"
						href="?page=<%=currentPage - 1%>" aria-label="Previous"> <span
							aria-hidden="true">&laquo;</span>
					</a></li>
					<%
					}
					%>
					<%
					for (int i = 1; i <= totalPages; i++) {
					%>
					<li class="page-item <%=(i == currentPage) ? "active" : ""%>">
						<a class="page-link" href="?page=<%=i%>"><%=i%></a>
					</li>
					<%
					}
					%>
					<%
					if (currentPage < totalPages) {
					%>
					<li class="page-item"><a class="page-link"
						href="?page=<%=currentPage + 1%>" aria-label="Next"> <span
							aria-hidden="true">&raquo;</span>
					</a></li>
					<%
					}
					%>
				</ul>
			</nav>
		</div>
	</div>

</body>
</html>

