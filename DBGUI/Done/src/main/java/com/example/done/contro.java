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
    private TableColumn<Products, Integer> income;

    @FXML
    private TableColumn<Products, String > name;

    @FXML
    private TableColumn<Products, Integer> price;

    @FXML
    private TableColumn<Products, Integer> remainder;

    @FXML
    private TableColumn<Products, Integer> sold;

    @FXML
    private TableView<Products> table;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        income.setCellValueFactory(new PropertyValueFactory<>("Income"));
        name.setCellValueFactory(new PropertyValueFactory<>("Product_name"));
        price.setCellValueFactory(new PropertyValueFactory<>("Price"));
        remainder.setCellValueFactory(new PropertyValueFactory<>("Remaining"));
        sold.setCellValueFactory(new PropertyValueFactory<>("Sold"));
        table.setItems(data);
    }

    ObservableList<Products> data = FXCollections.observableArrayList(
            new Products("Banana",20,35,200,2000),
            new Products("Apples",10,30,400,10000),
            new Products("Pizza",70,15,150,2132)
    );
}
