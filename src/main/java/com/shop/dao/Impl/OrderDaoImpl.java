package com.shop.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.shop.dao.IOrderDao;
import com.shop.model.Order;

public class OrderDaoImpl implements IOrderDao {

	private Connection con;

	public OrderDaoImpl(Connection con) {
		super();
		this.con = con;
	}

	public int insertOrder(Order order) {
		int id = 0;
		try {
			// Truy vấn SQL dành cho MSSQL
			String query = "INSERT INTO [orders] (orderid, status, paymentType, userId, date) OUTPUT INSERTED.id VALUES (?, ?, ?, ?, ?)";

			// Tạo PreparedStatement
			PreparedStatement psmt = this.con.prepareStatement(query);

			// Gán giá trị cho các tham số
			psmt.setString(1, order.getOrderId());
			psmt.setString(2, order.getStatus());
			psmt.setString(3, order.getPayementType());
			psmt.setInt(4, order.getUserId());
			psmt.setTimestamp(5, order.getDate());

			// Lấy ID được sinh ra từ OUTPUT INSERTED
			ResultSet rs = psmt.executeQuery();
			if (rs.next()) {
				id = rs.getInt(1); // ID sinh ra sẽ nằm ở cột đầu tiên
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	public List<Order> getAllOrderByUserId(int uid) {
		List<Order> list = new ArrayList<>();
		try {
			// Câu lệnh truy vấn SQL Server (cú pháp không thay đổi so với MySQL)
			String query = "SELECT * FROM orders WHERE userId = ?";

			// Tạo PreparedStatement
			PreparedStatement psmt = this.con.prepareStatement(query);
			psmt.setInt(1, uid);

			// Thực thi truy vấn
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				// Tạo đối tượng Order và gán giá trị
				Order order = new Order();
				order.setId(rs.getInt("id"));
				order.setOrderId(rs.getString("orderid"));
				order.setStatus(rs.getString("status"));
				order.setDate(rs.getTimestamp("date"));
				order.setPayementType(rs.getString("paymentType"));
				order.setUserId(uid);

				// Thêm vào danh sách
				list.add(order);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public Order getOrderById(int id) {
		Order order = new Order();
		try {
			// Câu truy vấn phù hợp với MSSQL
			String query = "SELECT * FROM [orders] WHERE id = ?";

			// Chuẩn bị câu lệnh với MSSQL
			PreparedStatement psmt = this.con.prepareStatement(query);
			psmt.setInt(1, id);

			// Thực thi câu lệnh và lấy kết quả
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				order.setId(rs.getInt("id"));
				order.setOrderId(rs.getString("orderid"));
				order.setStatus(rs.getString("status"));
				order.setDate(rs.getTimestamp("date"));
				order.setPayementType(rs.getString("paymentType"));
				order.setUserId(rs.getInt("userId"));
			}
		} catch (Exception e) {
			e.printStackTrace(); // Ghi log lỗi để dễ dàng tìm nguyên nhân
		}
		return order;
	}

	public List<Order> getAllOrder() {
		List<Order> list = new ArrayList<Order>();
		try {
			// Câu truy vấn cho MSSQL
			String query = "SELECT * FROM [orders];";

			// Tạo đối tượng Statement
			Statement statement = this.con.createStatement();

			// Thực thi câu truy vấn
			ResultSet rs = statement.executeQuery(query);

			// Duyệt qua kết quả
			while (rs.next()) {
				Order order = new Order();
				order.setId(rs.getInt("id")); // Cột 'id' trong bảng
				order.setOrderId(rs.getString("orderid")); // Cột 'orderid'
				order.setStatus(rs.getString("status")); // Cột 'status'
				order.setDate(rs.getTimestamp("date")); // Cột 'date'
				order.setPayementType(rs.getString("paymentType")); // Cột 'paymentType'
				order.setUserId(rs.getInt("userId")); // Cột 'userId'

				// Thêm đối tượng order vào danh sách
				list.add(order);
			}
		} catch (Exception e) {
			e.printStackTrace(); // Ghi log lỗi
		}
		return list; // Trả về danh sách kết quả
	}

	public void updateOrderStatus(int oid, String status) {
		String query = "UPDATE [orders] SET status = ? WHERE id = ?";
		try (PreparedStatement psmt = this.con.prepareStatement(query)) {
			// Gán giá trị cho các tham số
			psmt.setString(1, status);
			psmt.setInt(2, oid);

			// Thực thi câu lệnh
			int rowsUpdated = psmt.executeUpdate();
			if (rowsUpdated > 0) {
				System.out.println("Order status updated successfully for ID: " + oid);
			} else {
				System.out.println("No order found with ID: " + oid);
			}
		} catch (Exception e) {
			System.err.println("Error updating order status: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
