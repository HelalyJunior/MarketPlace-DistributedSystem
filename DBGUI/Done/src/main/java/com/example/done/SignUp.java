package com.example.done;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

public class SignUp extends testjdbc{
    @FXML
    private TextField FName_txt;

    @FXML
    private TextField LName_txt;

    @FXML
    private Button SignUp_btn;

    @FXML
    private TextField Username_txt;

    @FXML
    private TextField address_txt;

    @FXML
    private TextField mob_txt;

    @FXML
    private TextField pw_txt;

    @FXML
    private Label success;


    private Stage stage;

    private Scene scene;

    private Parent root;

    public void Signup_Pressed(javafx.event.ActionEvent event) throws IOException {
        StringBuffer sb = new StringBuffer();
        sb.append("register"+"_");
        sb.append(FName_txt.getText()+"_");
        sb.append(LName_txt.getText()+"_");
        sb.append(Username_txt.getText()+"_");
        sb.append(address_txt.getText()+"_");
        sb.append(mob_txt.getText()+"_");
        sb.append(pw_txt.getText());
        HelloApplication.client.output.println(sb);
        if(HelloApplication.client.input.readLine().equals("true"))
        {
            success.setText("Success");
        }
        else
        {
            success.setText("Failed");
        }
    }

    public void back(ActionEvent event) throws IOException
    {
        root = FXMLLoader.load(getClass().getResource("Client-Options.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}


