package com.example.social;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class EdgeService {
    public void addEdge(int userId1, int userId2) throws SQLException {
        Connection connection = DatabaseConnection.createConnection();
        String sql = "INSERT INTO edges (user_id1, user_id2) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId1);
            statement.setInt(2, userId2);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeEdge(int userId1, int userId2) throws SQLException {
        Connection connection = DatabaseConnection.createConnection();
        String sql = "DELETE FROM edges WHERE (user_id1 = ? AND user_id2 = ?) OR (user_id1 = ? AND user_id2 = ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId1);
            statement.setInt(2, userId2);
            statement.setInt(3, userId2);
            statement.setInt(4, userId1);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
