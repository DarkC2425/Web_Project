package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import configs.DBConnectSQL;
import dao.IStoreDao;
import models.Store;

public class StoreDaoImpl implements IStoreDao {
	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;
	@Override
	public void insert(Store store) {
		String sql = "INSERT INTO Stores (name, email, phone, ownerID, isActive, avatar, rating, createdAt, updatedAt) VALUES (?,?,?,?,?,?,?,?,?)";
		try {
			conn = new DBConnectSQL().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, store.getName());
			ps.setString(2, store.getEmail());
			ps.setString(3, store.getPhone());
			ps.setInt(4, store.getOwnerID());
			ps.setBoolean(5, store.getIsActive());
			ps.setString(6, store.getAvatar());
			ps.setFloat(7, store.getRating());
			ps.setDate(8, store.getCreatedAt());
			ps.setDate(9, store.getUpdatedAt());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(Store store) {
		String sql = "UPDATE Stores name=?, email=?, phone=?, ownerID=?, isActive=?, avatar=?, rating=?, createdAt=?, updatedAt=?) WHERE SID=?";
		try {
			conn = new DBConnectSQL().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, store.getName());
			ps.setString(2, store.getEmail());
			ps.setString(3, store.getPhone());
			ps.setInt(4, store.getOwnerID());
			ps.setBoolean(5, store.getIsActive());
			ps.setString(6, store.getAvatar());
			ps.setFloat(7, store.getRating());
			ps.setDate(9, store.getUpdatedAt());
			ps.setInt(10, store.getSID());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(int SID) {
		String sql = "DELETE Stores WHERE SID=?";
		try {
			conn = new DBConnectSQL().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(10, SID);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Store findBySID(int PID) {
		String sql = "SELECT * FROM Stores WHERE PID=?";
		try {
			conn = new DBConnectSQL().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, PID);
			rs = ps.executeQuery();
			while(rs.next())
			{
				Store store = new Store();
				store.setSID(rs.getInt("SID"));
				store.setName(rs.getString("name"));
				store.setEmail(rs.getString("email"));
				store.setPhone(rs.getString("phone"));
				store.setOwnerID(rs.getInt("ownerID"));
				store.setIsActive(rs.getBoolean("isActive"));
				store.setAvatar(rs.getString("avatar"));
				store.setRating(rs.getFloat("rating"));
				store.setCreatedAt(rs.getDate("createdAt"));
				store.setUpdatedAt(rs.getDate("updatedAt"));
				return store;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Store> findByName(String name) {
		String sql = "SELECT * FROM Stores WHERE name=?";
		try {
			conn = new DBConnectSQL().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			rs = ps.executeQuery();
			List<Store> stores = new ArrayList<Store>();
			while(rs.next())
			{
				Store store = new Store();
				store.setSID(rs.getInt("SID"));
				store.setName(rs.getString("name"));
				store.setEmail(rs.getString("email"));
				store.setPhone(rs.getString("phone"));
				store.setOwnerID(rs.getInt("ownerID"));
				store.setIsActive(rs.getBoolean("isActive"));
				store.setAvatar(rs.getString("avatar"));
				store.setRating(rs.getFloat("rating"));
				store.setCreatedAt(rs.getDate("createdAt"));
				store.setUpdatedAt(rs.getDate("updatedAt"));
				stores.add(store);
			}
			return stores;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
