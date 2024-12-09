<%@page import="com.shop.model.Category"%>
<%@page import="java.util.List"%>
<%@page import="com.shop.service.Impl.CategoryServiceImpl"%>
<%@page import="com.shop.service.ICategoryService"%>
<%@page import="com.shop.model.Message"%>
<%@page import="com.shop.model.Admin"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@page errorPage="error_exception.jsp"%>
<%
Admin activeAdmin = (Admin) session.getAttribute("activeAdmin");
    if (activeAdmin == null) {
        Message message = new Message("Bạn chưa đăng nhập! Đăng nhập trước!!", "error", "alert-danger");
        session.setAttribute("message", message);
        response.sendRedirect("adminlogin.jsp");
        return;
    }
%>
<% ICategoryService iCategoryService = new CategoryServiceImpl();
List<Category> categoryList = iCategoryService.getAllCategories();%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Trang Admin</title>
        <%@include file="../../Components/common_css_js.jsp"%>
        <style type="text/css">
            .cus-active {
                background-color: #e6faec !important;
                width: 100%;
            }

            .list-btn {
                font-size: 18px !important;
            }

            .list-btn:hover {
                color: #28f0c1 !important;
            }

            .no-border {
                border: 0;
                box-shadow: none;
            }

            a {
                text-decoration: none;
            }
        </style>
    </head>
    <body>
        <!--admin dashboard -->
        <div class="container-fluid py-4 px-3">
            <%@include file="../../Components/alert_message.jsp"%>
            <div class="row">
                <div class="container text-center" id="details">
                    <img src="../../Images/admin.png" style="max-width: 180px;"
                         class="img-fluid">
                    <h3>
                        Chào mừng "<%=activeAdmin.getName()%>"
                    </h3>
                </div>
            </div>
            <div class="container">
                <div class="row px-3 py-3">
                    <div class="col-md-4">
                        <a href="display_category.jsp">
                            <div class="card text-bg-light mb-3 text-center">
                                <div class="card-body">
                                    <img src="../../Images/Category_icon.png" style="max-width: 80px;"
                                         class="img-fluid">
                                    <h4 class="card-title mt-3">Danh mục</h4>
                                </div>
                            </div>
                        </a>
                    </div>
                    <div class="col-md-4">
                        <a href="display_products.jsp">
                            <div class="card text-bg-light mb-3 text-center">
                                <div class="card-body">
                                    <img src="../../Images/Product_icon.png" style="max-width: 80px;"
                                         class="img-fluid">
                                    <h4 class="card-title mt-3">Sản phẩm</h4>
                                </div>
                            </div>
                        </a>
                    </div>
                    <div class="col-md-4">
                        <a href="display_orders.jsp">
                            <div class="card text-bg-light mb-3 text-center">
                                <div class="card-body">
                                    <img src="../../Images/Order_icon.png" style="max-width: 80px;"
                                         class="img-fluid">
                                    <h4 class="card-title mt-3">Đơn đặt hàng</h4>
                                </div>
                            </div>
                        </a>
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="row px-3">
                    <div class="col-md-4 offset-md-2">
                        <a href="display_users.jsp">
                            <div class="card text-bg-light mb-3 text-center">
                                <div class="card-body">
                                    <img src="../../Images/User_icon.png" style="max-width: 80px;"
                                         class="img-fluid">
                                    <h4 class="card-title mt-3">User's</h4>
                                </div>
                            </div>
                        </a>
                    </div>
                    <div class="col-md-4">
                        <a href="display_admin.jsp">
                            <div class="card text-bg-light mb-3 text-center">
                                <div class="card-body">
                                    <img src="../../Images/Admin_icon.png" style="max-width: 80px;"
                                         class="img-fluid">
                                    <h4 class="card-title mt-3">Admin</h4>
                                </div>
                            </div>
                        </a>
                    </div>
                </div>
            </div>
        </div>
        <!--end-->

        <div class="modal fade" id="add-category" tabindex="-1"
             aria-labelledby="addCategoryModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h1 class="modal-title fs-5" id="addCategoryModalLabel">Thêm danh mục ở đây</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                aria-label="Close"></button>
                    </div>
                    <form action="AddOperationController" method="post"
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
                    <form action="AddOperationController" method="post"
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

        <script type="text/javascript">
            function validate() {
                var dis = document.addProductForm.discount.value;
                if (dis > 100 || dis < 0) {
                    alert("Mã giảm giá phải từ 0 - 100!!");
                    return false;
                }
            }
        </script>
    </body>
</html>