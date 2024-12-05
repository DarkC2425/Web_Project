package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import configs.DBConnectSQL;
import dao.IAddressDao;
import models.Address;

public class AddressDaoImpl implements IAddressDao {
	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;
	@Override
	public void insert(Address address) {
		String sql = "INSERT INTO Addresses (UID, name, phone, address) VALUES (?,?,?,?)";
		try {
			conn = new DBConnectSQL().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, address.getUID());
			ps.setString(2, address.getName());
			ps.setString(3, address.getPhone());
			ps.setString(4, address.getAddress());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(Address address) {
		String sql = "UPDATE Addresses SET UID=?, name=?, phone=?, address=? WHERE AddressID=?";
		try {
			conn = new DBConnectSQL().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, address.getUID());
			ps.setString(2, address.getName());
			ps.setString(3, address.getPhone());
			ps.setString(4, address.getAddress());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(int AddressID) {
		String sql = "DELETE Addresses WHERE AddressID=?";
		try {
			conn = new DBConnectSQL().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, AddressID);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Address findByAddressID(int AddressID) {
		String sql = "SELECT * FROM Addresses WHERE AddressID=?";
		try {
			conn = new DBConnectSQL().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, AddressID);
			rs = ps.executeQuery();
			while (rs.next()) {
				Address address = new Address();
				address.setUID(rs.getInt("UID"));
				address.setName(rs.getString("name"));
				address.setPhone(rs.getString("phone"));
				address.setAddress(rs.getString("address"));
				return address;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Address> findByUID(int UID) {
		String sql = "SELECT * FROM Addresses WHERE AddressID=?";
		try {
			conn = new DBConnectSQL().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, UID);
			rs = ps.executeQuery();
			List<Address> addresses = new ArrayList<Address>();
			while (rs.next()) {
				Address address = new Address();
				address.setUID(rs.getInt("UID"));
				address.setName(rs.getString("name"));
				address.setPhone(rs.getString("phone"));
				address.setAddress(rs.getString("address"));
				addresses.add(address);
				return addresses;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Address> listAll() {
		String sql = "SELECT * FROM Addresses";
		try {
			conn = new DBConnectSQL().getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			List<Address> addresses = new ArrayList<Address>();
			while (rs.next()) {
				Address address = new Address();
				address.setUID(rs.getInt("UID"));
				address.setName(rs.getString("name"));
				address.setPhone(rs.getString("phone"));
				address.setAddress(rs.getString("address"));
				addresses.add(address);
				return addresses;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
