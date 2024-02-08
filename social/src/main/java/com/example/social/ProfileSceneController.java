package com.example.social;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class ProfileSceneController {

    @FXML
    private Text logedInUserText;

    @FXML
    public void initialize() {
        // Change the text when the scene is loaded
        if (SessionManager.getCurrentUser() != null) {
            logedInUserText.setText(SessionManager.getCurrentUser().getFullname());
        } else {
            System.out.println("SessionManager or currentUser is null");
        }
    }
}
