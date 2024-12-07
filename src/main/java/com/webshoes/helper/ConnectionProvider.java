package com.webshoes.helper;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionProvider{
	
	private final static String serverName = "localhost";
	private final static String dbName = "2NTSHOP_DATABASE";
	private final static String portNumber = "1433";
	private final static String instance = "";
	private final static String userID = "sa";
	private final static String password = "123456789";
            
        public static Connection getConnection(){
        	try {
        	String url = "jdbc:sqlserver://" + serverName + "\\" + instance + ":" + portNumber + ";databaseName=" + dbName;
    		if (instance == null || instance.trim().isEmpty()) {
    			url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName=" + dbName;
    		}
    		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    		return DriverManager.getConnection(url, userID, password);
        	}
        	catch (Exception e) {
        		e.printStackTrace();
			}
        	return null;
    }
}
