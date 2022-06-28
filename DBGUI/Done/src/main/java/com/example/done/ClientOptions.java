package com.example.done;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientOptions {
    private Stage stage;

    private Scene scene;

    private Parent root;

    @FXML
    private Button Login_btn;

    @FXML
    private Button Signup_btn;

    public void Login_Pressed(javafx.event.ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Log-In-Client.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void Signup_Pressed(javafx.event.ActionEvent event) throws IOException
    {

        root = FXMLLoader.load(getClass().getResource("Sign-Up.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}
