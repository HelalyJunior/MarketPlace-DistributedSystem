package com.example.done;

import javafx.fxml.FXML;
import javafx.scene.control.TreeTableColumn;

import javax.swing.tree.TreeCellEditor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GenerateReport extends testjdbc{

    @FXML
    private TreeTableColumn<String, Integer> balance_col;

    @FXML
    private TreeTableColumn<String, String> fname_col;

    @FXML
    private TreeTableColumn<String, String> lname_col;

    @FXML
    private TreeTableColumn<String, Integer> number_col;
    static PreparedStatement ps;
    static ResultSet rs;

    public static void generate()
    {
        Connection c = connect();
        String fetch = "SELECT FName,LName,Balance,NumberofOrders FROM Clients";
        try {
            ps = c.prepareStatement(fetch);
            rs = ps.executeQuery();
            while (rs.next()) {

            }
        }
        catch (Exception e)
        {
            AlertBox.display("Error!","Error");
        }
    }


}

