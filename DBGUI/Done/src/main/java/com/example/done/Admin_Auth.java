package com.example.done;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Admin_Auth extends testjdbc {
    private Stage stage;

    private Scene scene;

    private Parent root;

    PreparedStatement ps;
    ResultSet rs;
    Connection c = connect();
    @FXML
    private Button Generate_btn;

    @FXML
    private Button Top_btn;


    public void Generate_Pressed(javafx.event.ActionEvent event) throws IOException {


        root = FXMLLoader.load(getClass().getResource("Generate-Report.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }

    public void Top_Pressed(javafx.event.ActionEvent event) {

    }
}
