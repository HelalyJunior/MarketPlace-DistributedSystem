package com.example.done;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
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

    private Stage stage;

    private Scene scene;

    private Parent root;


    public void Back_pressed(javafx.event.ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Admin-Auth.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

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
