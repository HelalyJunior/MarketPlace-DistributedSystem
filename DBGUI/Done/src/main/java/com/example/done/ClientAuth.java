package com.example.done;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class ClientAuth  {

    private Stage stage;

    private Scene scene;

    private Parent root;
    @FXML
    private Button deposit_btn;

    @FXML
    private Button display_btn;

    @FXML
    private Button shop_btn;

    @FXML
    public  Label welcome_lbl;

    public String username;

    public void shop_pressed(ActionEvent event) throws IOException
    {
//        List<Map<String, String>> rs=Api.getCart(username);
//        try{
//            for(int i=0;i< rs.size();i++)
//            {
//                System.out.println(rs.get(i).get("ProductName"));
//                System.out.println(rs.get(i).get("Price"));
//            }
//        }
//
//        catch(Exception e)
//        {
//            System.out.println(e);
//        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Shop.fxml"));
        root = loader.load();
        Shop clientController = loader.getController();
        clientController.username=username;
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void display_pressed(ActionEvent event) {
        int balance = Api.getBalance(username);
        System.out.println(balance);
    }

    public void deposit_pressed(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddBalance.fxml"));
        root = loader.load();
        AddBalance clientController = loader.getController();
        clientController.username=username;
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
