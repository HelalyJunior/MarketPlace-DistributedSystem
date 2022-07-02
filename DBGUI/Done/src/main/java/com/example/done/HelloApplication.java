package com.example.done;

import SocketHandling.Client;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    public static Client client;
    @FXML
    private TextField address_txt;
    private Stage stage;

    private Scene scene;

    private Parent root;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("IPAddress.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        stage.setTitle("Our Market");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws IOException {
        launch();
    }

    public void connnect_pressed(ActionEvent event) throws IOException {
        if (address_txt.getText() == "")
        {
            AlertBox.display("Error!","Empty Field!");
            return;
        }
        try {
            client = new Client(address_txt.getText());
        }
        catch (Exception e)
        {
            AlertBox.display("Error!","Invalid IP Address!");
            return;
        }
        root = FXMLLoader.load(getClass().getResource("Welcome-Page.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}