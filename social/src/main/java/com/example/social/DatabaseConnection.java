package com.example.social.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private  static Connection connection = null;

    public Connection createConnection() throws SQLException {
        String PASSWORD = "admin";
        String USER = "root";
        String URL = "jdbc:mysql://localhost:3306/social";
        this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
        System.out.println("Connected To Database Successfully");
        return connection;
    }
    public void createTableIfNotExists() {
        String sql = "CREATE TABLE IF NOT EXISTS users ("
                + "id INT AUTO_INCREMENT PRIMARY KEY,"
                + "fullname VARCHAR(255) NOT NULL,"
                + "email VARCHAR(255) NOT NULL,"
                + "password VARCHAR(255) NOT NULL"
                + ")";
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
}
}
