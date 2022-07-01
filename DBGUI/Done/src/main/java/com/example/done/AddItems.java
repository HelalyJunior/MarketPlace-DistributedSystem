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

public class AddItems {
    @FXML
    private Button AddItem_btn;

    @FXML
    private Button back;

    @FXML
    private TextField category_txt;

    @FXML
    private TextField id_txt;

    @FXML
    private TextField name_txt;

    @FXML
    private TextField price_txt;

    @FXML
    private TextField stock_txt;

    @FXML
    private Label success;
    private Stage stage;

    private Scene scene;

    private Parent root;

    public void Additem_pressed(ActionEvent event) throws IOException {

        HelloApplication.client.output.println("addProducts"+"_"+id_txt.getText()+"_"+name_txt.getText()+"_"+price_txt.getText()+"_"+stock_txt.getText()+"_"+category_txt.getText());
        if (HelloApplication.client.input.readLine().equals("true")) {
            success.setText("Item Added!");
            category_txt.setText("");
            id_txt.setText("");
            name_txt.setText("");
            price_txt.setText("");
            stock_txt.setText("");
        } else {
            success.setText("Failed");
            AlertBox.display("Error", "Item Id already Exists!");
            id_txt.setText("");

        }


    }

    public void back(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("Admin-Auth.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}
