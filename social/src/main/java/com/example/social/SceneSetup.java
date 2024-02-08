package com.example.social;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.*;


public class SceneSetup extends Application {
    @Override
    public void start(Stage stage){
        Parent root ;

        try {
            root = FXMLLoader.load(getClass().getResource("Login_page.fxml"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws SQLException {
        DatabaseConnection DatabaseConnection = new DatabaseConnection();
        Connection connection = DatabaseConnection.createConnection();
        DatabaseConnection.createUserTableIfNotExists();
        DatabaseConnection.createEdgesTableIfNotExists();

        launch();
    }
}
