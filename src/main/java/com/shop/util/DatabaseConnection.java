package com.shop.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
	private final static String serverName = "DINO";
	private final static String dbName = "2NTSHOP_DATABASE";
	private final static String portNumber = "1433";
	private final static String instance = "";
	private final static String userID = "ssa";
	private final static String password = "24082004@Ny";

	public static Connection getConnection() {
		try {
			String url = "jdbc:sqlserver://" + serverName + "\\" + instance + ":" + portNumber + ";databaseName="
					+ dbName + ";trustServerCertificate=true";
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
