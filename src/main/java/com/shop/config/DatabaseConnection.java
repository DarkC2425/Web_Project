package com.shop.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
	private final static String serverName = "LAPTOP-EI0HMM8Q\\MSSQL2022";
	private final static String dbName = "2NTSHOP_DATABASE";
	private final static String portNumber = "1433";
	private final static String instance = "";
	private final static String userID = "sa";
	private final static String password = "Nhatle@2425";

	public static Connection getConnection() {
		try {
			String url = "jdbc:sqlserver://" + serverName + "\\" + instance + ":" + portNumber + ";databaseName="
					+ dbName;
			if (instance == null || instance.trim().isEmpty()) {
				url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName=" + dbName;
			}
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			return DriverManager.getConnection(url, userID, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
