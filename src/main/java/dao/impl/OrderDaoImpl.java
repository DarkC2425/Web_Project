package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import configs.DBConnectSQL;
import dao.IOrdersDao;
import models.Order;

public class OrderDaoImpl implements IOrdersDao {
	public Connection conn = null;
    public PreparedStatement ps = null;
    public ResultSet rs = null;

    @Override
    public void insert(Order order) {
        String sql = "INSERT INTO Orders (UID, StockID, amount, AddressID, payment, createdAt, updatedAt, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            conn = new DBConnectSQL().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, order.getUID());
            ps.setInt(2, order.getStockID());
            ps.setInt(3, order.getAmount());
            ps.setInt(4, order.getAddressID());
            ps.setInt(5, order.getPayment());
            ps.setDate(6, order.getCreatedAt());
            ps.setDate(7, order.getUpdatedAt());
            ps.setInt(8, order.getStatus());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Order order) {
        String sql = "UPDATE Orders SET UID=?, StockID=?, amount=?, AddressID=?, payment=?, createdAt=?, updatedAt=?, status=? WHERE OID=?";
        try {
            conn = new DBConnectSQL().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, order.getUID());
            ps.setInt(2, order.getStockID());
            ps.setInt(3, order.getAmount());
            ps.setInt(4, order.getAddressID());
            ps.setInt(5, order.getPayment());
            ps.setDate(6, order.getUpdatedAt());
            ps.setInt(7, order.getStatus());
            ps.setInt(8, order.getOID());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int OID) {
        String sql = "DELETE FROM Orders WHERE OID=?";
        try {
            conn = new DBConnectSQL().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, OID);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Order findByOID(int OID) {
        String sql = "SELECT * FROM Orders WHERE OID=?";
        try {
            conn = new DBConnectSQL().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, OID);
            rs = ps.executeQuery();
            if (rs.next()) {
                Order order = new Order();
                order.setOID(rs.getInt("OID"));
                order.setUID(rs.getInt("UID"));
                order.setStockID(rs.getInt("StockID"));
                order.setAmount(rs.getInt("amount"));
                order.setAddressID(rs.getInt("AddressID"));
                order.setPayment(rs.getInt("payment"));
                order.setCreatedAt(rs.getDate("createdAt"));
                order.setUpdatedAt(rs.getDate("updatedAt"));
                order.setStatus(rs.getInt("status"));
                return order;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Order> findByUID(int UID) {
        String sql = "SELECT * FROM Orders WHERE UID=?";
        try {
            conn = new DBConnectSQL().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, UID);
            rs = ps.executeQuery();
            List<Order> orders = new ArrayList<>();
            while (rs.next()) {
                Order order = new Order();
                order.setOID(rs.getInt("OID"));
                order.setUID(rs.getInt("UID"));
                order.setStockID(rs.getInt("StockID"));
                order.setAmount(rs.getInt("amount"));
                order.setAddressID(rs.getInt("AddressID"));
                order.setPayment(rs.getInt("payment"));
                order.setCreatedAt(rs.getDate("createdAt"));
                order.setUpdatedAt(rs.getDate("updatedAt"));
                order.setStatus(rs.getInt("status"));
                orders.add(order);
            }
            return orders;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Order> listAll() {
        String sql = "SELECT * FROM Orders";
        try {
            conn = new DBConnectSQL().getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            List<Order> orders = new ArrayList<>();
            while (rs.next()) {
                Order order = new Order();
                order.setOID(rs.getInt("OID"));
                order.setUID(rs.getInt("UID"));
                order.setStockID(rs.getInt("StockID"));
                order.setAmount(rs.getInt("amount"));
                order.setAddressID(rs.getInt("AddressID"));
                order.setPayment(rs.getInt("payment"));
                order.setCreatedAt(rs.getDate("createdAt"));
                order.setUpdatedAt(rs.getDate("updatedAt"));
                order.setStatus(rs.getInt("status"));
                orders.add(order);
            }
            return orders;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
