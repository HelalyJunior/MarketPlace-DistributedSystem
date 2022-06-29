package com.example.done;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LogIn_Client extends testjdbc{
    private Stage stage;

    private Scene scene;

    private Parent root;
    @FXML
    private PasswordField Password_txt;

    @FXML
    private TextField Username_txt;

    @FXML
    private Button back_btn;

    @FXML
    private Label password_lbl;

    @FXML
    private Label username_lbl;

    public void Back_pressed(javafx.event.ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("Welcome-Page.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void Login_Pressed(javafx.event.ActionEvent event) throws IOException, SQLException
    {
        String password = Api.getPassword("client",Username_txt.getText().trim());
        if ( password.equals(Password_txt.getText().trim()))
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Client-Auth.fxml"));
            root = loader.load();
            ClientAuth clientController = loader.getController();
            clientController.username=Username_txt.getText().trim();
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else
        {
            AlertBox.display("Error","Wrong Password! ");
            Password_txt.setText("");
        }
    }
}