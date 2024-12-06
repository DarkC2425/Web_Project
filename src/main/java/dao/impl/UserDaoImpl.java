package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import configs.DBConnectSQL;
import dao.IUserDao;
import models.User;

public class UserDaoImpl implements IUserDao {
	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;

	@Override
	public User findByEmail(String email) {
		String sql = "select * from Users where email = ?";

		try {
			conn = new DBConnectSQL().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, email); // truyen tham so
			rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setUID(rs.getInt("UID"));
				user.setFirstName(rs.getString("firstName"));
				user.setLastName(rs.getString("lastName"));
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getString("phone"));
				user.setHashed_pasword(rs.getString("hashed_password"));
				user.setAvatar(rs.getString("avatar"));
				user.setAddress(rs.getString("address"));
				user.setCreatedAt(rs.getDate("createdAt"));
				user.setUpdatedAt(rs.getDate("updatedAt"));
				user.setRole(rs.getString("role"));
				return user;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public User findByUID(int UID) {
		String sql = "select * from Users where UID = ?";

		try {
			conn = new DBConnectSQL().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, UID); // truyen tham so
			rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setUID(rs.getInt("UID"));
				user.setFirstName(rs.getString("firstName"));
				user.setLastName(rs.getString("lastName"));
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getString("phone"));
				user.setHashed_pasword(rs.getString("hashed_password"));
				user.setAvatar(rs.getString("avatar"));
				user.setAddress(rs.getString("address"));
				user.setCreatedAt(rs.getDate("createdAt"));
				user.setUpdatedAt(rs.getDate("updatedAt"));
				user.setRole(rs.getString("role"));
				return user;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void insert(User user) {
		String sql = "INSERT INTO Users (firstName, lastName, email, phone, hashed_password, avatar, address, createdAt, updatedAt, role) VALUES (?,?,?,?,?,?,?,?,?,?)";
		try {
			conn = new DBConnectSQL().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getFirstName());
			ps.setString(2, user.getLastName());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getPhone());
			ps.setString(5, user.getHashed_pasword());
			ps.setString(6, user.getAvatar());
			ps.setString(7, user.getAddress());
			ps.setDate(8, user.getCreatedAt());
			ps.setDate(9, user.getUpdatedAt());
			ps.setString(10, user.getRole());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
