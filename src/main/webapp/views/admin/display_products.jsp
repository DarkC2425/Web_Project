<%@page import="com.shop.model.Message"%>
<%@page errorPage="error_exception.jsp"%>
<%@page import="com.shop.service.Impl.UserServiceImpl"%>
<%@page import="com.shop.model.Product"%>
<%@page import="com.shop.service.Impl.ProductServiceImpl"%>
<%@page import="com.shop.model.Admin"%>
<%@page import="com.shop.model.Cart"%>
<%@page import="com.shop.service.Impl.CartServiceImpl"%>
<%@page import="com.shop.model.User"%>
<%@page import="java.util.List"%>
<%@page import="com.shop.model.Category"%>

<%@page import="com.shop.service.Impl.CategoryServiceImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%
Admin activeAdmin = (Admin) session.getAttribute("activeAdmin");
    if (activeAdmin == null) {
        Message message = new Message("Bạn chưa đăng nhập! Đăng nhập trước!!", "error", "alert-danger");
        session.setAttribute("message", message);
        response.sendRedirect("adminlogin.jsp");
        return;
    }
    UserServiceImpl userService = new UserServiceImpl();
    ProductServiceImpl productService = new ProductServiceImpl();
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
        <title>Xem sản phẩm</title>
        <%@include file="../../Components/common_css_js.jsp"%>
    </head>
    <body>
        <!-- update product -->
        <div class="container mt-3">
            <%@include file="../../Components/alert_message.jsp"%>
            <table class="table table-hover">
                <tr class="table-primary text-center" style="font-size: 20px;">
                    <th>Ảnh</th>
                    <th>Tên</th>
                    <th class="text-start">Danh mục</th>
                    <th>Giá</th>
                    <th class="text-start">Số lượng</th>
                    <th class="text-start">Giảm giá</th>
                    <th>Action</th>
                </tr>
                <%                            List<Product> productList = productService.getAllProducts();
                    for (Product prod : productList) {
                        String category = catService.getCategoryName(prod.getCategoryId());
                %>
                <tr class="text-center">
                    <td><img src="http://localhost:8080/DoAnCuoiKy/Product_imgs/<%=prod.getProductImages()%>"
                             style="width: 60px; height: 60px; width: auto;"></td>
                    <td class="text-start"><%=prod.getProductName()%></td>
                    
                    <td><%=category%></td>
                    <td><%=prod.getProductPriceAfterDiscount()%>&#8363;</td>
                    <td><%=prod.getProductQunatity()%></td>
                    <td><%=prod.getProductDiscount()%>%</td>
                    <td><a href="update_product.jsp?pid=<%=prod.getProductId()%>" role="button" class="btn btn-secondary">Cập nhật</a>&emsp;<a
                            href="../../AddOperationController?pid=<%=prod.getProductId()%>&operation=deleteProduct"
                            class="btn btn-danger" role="button">Xóa</a></td>
                </tr>
                <%
                    }
                %>
            </table>
        </div>
    </body>
</html>

