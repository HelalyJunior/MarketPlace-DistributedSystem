package com.example.done;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomePage {
    private Stage stage;

    private Scene scene;

    private Parent root;

    @FXML
    private Button Admin_Btn;

    @FXML
    private Button Client_Btn;

    @FXML
    private Label Welcome_label;

    public void Admin_Pressed(javafx.event.ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Log-In-Admin.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void Client_Pressed(javafx.event.ActionEvent event) throws IOException
    {
        root = FXMLLoader.load(getClass().getResource("Client-Options.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
