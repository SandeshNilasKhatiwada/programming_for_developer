package com.example.idm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DownloadManager extends Application {

    @Override
    public void start(Stage stage){
        Parent root ;

        try {
            root = FXMLLoader.load(getClass().getResource("download-manager-view.fxml"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
