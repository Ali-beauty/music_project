package com.music.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static DBUtil jdbcUtil = new DBUtil();

    private final String driverClass = "com.mysql.jdbc.Driver";
    private final String url = "jdbc:mysql://localhost:3306/music?useUnicode=true&characterEncoding=utf8";
    private final String userName = "root";
    private final String password = "root";

    private DBUtil(){
        try {
            Class.forName(driverClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static DBUtil getInstance(){
        return jdbcUtil;
    }

    public Connection getConnection(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public void closeConnection(Connection conn){
        try {
            conn.close();
            conn = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
