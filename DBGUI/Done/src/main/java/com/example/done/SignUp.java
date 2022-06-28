package com.example.done;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.awt.event.ActionEvent;
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
    private Label suggest_lbl;
    PreparedStatement ps;
    ResultSet rs;

    public void Signup_Pressed(javafx.event.ActionEvent event)
    {
        int mobile = Integer.parseInt(mob_txt.getText());
        System.out.println(mobile);
        Connection c = connect();
        String insert = "INSERT INTO Clients(FName,LName,Username,PW,Mobile,Address) VALUES('"+FName_txt.getText()+"','"+LName_txt.getText()+"','"+Username_txt.getText()+"','"+pw_txt.getText()+"',?,'"+address_txt.getText()+"')";
        try {
            ps = c.prepareStatement(insert);
            ps.setInt(1,mobile);
            ps.executeUpdate();
            suggest_lbl.setText("Account Created Succesfully!");
        }
        catch (Exception e)
        {
            AlertBox.display("Error!","Username Already Exists");
            Username_txt.setText("");
        }
    }
}
