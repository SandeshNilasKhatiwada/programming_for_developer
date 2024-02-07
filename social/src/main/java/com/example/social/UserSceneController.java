package com.example.social;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public void switchToRegister(ActionEvent event){
        try{
        Parent root = FXMLLoader.load(getClass().getResource("Register_page.fxml"));
        stage =  (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void switchToLogin(ActionEvent event){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("Login_page.fxml"));
            stage =  (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
