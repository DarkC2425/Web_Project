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

<style>
    .navbar {
        font-weight: 500;
        background: linear-gradient(109.6deg, rgb(9, 154, 151) 11.2%, rgb(255, 99, 0) 91.1%);
    }

    .nav-link {
        color: white !important;
        font-weight: bold;
        transition: color 0.3s ease;
    }

    .nav-link:hover {
        color: #add8e6 !important;
    }

    .dropdown-menu {
        background-color: #ffffff !important;
        border-color: #949494;
    }

    .dropdown-menu li a {
        color: #333 !important;
        transition: background-color 0.3s ease, color 0.3s ease;
    }

    .dropdown-menu li a:hover {
        background-color: #25e8b1 !important;
        color: white !important;
    }

    .btn-outline-light {
        color: white;
        border-color: white;
        transition: background-color 0.3s ease, color 0.3s ease;
    }

    .btn-outline-light:hover {
        background-color: #0033cc;
        color: #add8e6;
    }

    .navbar-brand {
        color: white !important;
    }

    .navbar-toggler {
        border-color: rgba(255, 255, 255, 1.2);
    }

    .navbar-toggler-icon {
        background-color: #96e0dd;
    }

    .badge {
        background-color: #FF0000;
        color: white;
    }
</style>



<nav class="navbar navbar-expand-lg custom-color" data-bs-theme="dark">

    <!-- admin nav bar -->
    <%
    if (admin != null) {
    %>
    <div class="container">
        <a class="navbar-brand" href="http://localhost:8080/DoAnCuoiKy/views/admin/admin.jsp"><i
                class="fa-sharp fa-solid fa-house" style="color: #ffffff;"></i>&ensp;2NT</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false"
                aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">

            <div class="container text-end">
                <ul class="navbar-nav justify-content-end">
                    <li class="nav-item"><button type="button"
                                                 class="btn nav-link" data-bs-toggle="modal"
                                                 data-bs-target="#add-category"><i class="fa-solid fa-plus fa-xs"></i>Thêm danh mục</button></li>
                    <li class="nav-item"><button type="button"
                                                 class="btn nav-link" data-bs-toggle="modal"
                                                 data-bs-target="#add-product"><i class="fa-solid fa-plus fa-xs"></i>Thêm sản phẩm</button></li>
                    <li class="nav-item"><a class="nav-link" aria-current="page"
                                            href="admin.jsp"><%=admin.getName()%></a></li>
                    <li class="nav-item"><a class="nav-link" aria-current="page"
                                            href="http://localhost:8080/DoAnCuoiKy/LogoutController?user=admin"><i
                                class="fa-solid fa-user-slash fa-sm" style="color: #aaf9ff;"></i>&nbsp;Đăng xuất</a></li>
                </ul>
            </div>
        </div>
    </div>
    <%
    } else {
    %>

    <!-- end -->

    <!-- for all -->
    <div class="container">
        <a class="navbar-brand" href="http://localhost:8080/DoAnCuoiKy/index.jsp"><i
                class="fa-sharp fa-solid fa-house" style="color: #ffffff;"></i>&ensp;2NT</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false"
                aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item"><a class="nav-link" href="http://localhost:8080/DoAnCuoiKy/views/products.jsp"
                                        role="button" aria-expanded="false"> Sản phẩm </a>
                <li class="nav-item dropdown"><a
                        class="nav-link dropdown-toggle" href="http://localhost:8080/DoAnCuoiKy/views/#" role="button"
                        data-bs-toggle="dropdown" aria-expanded="false"> Danh mục </a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="http://localhost:8080/DoAnCuoiKy/views/products.jsp?category=0">Tất cả sản phẩm</a></li>
                            <%
                            for (Category c : categoryList) {
                            %>
                        <li><a class="dropdown-item"
                               href="http://localhost:8080/DoAnCuoiKy/views/products.jsp?category=<%=c.getCategoryId()%>"><%=c.getCategoryName()%></a></li>
                            <%
                            }
                            %>
                    </ul></li>
            </ul>
            <form class="d-flex pe-5" role="search" action="http://localhost:8080/DoAnCuoiKy/views/products.jsp"
                  method="get">
                <input name="search" class="form-control me-2" size="50"
                       type="search" placeholder="Tìm kiếm cho sản phẩm" aria-label="Search"
                       style="background-color: white !important;">
                <button class="btn btn-outline-light" type="submit">Search</button>
            </form>

            <!-- when user is logged in -->
            <%
            if (user != null) {
                                            CartServiceImpl cartService = new CartServiceImpl();
                                            int cartCount = cartService.getCartCountByUserId(user.getUserId());
            %>
            <ul class="navbar-nav ml-auto">
                <li class="nav-item active pe-3"><a
                        class="nav-link position-relative" aria-current="page"
                        href="http://localhost:8080/DoAnCuoiKy/views/user/cart.jsp"><i class="fa-solid fa-cart-shopping"
                                       style="color: #ffffff;"></i> &nbsp;Giỏ hàng<span
                                       class="position-absolute top-1 start-0 translate-middle badge rounded-pill bg-danger"><%=cartCount%></span></a></li>
                <li class="nav-item active pe-3"><a class="nav-link"
                                                    aria-current="page" href="http://localhost:8080/DoAnCuoiKy/views/user/profile.jsp"><%=user.getUserName()%></a></li>
                <li class="nav-item pe-3"><a class="nav-link"
                                             aria-current="page" href="http://localhost:8080/DoAnCuoiKy/LogoutController?user=user"><i
                            class="fa-solid fa-user-slash" style="color: #aaf9ff;"></i>&nbsp;Đăng xuất</a></li>
            </ul>
            <%
            } else {
            %>
            <ul class="navbar-nav ml-auto">
                <li class="nav-item active pe-2"><a class="nav-link"
                                                    aria-current="page" href="register.jsp"> <i
                            class="fa-solid fa-user-plus" style="color: #aaf9ff;"></i>&nbsp;Đăng ký
                    </a></li>
                <li class="nav-item pe-2"><a class="nav-link"
                                             aria-current="page" href="http://localhost:8080/DoAnCuoiKy/views/login.jsp"><i
                            class="fa-solid fa-user-lock" style="color: #aaf9ff;"></i>&nbsp;Đăng nhập</a></li>
                <li class="nav-item pe-2"><a class="nav-link"
                                             aria-current="page" href="http://localhost:8080/DoAnCuoiKy/views/admin/adminlogin.jsp">&nbsp;Admin</a></li>
            </ul>

        </div>
    </div>
    <%
            }
        }
    %>
    <!-- end  -->
    <!-- add category modal-->
        <div class="modal fade" id="add-category" tabindex="-1"
             aria-labelledby="addCategoryModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h1 class="modal-title fs-5" id="addCategoryModalLabel">Thêm danh mục ở đây</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                aria-label="Close"></button>
                    </div>
                    <form action="http://localhost:8080/DoAnCuoiKy/AddOperationController" method="post"
                          enctype="multipart/form-data">
                        <div class="modal-body">
                            <input type="hidden" name="operation" value="addCategory">

                            <div class="mb-3">
                                <label class="form-label"><b>Tên danh mục</b></label> <input
                                    type="text" name="category_name"
                                    placeholder="Nhập tên danh mục ở đây" class="form-control" required>
                            </div>
                            <div class="mb-3">
                                <label for="formFile" class="form-label"><b>Ảnh danh mục</b></label> <input class="form-control" type="file"
                                                                                                            name="category_img" id="formFile">
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary"
                                    data-bs-dismiss="modal">Đóng</button>
                            <button type="submit" class="btn btn-primary me-3">Thêm danh mục</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!-- end of modal -->

        <!-- add product modal-->
        <div class="modal fade" id="add-product" tabindex="-1"
             aria-labelledby="addProductModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <h1 class="modal-title fs-5" id="addProductModalLabel">Thêm sản phẩm ở đây</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                aria-label="Close"></button>
                    </div>
                    <form action="http://localhost:8080/DoAnCuoiKy/AddOperationController" method="post"
                          name="addProductForm" enctype="multipart/form-data">
                        <div class="modal-body">
                            <input type="hidden" name="operation" value="addProduct">
                            <div>
                                <label class="form-label"><b>Tên sản phẩm</b></label> <input
                                    type="text" name="name" placeholder="Nhập tên sản phẩm"
                                    class="form-control" required>
                            </div>
                            <div class="mb-2">
                                <label class="form-label"><b>Mô tả sản phẩm</b></label>
                                <textarea class="form-control" name="description" rows="4"
                                          placeholder="Nhập mô tả sản phẩm"></textarea>
                            </div>
                            <div class="row">
                                <div class="col-md-6 mb-2">
                                    <label class="form-label"><b>Giá sản phẩm</b></label> <input
                                        type="number" name="price" placeholder="Nhập giá"
                                        class="form-control" required>
                                </div>
                                <div class="col-md-6 mb-2">
                                    <label class="form-label"><b>Giảm giá</b></label> <input
                                        type="number" name="discount" onblur="validate()"
                                        placeholder="Nhập giảm giá!" class="form-control">
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6 mb-2">
                                    <label class="form-label"><b>Số lượng sản phẩm</b></label> <input
                                        type="number" name="quantity"
                                        placeholder="Nhập số lượng sản phẩm" class="form-control">
                                </div>
                                <div class="col-md-6 mb-2">
                                    <label class="form-label"><b>Chọn thể loại danh mục</b></label> <select
                                        name="categoryType" class="form-control">
                                        <option value="0">--Chọn danh mục--</option>
                                        <%
                                            for (Category c : categoryList) {
                                        %>
                                        <option value="<%=c.getCategoryId()%>">
                                            <%=c.getCategoryName()%></option>
                                            <%
                                                }
                                            %>
                                    </select>
                                </div>
                            </div>
                            <div class="mb-2">
                                <label class="form-label"><b>Ảnh sản phẩm</b></label> <input
                                    type="file" name="photo" class="form-control" required>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary"
                                    data-bs-dismiss="modal">Đóng</button>
                            <button type="submit" class="btn btn-primary me-3">Thêm sản phẩm</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!-- end of modal -->
</nav>

