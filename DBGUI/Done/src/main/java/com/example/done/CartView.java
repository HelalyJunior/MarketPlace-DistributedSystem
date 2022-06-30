package com.example.done;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CartView implements Initializable {

    public static ObservableList<Products> data;

    public static int total=0;

    @FXML
    private TableColumn<Products, String> item_col;

    @FXML
    private TableColumn<Products, Integer> price_col;

    @FXML
    private TableColumn<Products, Integer> quantity_col;

    @FXML
    private TableView<Products> table;

    @FXML
    private TableColumn<Products, Integer> total_col;

    @FXML
    private Label totalAmount;

    private Stage stage;

    private Scene scene;

    private Parent root;

    public String username;

    public static void FetchingData(String s)
    {
        data =  FXCollections.observableArrayList();
        String[] sb = s.split(",");
        for(int i=0;i<sb.length;i++)
        {
            String[] st = sb[i].split("_");
            int total_income = (Integer.valueOf(st[1]) * Integer.valueOf(st[2]));
            total+=total_income;
            data.add(new Products(st[0],Integer.valueOf(st[1]),0,Integer.valueOf(st[2]),total_income));
        }
    }

    public void back(ActionEvent event) throws IOException
    {
        total=0;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Shop.fxml"));
        root = loader.load();
        Shop clientController = loader.getController();
        clientController.username=username;
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        total_col.setCellValueFactory(new PropertyValueFactory<>("Income"));
        item_col.setCellValueFactory(new PropertyValueFactory<>("Product_name"));
        price_col.setCellValueFactory(new PropertyValueFactory<>("Price"));
        quantity_col.setCellValueFactory(new PropertyValueFactory<>("Sold"));
        table.setItems(data);
        totalAmount.setText(""+total);
    }

    public void purchase_pressed(ActionEvent event) throws IOException
    {
        if(total==0)
        {
            AlertBox.display("Error","Add Items to the cart");
        }
        else
        {
            HelloApplication.client.output.println("buy_"+username+"_"+total);
            if(HelloApplication.client.input.readLine().equals("true"))
            {
                Shop.cards.clear();
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
            else
            {
                AlertBox.display("Error","Check Balance and Availability of Items");
            }
        }
    }
}
