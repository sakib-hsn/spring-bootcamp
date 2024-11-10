package com.codemaster.io;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    public static Connection getConnection() throws SQLException {
        String url = "127.0.0.1:33306";
        String username = "root";
        String password = "root";
        String dbName = "bootcamp_db";

        String jdbcUrl = "jdbc:mysql://" + url + "/" + dbName;
        System.out.println("jdbcUrl = " + jdbcUrl);

       return DriverManager.getConnection(jdbcUrl, username, password);
    }
}
