package com.example.done;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class contro implements Initializable {
    @FXML
    private TableColumn<Store , Integer> income;

    @FXML
    private TableColumn<Store, String > name;

    @FXML
    private TableColumn<Store, Integer> price;

    @FXML
    private TableColumn<Store, Integer> remainder;

    @FXML
    private TableColumn<Store, Integer> sold;

    @FXML
    private TableView<Store> table;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        income.setCellValueFactory(new PropertyValueFactory<>("Income"));
        name.setCellValueFactory(new PropertyValueFactory<>("Product_name"));
        price.setCellValueFactory(new PropertyValueFactory<>("Price"));
        remainder.setCellValueFactory(new PropertyValueFactory<>("Remaining"));
        sold.setCellValueFactory(new PropertyValueFactory<>("Sold"));
        table.setItems(data);
    }

    ObservableList<Store> data = FXCollections.observableArrayList(
            new Store("Banana",20,35,200,2000),
            new Store("Apples",10,30,400,10000),
            new Store("Pizza",70,15,150,2132)
    );
}
