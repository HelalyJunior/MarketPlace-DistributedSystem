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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class orders implements Initializable
{

    public String username;

    public static ObservableList<Order> data;

    private Stage stage;

    private Scene scene;

    private Parent root;

    @FXML
    private TableColumn<Order, Integer> amount;

    @FXML
    private TableColumn<Order, String> name;

    @FXML
    private TableColumn<Order, Integer> order;

    @FXML
    private TableView<Order> table;

    @FXML
    void Back_pressed(ActionEvent event) throws IOException {
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

    public static void FetchingData(String s)
    {
        data =  FXCollections.observableArrayList();
        String[] sb = s.split(",");
        for(int i=0;i<sb.length;i++)
        {
            String[] st = sb[i].split("_");
            data.add(new Order(st[0],Integer.valueOf(st[1]),Integer.valueOf(st[2])));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        order.setCellValueFactory(new PropertyValueFactory<>("id"));
        table.setItems(data);
    }
}
