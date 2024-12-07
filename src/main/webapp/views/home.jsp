<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <!-- Tiêu đề -->
    <div class="container my-5 text-center">
        <h1>Sản phẩm bán chạy nhất</h1>
        <p>Danh sách các sản phẩm bán chạy theo thứ tự giá từ cao đến thấp.</p>
    </div>

    <!-- Danh sách sản phẩm -->
    <div class="container">
        <div class="row">
            <!-- Lặp qua danh sách sản phẩm -->
            <c:forEach var="product" items="${products}">
                <div class="col-md-3 mb-4">
                    <div class="card">
                        <img src="${product.imageUrl}" class="card-img-top" alt="${product.name}">
                        <div class="card-body">
                            <h5 class="card-title">${product.name}</h5>
                            <p class="card-text">${product.description}</p>
                            <p class="card-text"><strong>${product.price} VND</strong></p>
                        </div>
                        <div class="card-footer">
                            <a href="#" class="btn btn-primary">Mua ngay</a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>

    <!-- Bootstrap JS và Popper -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"></script>