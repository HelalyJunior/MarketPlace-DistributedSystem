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

import javax.swing.text.View;
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
        HelloApplication.client.output.println("shop_");
        String s = HelloApplication.client.input.readLine();
        if(s.length()>0)
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Shop.fxml"));
            Shop.getData(s);
            root = loader.load();
            Shop clientController = loader.getController();
            clientController.username=username;
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void display_pressed(ActionEvent event) throws IOException {
        HelloApplication.client.output.println("returnInfo_"+username);
        String s = HelloApplication.client.input.readLine();
        System.out.println(s);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewAccountInfo.fxml"));
        root = loader.load();
        ViewAccountInfo clientController = loader.getController();
        String[] st = s.split("_");
        clientController.fill(st[0],st[2],st[5],st[1],st[4]);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

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

    public void ordersPressed(ActionEvent event) throws IOException {
        HelloApplication.client.output.println("orders_"+username);
        String s = HelloApplication.client.input.readLine();
        if(s.equals("false"))
        {
            AlertBox.display("Error","No Orders Found !");
            return;
        }
        if( s.length()>0)
        {
            orders.FetchingData(s);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("orders.fxml"));
            root = loader.load();
            orders clientController = loader.getController();
            clientController.username=username;
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
}
