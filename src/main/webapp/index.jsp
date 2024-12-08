<%@page import="com.shop.dao.Impl.ProductDaoImpl"%>
<%@page import="com.shop.model.Product"%>
<%@page import="com.shop.util.DatabaseConnection"%>
<%@page errorPage="error_exception.jsp"%>
<%@page import="com.shop.model.Admin"%>
<%@page import="com.shop.model.Cart"%>
<%@page import="com.shop.dao.Impl.CartDaoImpl"%>
<%@page import="com.shop.model.User"%>
<%@page import="java.util.List"%>
<%@page import="com.shop.model.Category"%>
<%@page import="com.shop.util.DatabaseConnection"%>
<%@page import="com.shop.dao.Impl.CategoryDaoImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
ProductDaoImpl productDao = new ProductDaoImpl(DatabaseConnection.getConnection());
    List<Product> productList = productDao.getAllLatestProducts();
    List<Product> topDeals = productDao.getDiscountedProducts();
%>
<%
User user = (User) session.getAttribute("activeUser");
    Admin admin = (Admin) session.getAttribute("activeAdmin");

    CategoryDaoImpl catDao = new CategoryDaoImpl(DatabaseConnection.getConnection());
    List<Category> categoryList = catDao.getAllCategories();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang chủ</title>
<%@include file="Components/common_css_js.jsp"%>
<style type="text/css">
.cus-card {
	border-radius: 50%;
	border-color: transparent;
	max-height: 200px;
	max-width: 200px;
	max-height: 200px;
}

