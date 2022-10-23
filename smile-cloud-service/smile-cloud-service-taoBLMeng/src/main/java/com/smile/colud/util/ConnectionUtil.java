package com.smile.colud.util;

import java.sql.DriverManager;
import java.sql.SQLException;


import com.mysql.jdbc.Connection;

public class ConnectionUtil {

	  public static Connection getConn() {
	        String driver = "com.mysql.jdbc.Driver";
	        String url = "jdbc:mysql://localhost:3306/smile?useUnicode=true&characterEncoding=utf8&autoReconnect=true";
	        String username = "root";
	        String password = "root";
	        Connection conn = null;
	        try {
	            Class.forName(driver); //classLoader,加载对应驱动
	            conn = (Connection) DriverManager.getConnection(url, username, password);
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return conn;
	    }
}
