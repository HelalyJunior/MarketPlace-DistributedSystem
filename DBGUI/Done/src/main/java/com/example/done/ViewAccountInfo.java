package com.example.done;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewAccountInfo {

    private Stage stage;

    private Scene scene;

    private Parent root;

    @FXML
    private Label address;

    @FXML
    private Label balance;

    @FXML
    private Label lname;

    @FXML
    private Label uname;

    @FXML
    private Label phone;

    public void fill( String lName , String uName , String bal , String add , String phonenumber)
    {
        address.setText(add);
        balance.setText(bal);
        lname.setText(lName);
        uname.setText(uName);
        phone.setText(phonenumber);
    }



    public void back(ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Client-Auth.fxml"));
        root = loader.load();
        ClientAuth clientController = loader.getController();
        clientController.username=uname.getText();
        clientController.welcome_lbl.setText("Welcome "+ clientController.username);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
