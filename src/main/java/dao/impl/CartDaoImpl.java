package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import configs.DBConnectSQL;
import dao.ICartDao;
import models.Cart;

public class CartDaoImpl implements ICartDao{

	public Connection conn = null;
    public PreparedStatement ps = null;
    public ResultSet rs = null;

    @Override
    public void insert(Cart cart) {
        String sql = "INSERT INTO Carts (UID, StockID, amount, createdAt, updatedAt) VALUES (?, ?, ?, ?, ?)";
        try {
            conn = new DBConnectSQL().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, cart.getUID());
            ps.setInt(2, cart.getStockID());
            ps.setInt(3, cart.getAmount());
            ps.setDate(4, cart.getCreatedAt());
            ps.setDate(5, cart.getUpdatedAt());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Cart cart) {
        String sql = "UPDATE Carts SET UID=?, StockID=?, amount=?, updatedAt=? WHERE CartID=?";
        try {
            conn = new DBConnectSQL().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, cart.getUID());
            ps.setInt(2, cart.getStockID());
            ps.setInt(3, cart.getAmount());
            ps.setDate(4, cart.getUpdatedAt());
            ps.setInt(5, cart.getCartID());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int CartID) {
        String sql = "DELETE FROM Carts WHERE CartID=?";
        try {
            conn = new DBConnectSQL().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, CartID);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Cart findByCartID(int CartID) {
        String sql = "SELECT * FROM Carts WHERE CartID=?";
        try {
            conn = new DBConnectSQL().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, CartID);
            rs = ps.executeQuery();
            if (rs.next()) {
                Cart cart = new Cart();
                cart.setCartID(rs.getInt("CartID"));
                cart.setUID(rs.getInt("UID"));
                cart.setStockID(rs.getInt("StockID"));
                cart.setAmount(rs.getInt("amount"));
                cart.setCreatedAt(rs.getDate("createdAt"));
                cart.setUpdatedAt(rs.getDate("updatedAt"));
                return cart;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Cart> findByUID(int UID) {
        String sql = "SELECT * FROM Carts WHERE UID=?";
        try {
            conn = new DBConnectSQL().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, UID);
            rs = ps.executeQuery();
            List<Cart> carts = new ArrayList<>();
            while (rs.next()) {
                Cart cart = new Cart();
                cart.setCartID(rs.getInt("CartID"));
                cart.setUID(rs.getInt("UID"));
                cart.setStockID(rs.getInt("StockID"));
                cart.setAmount(rs.getInt("amount"));
                cart.setCreatedAt(rs.getDate("createdAt"));
                cart.setUpdatedAt(rs.getDate("updatedAt"));
                carts.add(cart);
            }
            return carts;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Cart> listAll() {
        String sql = "SELECT * FROM Carts";
        try {
            conn = new DBConnectSQL().getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            List<Cart> carts = new ArrayList<>();
            while (rs.next()) {
                Cart cart = new Cart();
                cart.setCartID(rs.getInt("CartID"));
                cart.setUID(rs.getInt("UID"));
                cart.setStockID(rs.getInt("StockID"));
                cart.setAmount(rs.getInt("amount"));
                cart.setCreatedAt(rs.getDate("createdAt"));
                cart.setUpdatedAt(rs.getDate("updatedAt"));
                carts.add(cart);
            }
            return carts;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
