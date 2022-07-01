package com.example.done;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Admin_Auth extends testjdbc {

    private Stage stage;

    private Scene scene;

    private Parent root;

    @FXML
    private Button Generate_btn;

    @FXML
    private Button Top_btn;


    public void Generate_Pressed(javafx.event.ActionEvent event) throws IOException {
        HelloApplication.client.output.println("report_");

        String s = HelloApplication.client.input.readLine();
        if( s.length()>0)
        {
            GenerateReport.FetchingData(s);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Generate-Report.fxml"));
            root = loader.load();
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

}
