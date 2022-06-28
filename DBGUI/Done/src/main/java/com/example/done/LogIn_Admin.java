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

public class LogIn_Admin extends testjdbc{
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

    PreparedStatement ps;
    ResultSet rs;
    Connection c = connect();
    public void Back_pressed(javafx.event.ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("Welcome-Page.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void Login_Pressed(javafx.event.ActionEvent event) throws IOException, SQLException {
        String s1 = "SELECT Pass FROM Admins WHERE Username = '"+Username_txt.getText().trim()+"'";

        try {
            ps = c.prepareStatement(s1);
            rs = ps.executeQuery();
            if ( (rs.getString(1).trim()).equals(Password_txt.getText().trim()))
            {
                root = FXMLLoader.load(getClass().getResource("Admin-Auth.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            else
            {
                AlertBox.display("Error","Wrong Password!");
                Password_txt.setText("");
            }
        }
        catch (Exception e)
        {
            AlertBox.display("Error","Wrong Username!");
            Username_txt.setText("");
            Password_txt.setText("");
        }

    }
}
