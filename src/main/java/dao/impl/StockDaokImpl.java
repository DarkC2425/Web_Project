package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import configs.DBConnectSQL;
import dao.IStockDao;
import models.Stock;

public class StockDaokImpl implements IStockDao {
	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;

	@Override
	public void insert(Stock stock) {
		String sql = "INSERT INTO Stocks (PID, name, color, size, createdAt, updatedAt) VALUES (?,?,?,?,?,?)";
		try {
			conn = new DBConnectSQL().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, stock.getPID());
			ps.setString(2, stock.getName());
			ps.setString(3, stock.getColor());
			ps.setString(4, stock.getSize());
			ps.setDate(5, stock.getCreatedAt());
			ps.setDate(6, stock.getUpdatedAt());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(Stock stock) {
		String sql = "UPDATE Stocks SET PID=?, name=?, color=?, size=?, updatedAt=? WHERE StockID=?";
		try {
			conn = new DBConnectSQL().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, stock.getPID());
			ps.setString(2, stock.getName());
			ps.setString(3, stock.getColor());
			ps.setString(4, stock.getSize());
			ps.setDate(5, stock.getUpdatedAt());
			ps.setInt(6, stock.getStockID());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete(int StockID) {
		String sql = "DELETE Stocks WHERE StockID=?";
		try {
			conn = new DBConnectSQL().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, StockID);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public Stock findByStockID(int StockID) {
		String sql = "SELECT * FROM Stocks WHERE StockID=?";
		try {
			conn = new DBConnectSQL().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, StockID);
			rs = ps.executeQuery();
			while (rs.next()) {
				Stock stock = new Stock();
				stock.setStockID(rs.getInt("StockID"));
				stock.setName(rs.getString("name"));
				stock.setColor(rs.getString("color"));
				stock.setCreatedAt(rs.getDate("createdAt"));
				stock.setUpdatedAt(rs.getDate("updatedAt"));
				return stock;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Stock> listAll() {
		String sql = "SELECT * FROM Stocks";
		try {
			conn = new DBConnectSQL().getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			List<Stock> stocks = new ArrayList<Stock>();
			while (rs.next()) {
				Stock stock = new Stock();
				stock.setStockID(rs.getInt("StockID"));
				stock.setName(rs.getString("name"));
				stock.setColor(rs.getString("color"));
				stock.setCreatedAt(rs.getDate("createdAt"));
				stock.setUpdatedAt(rs.getDate("updatedAt"));
				stocks.add(stock);
			}
			return stocks;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Stock> findByPID(int PID) {
		String sql = "SELECT * FROM Stocks WHERE StockID=?";
		try {
			conn = new DBConnectSQL().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, PID);
			rs = ps.executeQuery();
			List<Stock> stocks = new ArrayList<Stock>();
			while (rs.next()) {
				Stock stock = new Stock();
				stock.setStockID(rs.getInt("StockID"));
				stock.setName(rs.getString("name"));
				stock.setColor(rs.getString("color"));
				stock.setCreatedAt(rs.getDate("createdAt"));
				stock.setUpdatedAt(rs.getDate("updatedAt"));
				stocks.add(stock);
			}
			return stocks;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
