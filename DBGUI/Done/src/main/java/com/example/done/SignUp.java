package com.example.done;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

public class SignUp extends testjdbc{
    @FXML
    private TextField FName_txt;

    @FXML
    private TextField LName_txt;

    @FXML
    private Button SignUp_btn;

    @FXML
    private TextField Username_txt;

    @FXML
    private TextField address_txt;

    @FXML
    private TextField mob_txt;

    @FXML
    private TextField pw_txt;
    @FXML
    private Label suggest_lbl;
    PreparedStatement ps;
    ResultSet rs;

    public void Signup_Pressed(javafx.event.ActionEvent event)
    {




        //!!!!!!!!!!!!!!! YOU DIDN'T GET THE MOBILE FROM ITS TEXT BOX !!!!!!!!!!!!!!!!!!!1


//        int mobile = Integer.parseInt(mob_txt.getText());
//        System.out.println(mobile);
//        Connection c = connect();
//        String insert = "INSERT INTO Clients(FName,LName,Username,PW,Mobile,Address) VALUES('"+FName_txt.getText()+"','"+LName_txt.getText()+"','"+Username_txt.getText()+"','"+pw_txt.getText()+"',?,'"+address_txt.getText()+"')";
//        try {
//            ps = c.prepareStatement(insert);
//            ps.setInt(1,mobile);
//            ps.executeUpdate();
//            suggest_lbl.setText("Account Created Succesfully!");
//        }
//        catch (Exception e)
//        {
//            AlertBox.display("Error!","Username Already Exists");
//            Username_txt.setText("");
//        }


//        PreparedStatement ps;
//        ResultSet rs;
//        Connection c = connect();
//        String s1 = String.format("SELECT Username FROM CLIENTS WHERE username = \"%s\"",Username_txt.getText());
//        System.out.println(s1);
//
//        try {
//            ps = c.prepareStatement(s1);
//            rs=ps.executeQuery();
//            if (rs.next()){
//                AlertBox.display("Error!","Username Already Exists");
//                Username_txt.setText("");
//            }
//            else {
//                String s2 = "INSERT INTO Clients(FName,LName,Username,PW,Mobile,Address) VALUES('"+FName_txt.getText()+"','"+LName_txt.getText()+"','"+Username_txt.getText()+"','"+pw_txt.getText()+"',1220004031,'"+address_txt.getText()+"')";
//                System.out.println(s2);
//                ps = c.prepareStatement(s2);
//                ps.executeUpdate();
//
//            }
//        }
//        catch (Exception e)
//        {
//            System.out.println(e);
//
//        }
//        finally {
//            try {
//                c.close();
//            }
//            catch (Exception e) {
//
//            }
//        }

//        PreparedStatement ps;
//        Connection c = connect();
//        String s1 = "UPDATE Cart SET amount=amount-1 WHERE Username= 'panda' AND productID= 1";
//        System.out.println(s1);
//        try {
//            ps = c.prepareStatement(s1);
//            ps.executeUpdate();
//        }
//        catch (Exception e)
//        {
//            System.out.println(e);
//            return;
//        }
//        finally {
//            try {
//                c.close();
//            }
//            catch (Exception e)
//            {
//
//            }
//        }


        PreparedStatement ps;
        ResultSet rs;
        Connection c = connect();
        int balance = Api.getBalance("moazahmedm");
        String s2 = String.format("SELECT Price,Stock FROM PRODUCTS WHERE ID = %d ",2);
        try {
            System.out.println(s2);
            ps = c.prepareStatement(s2);
            rs = ps.executeQuery();
            int price =(rs.getInt("Price"))* 10;
            int stock =rs.getInt("Stock");
            if (price <= balance){
                if (50<=stock) {
                    Api.AddToCart("moazahmedm",2) ;
                    String s3 = String.format("UPDATE PRODUCTS SET Stock=Stock- %d , Sold = Sold + %d WHERE ID = %d ", 10, 10,2);
                    //s3 += String.format(";UPDATE CLIENTS SET Balance = Balance - %d WHERE Username = \"%s\"",price,"moazahmedm");
                    String s4 = String.format("UPDATE CLIENTS SET Balance = Balance - %d WHERE Username = \"%s\"",price,"moazahmedm");
                    ps = c.prepareStatement(s3);
                    ps.executeUpdate();
                    ps = c.prepareStatement(s4);
                    ps.executeUpdate();
                    System.out.println("success");
                }
                System.out.println("fail");
            }
            else {
                System.out.println("fail");
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
            System.out.println("fail");
        }
        finally {
            try {
                c.close();
            }
            catch (Exception e)
            {

            }
        }


    }

}


