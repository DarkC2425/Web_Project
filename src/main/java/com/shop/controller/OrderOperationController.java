package com.shop.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.shop.dao.Impl.CartDaoImpl;
import com.shop.dao.Impl.OrderDaoImpl;
import com.shop.dao.Impl.OrderedProductDaoImpl;
import com.shop.dao.Impl.ProductDaoImpl;
import com.shop.model.Cart;
import com.shop.model.Order;
import com.shop.model.OrderedProduct;
import com.shop.model.Product;
import com.shop.model.User;
import com.shop.util.DatabaseConnection;
import com.shop.util.MailMessenger;
import com.shop.util.OrderIdGenerator;

import java.time.LocalDateTime;

public class OrderOperationController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String from = (String) session.getAttribute("from");
        String paymentType = request.getParameter("paymentMode");
        User user = (User) session.getAttribute("activeUser");
        String orderId = OrderIdGenerator.getOrderId();
        String status = "Đã đặt hàng";
        LocalDateTime orderDateTime = LocalDateTime.now();
        
        if (from.trim().equals("cart")) {
            try {

                Order order = new Order(orderId, status, java.sql.Timestamp.valueOf(orderDateTime), paymentType, user.getUserId());
                OrderDaoImpl orderDaoImpl = new OrderDaoImpl(DatabaseConnection.getConnection());
                orderDaoImpl.insertOrder(order);

                CartDaoImpl cartDaoImpl = new CartDaoImpl(DatabaseConnection.getConnection());
                List<Cart> listOfCart = cartDaoImpl.getCartListByUserId(user.getUserId());
                OrderedProductDaoImpl orderedProductDaoImpl = new OrderedProductDaoImpl(DatabaseConnection.getConnection());
                ProductDaoImpl productDaoImpl = new ProductDaoImpl(DatabaseConnection.getConnection());
                for (Cart item : listOfCart) {

                    Product prod = productDaoImpl.getProductsByProductId(item.getProductId());
                    String prodName = prod.getProductName();
                    int prodQty = item.getQuantity();
                    float price = prod.getProductPriceAfterDiscount();
                    String image = prod.getProductImages();

                    OrderedProduct orderedProduct = new OrderedProduct(prodName, prodQty, price, image, orderId);
                    orderedProductDaoImpl.insertOrderedProduct(orderedProduct);
                }
                session.removeAttribute("from");
                session.removeAttribute("totalPrice");

                //removing all product from cart after successful order
                cartDaoImpl.removeAllProduct();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (from.trim().equals("buy")) {

            try {

                int pid = (int) session.getAttribute("pid");
                Order order = new Order(orderId, status, java.sql.Timestamp.valueOf(orderDateTime), paymentType, user.getUserId());
                OrderDaoImpl orderDaoImpl = new OrderDaoImpl(DatabaseConnection.getConnection());
                int id = orderDaoImpl.insertOrder(order);
                OrderedProductDaoImpl orderedProductDaoImpl = new OrderedProductDaoImpl(DatabaseConnection.getConnection());
                ProductDaoImpl productDaoImpl = new ProductDaoImpl(DatabaseConnection.getConnection());

                Product prod = productDaoImpl.getProductsByProductId(pid);
                String prodName = prod.getProductName();
                int prodQty = 1;
                float price = prod.getProductPriceAfterDiscount();
                String image = prod.getProductImages();

                OrderedProduct orderedProduct = new OrderedProduct(prodName, prodQty, price, image, orderId);
                orderedProductDaoImpl.insertOrderedProduct(orderedProduct);

                //updating(decreasing) quantity of product in database
                productDaoImpl.updateQuantity(pid, productDaoImpl.getProductQuantityById(pid) - 1);

                session.removeAttribute("from");
                session.removeAttribute("pid");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        session.setAttribute("order", "success");
        MailMessenger.successfullyOrderPlaced(user.getUserName(), user.getUserEmail(), orderId, new Date().toString());
        response.sendRedirect("index.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

}