.real-price {
	font-size: 20px !important;
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
</style>
</head>
<body>


	<!-- Category list -->
	<div class="container-fluid px-3 py-3"
		style="background-color: #e3f7fc;">
		<div class="row">
			<div class="card-group">
				<%
				int numberProducts = 1;
				for (Category c : categoryList) {
                    %>
				<div class="col text-center">
					<a href="views/products.jsp?category=<%=c.getCategoryId()%>"
						style="text-decoration: none;">
						<div class="card cus-card h-100">
							<div class="container text-center">
								<img src="Product_imgs/<%=c.getCategoryImage()%>" class="mt-3 "
									style="max-width: 100%; max-height: 100px; width: auto; height: auto;">
							</div>
							<h6><%=c.getCategoryName()%></h6>
						</div>
					</a>
				</div>

				<%
				numberProducts++;
					if(numberProducts>=10) break;
                        }
                    %>
			</div>
		</div>
	</div>
	<!-- end of list -->

	<!-- Carousel -->
	<!-- div id="carouselAutoplaying"
		class="carousel slide carousel-dark mt-3 mb-3" data-bs-ride="carousel">
		<div class="carousel-inner">
			<div class="carousel-item active">
				<img src="Images/scroll_img_2.jpg" class="d-block w-100" alt="...">
			</div>
			<div class="carousel-item">
				<img src="Images/scroll_img_1.jpg" class="d-block w-100" alt="...">
			</div>
			<div class="carousel-item">
				<img src="Images/scroll_img_3.jpg" class="d-block w-100" alt="...">
			</div>
		</div>
		<button class="carousel-control-prev" type="button"
			data-bs-target="#carouselAutoplaying" data-bs-slide="prev">
			<span class="carousel-control-prev-icon" aria-hidden="true"
				style="color: black;"></span> <span class="visually-hidden">Quay
				lại</span>
		</button>
		<button class="carousel-control-next" type="button"
			data-bs-target="#carouselAutoplaying" data-bs-slide="next">
			<span class="carousel-control-next-icon" aria-hidden="true"></span> <span
				class="visually-hidden">Tiếp tục</span>
		</button>
	</div-->
	<!-- end of carousel -->

	<!-- latest product listed -->
	<!--div class="container-fluid py-3 px-3" style="background: #f2f2f2;">
		<div class="row row-cols-1 row-cols-md-4 g-3">
			<div class="col">
				<div class="container text-center px-5 py-5">
					<h1>Sản phẩm mới nhất</h1>
					<img src="Images\shoes.jpg" class="card-img-top"
						style="max-width: 100%; max-height: 200px; width: auto;">
				</div>
			</div>
			<%
                    for (int i = 0; i < Math.min(3, productList.size()); i++) {
                %>
			<div class="col">
				<a href="viewProduct.jsp?pid=<%=productList.get(i).getProductId()%>"
					style="text-decoration: none;">
					<div class="card h-100">
						<div class="container text-center">
							<img
								src="https://<%=productList.get(i).getProductImages()%>"
								class="card-img-top m-2"
								style="max-width: 100%; max-height: 200px; width: auto;">
						</div>
						<div class="card-body">
							<h5 class="card-title text-center"><%=productList.get(i).getProductName()%></h5>

							<div class="container text-center">
								<span class="real-price"><%=productList.get(i).getProductPriceAfterDiscount()%>&#8363;</span>
								&ensp;<span class="product-price"><%=productList.get(i).getProductPrice()%>&#8363;
								</span>&ensp;<span class="product-discount"><%=productList.get(i).getProductDiscount()%>&#37;
									off</span>
							</div>
						</div>
					</div>
				</a>
			</div>

			<%
                    }
                %>
		</div>
	</div-->
	<div class="container-fluid py-5" style="background: #f8f9fa;">
		<!-- Tiêu đề chính -->
		<div class="text-center mb-5">
			<h1 class="fw-bold">Sản phẩm mới nhất</h1>
			<p class="text-muted">Khám phá những sản phẩm hot nhất hiện nay</p>
		</div>

		<!-- Bắt đầu danh sách sản phẩm -->
		<div
			class="row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-5 g-4">


			<!-- Hiển thị danh sách sản phẩm từ productList -->
			<% 
            for (int i = 0; i < Math.min(10, productList.size()); i++) { 
        %>
			<div class="col">
				<a
					href="views/viewProduct.jsp?pid=<%=productList.get(i).getProductId() %>"
					class="text-decoration-none">
					<div class="card h-100 shadow-sm">
						<div class="text-center p-3">
							<img
								src="https://<%= productList.get(i).getProductImages() %>"
								alt="<%= productList.get(i).getProductName() %>"
								class="img-fluid rounded"
								style="max-height: 200px; object-fit: cover;">
						</div>
						<div class="card-body text-center">
							<h6 class="card-title text-dark fw-bold">
								<%= productList.get(i).getProductName() %>
							</h6>
							<div
								class="d-flex justify-content-center align-items-center flex-wrap">
								<span class="text-danger fw-bold me-2"> <%= productList.get(i).getProductPriceAfterDiscount() %>₫
								</span> <span class="text-muted text-decoration-line-through me-2">
									<%= productList.get(i).getProductPrice() %>₫
								</span> <span class="badge bg-success"> -<%= productList.get(i).getProductDiscount() %>%
									off
								</span>
							</div>
						</div>
					</div>
				</a>
			</div>
			<% 
            } 
        %>
		</div>
	</div>

	<!-- end of list -->

	<!-- product with heavy deals -->
	<!--        <div class="container-fluid py-3 px-3" style="background: #f0fffe;">
            <h3>Hot Deals</h3>
            <div class="row row-cols-1 row-cols-md-4 g-3">
                <%
                    for (int i = 0; i < Math.min(4, topDeals.size()); i++) {
                %>
                <div class="col">
                    <a href="viewProduct.jsp?pid=<%=topDeals.get(i).getProductId()%>"
                       style="text-decoration: none;">
                        <div class="card h-100">
                            <div class="container text-center">
                                <img src="https://<%=topDeals.get(i).getProductImages()%>"
                                     class="card-img-top m-2"
                                     style="max-width: 100%; max-height: 200px; width: auto;">
                            </div>
                            <div class="card-body">
                                <h5 class="card-title text-center"><%=topDeals.get(i).getProductName()%></h5>

                                <div class="container text-center">
                                    <span class="real-price"><%=topDeals.get(i).getProductPriceAfterDiscount()%>&#8363;</span>
                                    &ensp;<span class="product-price"><%=topDeals.get(i).getProductPrice()%>&#8363;
                                    </span>&ensp;<span class="product-discount"><%=topDeals.get(i).getProductDiscount()%>&#37;
                                        off</span>
                                </div>
                            </div>
                        </div>
                    </a>
                </div>
              }    %>
            </div>
        </div>-->
	<!-- end -->

	<!-- confirmation message for successful order -->
	<%
	String order = (String) session.getAttribute("order");
	if (order != null) {
	%>
	<script type="text/javascript">
            console.log("testing..4...");
            Swal.fire({
                icon: 'success',
                title: 'Đơn hàng đã được đặt, Cảm ơn bạn đã tin dùng!',
                text: 'Thư xác nhận sẽ được gửi đến <%=user.getUserEmail()%>',
                width: 600,
                padding: '3em',
                showConfirmButton: false,
                timer: 5500,
                backdrop: `
                    rgba(0,0,123,0.4)
                  `
            });
        </script>
	<%
	}
	}
	session.removeAttribute("order");
	%>
	<!-- end of message -->

</body>
</html>