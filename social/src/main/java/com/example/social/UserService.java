package com.example.social;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.social.DatabaseConnection;

public class UserService {
Connection connection= null;

    public void createUser(User user) throws SQLException {
        Connection connection = DatabaseConnection.createConnection();
        String sql = "INSERT INTO users (fullname, email, password) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getFullname());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("User Created");
    }
    public Optional<User> findByEmail(String email) throws SQLException {
        Connection connection = DatabaseConnection.createConnection();
        String query = "SELECT * FROM users WHERE email = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    User user = new User(
                            resultSet.getString("fullname"),
                            resultSet.getString("email"),
                            resultSet.getString("password")
                    );
                    return Optional.of(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
    public List<User> findUsersWithSimilarName(String partialName) {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users WHERE fullname LIKE ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "%" + partialName + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int userId = resultSet.getInt("id");
                String fullname = resultSet.getString("fullname");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                users.add(new User(userId, fullname, email, password));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

}
