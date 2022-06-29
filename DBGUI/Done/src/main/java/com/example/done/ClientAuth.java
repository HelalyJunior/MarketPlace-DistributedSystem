package com.example.done;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class ClientAuth  {
    @FXML
    private Button deposit_btn;

    @FXML
    private Button display_btn;

    @FXML
    private Button shop_btn;

    @FXML
    public  Label welcome_lbl;

    public String username;

    public void shop_pressed(ActionEvent event)
    {
        List<Map<String, String>> rs=Api.getCart(username);
        try{
            for(int i=0;i< rs.size();i++)
            {
                System.out.println(rs.get(i).get("ProductName"));
                System.out.println(rs.get(i).get("Price"));
            }
        }

        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    public void display_pressed(ActionEvent event) {
        int balance = Api.getBalance(username);
        System.out.println(balance);
    }

    public void deposit_pressed(ActionEvent event) {
Api.AddToCart(username,2);
    }
}
