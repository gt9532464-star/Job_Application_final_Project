package com.jobportal;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {

    private static final String URL = "jdbc:mysql://localhost:3306/jobportal?useSSL=false&allowPublicKeyRetrieval=true";
    private static final String USER = "root"; // change if needed
    private static final String PASS = "Gaurav"; // change your MySQL password

    public static Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
