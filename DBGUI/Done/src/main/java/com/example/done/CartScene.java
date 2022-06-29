package com.example.done;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Label;

public class CartScene
{
    @FXML
    private Label mylabel;

    public void addText(ActionEvent actionEvent) {

        mylabel.setText("HelloWorld!");
    }
}
