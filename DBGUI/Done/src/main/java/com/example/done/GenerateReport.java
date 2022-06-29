package com.example.done;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class GenerateReport implements Initializable {
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

    static ObservableList<Products> data;


    public static void FetchingData()
    {
        List<Map<String, String>> rs=Api.getAllItems();
        data =  FXCollections.observableArrayList();

            for (int i=0;i< rs.size();i++) {
                int total_income = (Integer.valueOf(rs.get(i).get("Price")) * Integer.valueOf(rs.get(i).get("Sold")));
                data.add(new Products(rs.get(i).get("ProductName"), Integer.valueOf(rs.get(i).get("Price")),
                        Integer.valueOf(rs.get(i).get("Stock")), Integer.valueOf(rs.get(i).get("Sold")), total_income));
            }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FetchingData();
        income.setCellValueFactory(new PropertyValueFactory<>("Income"));
        name.setCellValueFactory(new PropertyValueFactory<>("Product_name"));
        price.setCellValueFactory(new PropertyValueFactory<>("Price"));
        remainder.setCellValueFactory(new PropertyValueFactory<>("Remaining"));
        sold.setCellValueFactory(new PropertyValueFactory<>("Sold"));
        table.setItems(data);
    }

}
