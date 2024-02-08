package com.example.social;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private static Connection connection = null;

    public static Connection createConnection() throws SQLException {
        String PASSWORD = "admin";
        String USER = "root";
        String URL = "jdbc:mysql://localhost:3306/social";
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
        System.out.println("Connected To Database Successfully");
        return connection;
    }

    public void createUserTableIfNotExists() {
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

    public void createEdgesTableIfNotExists() {
        String sql = "CREATE TABLE IF NOT EXISTS edges ("
                + "id INT AUTO_INCREMENT PRIMARY KEY,"
                + "user_id1 INT NOT NULL,"
                + "user_id2 INT NOT NULL,"
                + "FOREIGN KEY (user_id1) REFERENCES users(id),"
                + "FOREIGN KEY (user_id2) REFERENCES users(id)"
                + ")";
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
