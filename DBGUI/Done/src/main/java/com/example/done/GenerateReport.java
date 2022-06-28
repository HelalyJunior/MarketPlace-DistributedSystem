package com.example.done;

import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class GenerateReport extends testjdbc  implements Initializable{
    @FXML
    private static TableColumn<Store, Integer> income;

    @FXML
    private static TableColumn<Store, String > name ;

    @FXML
    private static TableColumn<Store, Integer> price ;

    @FXML
    private static TableColumn<Store, Integer> remainder ;

    @FXML
    private static TableColumn<Store, Integer> sold ;

    @FXML
    private static TableView<Store> table ;

    static PreparedStatement ps;
    static ResultSet rs;
    private ObservableList<Store> data;
    public static Store save;




//    public static void generate()
//    {
//
//        Connection c = connect();
//        String fetch = "SELECT ProductName,Price,Stock,Sold FROM Products";
//        try {
//            ps = c.prepareStatement(fetch);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                int total_income =(rs.getInt(2)*rs.getInt(4));
//                //System.out.println(total_income);
//                save = new Store(rs.getString(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),total_income);
////                System.out.println(rs.getString(1));
////                System.out.println(rs.getInt(2));
////                System.out.println(rs.getInt(3));
////                System.out.println(rs.getInt(4));
//                System.out.println(save.getPrice());
//                data.add(save);
//                //System.out.println("hello");
//
//            }
//
////            for (int i =0; i < data.size(); i++)
////            {
////                System.out.println(data.get(i).getProduct_name());
////            }
//            //table.setEditable(false);
//
//            income.setCellValueFactory(new PropertyValueFactory<>("Income"));
//            name.setCellValueFactory(new PropertyValueFactory<>("Product_name"));
//            price.setCellValueFactory(new PropertyValueFactory<>("Price"));
//            remainder.setCellValueFactory(new PropertyValueFactory<>("Remaining"));
//            sold.setCellValueFactory(new PropertyValueFactory<>("Sold"));
//            //table.getColumns().addAll(name,price,remainder,sold,income);
//            table.setItems(data);
//
//        }
//        catch (Exception e)
//        {
//            System.out.println(e.getMessage());
//            AlertBox.display("Error!","Error");
//        }
//    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Connection c = connect();
        String fetch = "SELECT ProductName,Price,Stock,Sold FROM Products";
        data =  FXCollections.observableArrayList();
        try{
            ps = c.prepareStatement(fetch);
            rs = ps.executeQuery();
            while (rs.next())
            {
                int total_income =(rs.getInt(2)*rs.getInt(4));
                //save = new Store(rs.getString(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),total_income);
                data.add(new Store(rs.getString(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),total_income));

            }
            for (int i = 0 ; i < data.size();i++)
            {
                System.out.println(data.get(i).getProduct_name());
            }
            income.setCellValueFactory(new PropertyValueFactory<>("Income"));
            name.setCellValueFactory(new PropertyValueFactory<>("Product_name"));
            price.setCellValueFactory(new PropertyValueFactory<>("Price"));
            remainder.setCellValueFactory(new PropertyValueFactory<>("Remaining"));
            sold.setCellValueFactory(new PropertyValueFactory<>("Sold"));
            table.setItems(data);


        } catch (Exception e) {
            System.out.println(e.getMessage());
            AlertBox.display("Error!","Failed");
        }



    }
}

