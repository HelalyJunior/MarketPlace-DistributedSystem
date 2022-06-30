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

public class AddBalance {

    private Stage stage;

    private Scene scene;

    private Parent root;

    public String username;

    @FXML
    private Button back;

    @FXML
    private Button deposit;

    @FXML
    private Label status;

    @FXML
    private TextField money;


    @FXML
    void back(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Client-Auth.fxml"));
        root = loader.load();
        ClientAuth clientController = loader.getController();
        clientController.username=username;
        clientController.welcome_lbl.setText("Welcome "+ clientController.username);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void deposit(ActionEvent event) throws IOException {
        int moneyEntered = Integer.valueOf(money.getText());
        HelloApplication.client.output.println("deposit_"+username+"_"+moneyEntered);
        if ( HelloApplication.client.input.readLine().equals("true"))
            status.setText("SUCCESS");
        else
            status.setText("FAILED");
    }

}
