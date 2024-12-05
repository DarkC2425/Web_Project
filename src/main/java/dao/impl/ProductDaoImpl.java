package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import configs.DBConnectSQL;
import dao.IProductDao;
import models.Product;

public class ProductDaoImpl implements IProductDao {
	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;

	@Override
	public void insert(Product product) {

		String sql = "INSERT INTO Products (name, description, isActive, listImages, categoryID, rating, storeID, createdAt, updatedAt, price) VALUES (?,?,?,?,?,?,?,?,?,?)";
		try {
			conn = new DBConnectSQL().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, product.getName());
			ps.setString(2, product.getDescription());
			ps.setBoolean(3, product.getIsActive());
			ps.setString(4, product.getListImages());
			ps.setInt(5, product.getCategoryID());
			ps.setFloat(6, product.getRating());
			ps.setInt(7, product.getStoreID());
			ps.setDate(8, product.getCreatedAt());
			ps.setDate(9, product.getUpdatedAt());
			ps.setInt(10, product.getPrice());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(Product product) {
		String sql = "UPDATE Products name=?, description=?, isActive=?, listImages=?, categoryID=?, rating=?, storeID=?, updatedAt=?, price=? WHERE PID=?";
		try {
			conn = new DBConnectSQL().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, product.getName());
			ps.setString(2, product.getDescription());
			ps.setBoolean(3, product.getIsActive());
			ps.setString(4, product.getListImages());
			ps.setInt(5, product.getCategoryID());
			ps.setFloat(6, product.getRating());
			ps.setInt(7, product.getStoreID());
			ps.setDate(8, product.getUpdatedAt());
			ps.setInt(9, product.getPrice());
			ps.setInt(10, product.getPID());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete(int PID) {
		String sql = "DELETE Products WHERE PID=?";
		try {
			conn = new DBConnectSQL().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, PID);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public Product findByPID(int UID) {
		String sql = "SELECT * FROM Products WHERE UID=?";
		try {
			conn = new DBConnectSQL().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, UID);
			rs = ps.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				product.setPID(rs.getInt("PID"));
				product.setName(rs.getString("name"));
				product.setDescription(rs.getString("description"));
				product.setActive(rs.getBoolean("isActive"));
				product.setListImages(rs.getString("listImages"));
				product.setCategoryID(rs.getInt("categoryID"));
				product.setRating(rs.getFloat("rating"));
				product.setStoreID(rs.getInt("storeID"));
				product.setCreatedAt(rs.getDate("createdAt"));
				product.setUpdatedAt(rs.getDate("updatedAt"));
				product.setPrice(rs.getInt("price"));
				return product;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Product> findByName(String name) {
		String sql = "SELECT * FROM Products WHERE name=?";
		try {
			List<Product> products = new ArrayList<Product>();
			conn = new DBConnectSQL().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			rs = ps.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				product.setPID(rs.getInt("PID"));
				product.setName(rs.getString("name"));
				product.setDescription(rs.getString("description"));
				product.setActive(rs.getBoolean("isActive"));
				product.setListImages(rs.getString("listImages"));
				product.setCategoryID(rs.getInt("categoryID"));
				product.setRating(rs.getFloat("rating"));
				product.setStoreID(rs.getInt("storeID"));
				product.setCreatedAt(rs.getDate("createdAt"));
				product.setUpdatedAt(rs.getDate("updatedAt"));
				product.setPrice(rs.getInt("price"));
				products.add(product);
			}
			return products;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Product> listAll() {
		String sql = "SELECT * FROM Products";
		try {
			List<Product> products = new ArrayList<Product>();
			conn = new DBConnectSQL().getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				product.setPID(rs.getInt("PID"));
				product.setName(rs.getString("name"));
				product.setDescription(rs.getString("description"));
				product.setActive(rs.getBoolean("isActive"));
				product.setListImages(rs.getString("listImages"));
				product.setCategoryID(rs.getInt("categoryID"));
				product.setRating(rs.getFloat("rating"));
				product.setStoreID(rs.getInt("storeID"));
				product.setCreatedAt(rs.getDate("createdAt"));
				product.setUpdatedAt(rs.getDate("updatedAt"));
				product.setPrice(rs.getInt("price"));
				products.add(product);
			}
			return products;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Product> listBySID(int storeID) {
		String sql = "SELECT * FROM Products WHERE storeID=?";
		try {
			List<Product> products = new ArrayList<Product>();
			conn = new DBConnectSQL().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, storeID);
			rs = ps.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				product.setPID(rs.getInt("PID"));
				product.setName(rs.getString("name"));
				product.setDescription(rs.getString("description"));
				product.setActive(rs.getBoolean("isActive"));
				product.setListImages(rs.getString("listImages"));
				product.setCategoryID(rs.getInt("categoryID"));
				product.setRating(rs.getFloat("rating"));
				product.setStoreID(rs.getInt("storeID"));
				product.setCreatedAt(rs.getDate("createdAt"));
				product.setUpdatedAt(rs.getDate("updatedAt"));
				product.setPrice(rs.getInt("price"));
				products.add(product);
			}
			return products;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
