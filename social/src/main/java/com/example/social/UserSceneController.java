package com.example.social;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.Optional;

public class UserSceneController {
    private Stage stage;
    private Scene scene;

    @FXML
    private TextField emailText;

    @FXML
    private TextField fullnameText;

    @FXML
    private PasswordField passwordText;

    @FXML
    private TextField loginEmailText;

    @FXML
    public void switchToRegister(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Register_page.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void createUser(ActionEvent event) throws SQLException {
        UserService userService = new UserService();
        User user = new User(fullnameText.getText(), emailText.getText(), passwordText.getText());
        System.out.println(user.toString());
        userService.createUser(user);
    }

    @FXML
    public void Login(ActionEvent event) throws SQLException {
        User user1 = new User();
        Optional<User> user = Optional.of(new User());
        UserService userService = new UserService();
        Optional<User> userOptional = userService.findByEmail(loginEmailText.getText());
        if (userOptional.isPresent()) {
            user1 = userOptional.get();
            SessionManager.getInstance().setCurrentUser(user1);
        }
        String password = userOptional.map(User::getPassword).orElse(null);
        if (passwordText.getText().equals(password)) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("Profile_page.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("Incorrect Password");
        }

    }

    @FXML
    public void switchToLogin(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Login_page.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
