package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import configs.DBConnectSQL;
import dao.ICategoryDao;
import models.Category;

public class CategoryDaoImpl implements ICategoryDao {
	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;

	@Override
	public void insert(Category category) {
		String sql = "INSERT INTO Categories (name, image, createdAt, updatedAt) VALUES (?,?,?,?)";
		try {
			conn = new DBConnectSQL().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, category.getName());
			ps.setString(2, category.getImage());
			ps.setDate(3, category.getCreatedAt());
			ps.setDate(4, category.getUpdatedAt());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(Category category) {
		String sql = "UPDATE Categories SET name=?, image=?, updatedAt=? WHERE CID=?";
		try {
			conn = new DBConnectSQL().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, category.getName());
			ps.setString(2, category.getImage());
			ps.setDate(3, category.getUpdatedAt());
			ps.setInt(4, category.getCID());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete(int CID) {
		String sql = "DELETE Categories WHERE CID=?";
		try {
			conn = new DBConnectSQL().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, CID);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public Category findByPID(int CID) {
		String sql = "SELECT * FROM Categories WHERE CID=?";
		try {
			conn = new DBConnectSQL().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, CID);
			rs = ps.executeQuery();
			while (rs.next()) {
				Category category = new Category();
				category.setCID(rs.getInt("CID"));
				category.setName(rs.getString("name"));
				category.setImage(rs.getString("image"));
				category.setCreatedAt(rs.getDate("createdAt"));
				category.setUpdatedAt(rs.getDate("updatedAt"));
				return category;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Category> findByName(String name) {
		String sql = "SELECT * FROM Categories WHERE name=?";
		try {
			conn = new DBConnectSQL().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			rs = ps.executeQuery();
			List<Category> categories = new ArrayList<Category>();
			while (rs.next()) {
				Category category = new Category();
				category.setCID(rs.getInt("CID"));
				category.setName(rs.getString("name"));
				category.setImage(rs.getString("image"));
				category.setCreatedAt(rs.getDate("createdAt"));
				category.setUpdatedAt(rs.getDate("updatedAt"));
				categories.add(category);
			}
			return categories;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Category> listAll() {
		String sql = "SELECT * FROM Categories";
		try {
			conn = new DBConnectSQL().getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			List<Category> categories = new ArrayList<Category>();
			while (rs.next()) {
				Category category = new Category();
				category.setCID(rs.getInt("CID"));
				category.setName(rs.getString("name"));
				category.setImage(rs.getString("image"));
				category.setCreatedAt(rs.getDate("createdAt"));
				category.setUpdatedAt(rs.getDate("updatedAt"));
				categories.add(category);
			}
			return categories;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
