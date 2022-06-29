package com.example.done;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.done.testjdbc.connect;

public class Api
{

    public static int getBalance(String username)
    {
        PreparedStatement ps;
        ResultSet rs=null;
        Connection c = connect();
        String s1 = String.format("SELECT Balance FROM Clients WHERE username =\"%s\"",username);
        try {
            ps = c.prepareStatement(s1);
            rs = ps.executeQuery();
            return rs.getInt(1);
        }
        catch (Exception e)
        {
            return -1;
        }
        finally {
            try {
                c.close();
            }
            catch (Exception e)
            {
                return -1;
            }
        }
    }

    public static String getPassword(String type,String username)
    {
        String tableName = "Admins";
        if(type=="client")
        {
            tableName="Clients";
        }
        PreparedStatement ps;
        ResultSet rs;
        Connection c = connect();
        String s1 = String.format("SELECT PW FROM %s WHERE Username = \"%s\"",tableName,username);

        try {
            ps = c.prepareStatement(s1);
            rs = ps.executeQuery();
            return rs.getString(1).trim();
        }
        catch (Exception e)
        {
            return "ERROR";
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
    public static List<Map<String, String>> getAllItems()
    {
        PreparedStatement ps;
        ResultSet rs=null;
        Connection c = connect();
        String s1 ="SELECT * FROM Products";
        try {
            ps = c.prepareStatement(s1);
            rs = ps.executeQuery();
            List<Map<String, String>> list = new ArrayList<Map<String, String>>();
            ResultSetMetaData meta = rs.getMetaData();
            while (rs.next()) {
                Map map = new HashMap();
                for (int i = 1; i <= meta.getColumnCount(); i++) {
                    String key = meta.getColumnName(i);
                    String value = rs.getString(key);
                    map.put(key, value);
                }
                list.add(map);
            }
            return list;
        }
        catch (Exception e)
        {
            return null;
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

    public static void AddToCart(String username,int productId)
    {
        PreparedStatement ps;
        Connection c = connect();
        String s1 = String.format("INSERT INTO Cart values(\"%s\",%d,%d)",username,productId,1);
        System.out.println(s1);


        try {
            ps = c.prepareStatement(s1);
            ps.executeUpdate();
        }
        catch (Exception e)
        {
            System.out.println(e);
            return;
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

    public static List<Map<String, String>> getCart(String username)
    {
        PreparedStatement ps;
        ResultSet rs;
        Connection c = connect();
        String s1 = String.format("SELECT *  FROM Cart,Products WHERE username = \"%s\" AND Cart.productID=Products.Id",username);
        try {
            ps = c.prepareStatement(s1);
            rs=ps.executeQuery();
            List<Map<String, String>> list = new ArrayList<Map<String, String>>();
            ResultSetMetaData meta = rs.getMetaData();
            while (rs.next()) {
                Map map = new HashMap();
                for (int i = 1; i <= meta.getColumnCount(); i++) {
                    String key = meta.getColumnName(i);
                    String value = rs.getString(key);
                    map.put(key, value);
                }
                list.add(map);
            }
            return list;
        }
        catch (Exception e)
        {
            System.out.println(e);
            return null;
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

    public static boolean Register (String username,String password){

        PreparedStatement ps;
        ResultSet rs;
        Connection c = connect();
        String s1 = String.format("SELECT *  FROM CLIENTS WHERE username = \"%s\"",username);
        System.out.println(s1);

        try {
            ps = c.prepareStatement(s1);
            rs=ps.executeQuery();
            if (rs.next()){
                return false;
            }
            else {
                String s2 = String.format("INSERT INTO CLIENTS values(\"%s\",\"%s\")",username,password);
                ps = c.prepareStatement(s2);
                ps.executeUpdate();
                return true;
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
        finally {
            try {
                c.close();
            }
            catch (Exception e) {

            }
        }
    }

    // shouldnt we edit Products ?????????
    public static void Remove(String username,int productID){
        PreparedStatement ps;
        Connection c = connect();
        String s1 = String.format("UPDATE Cart SET amount=amount-1 WHERE Username= \"%s\" AND productID= %d",username,productID);
        System.out.println(s1);
        try {
            ps = c.prepareStatement(s1);
            ps.executeUpdate();
        }
        catch (Exception e)
        {
            System.out.println(e);
            return;
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

    public static List<Map<String, String>> returnAccountInfo(String username){
        PreparedStatement ps;
        ResultSet rs;
        Connection c = connect();
        String s1 = String.format("SELECT *  FROM CLIENTS WHERE Username = \"%s\"",username);
        System.out.println(s1);
        try {
            ps = c.prepareStatement(s1);
            rs = ps.executeQuery();
            List<Map<String, String>> list = new ArrayList<Map<String, String>>();
            ResultSetMetaData meta = rs.getMetaData();
            while (rs.next()) {
                Map map = new HashMap();
                for (int i = 1; i <= meta.getColumnCount(); i++) {
                    String key = meta.getColumnName(i);
                    String value = rs.getString(key);
                    map.put(key, value);
                }
                list.add(map);
            }
            return list;
//            if (rs.next()){
//                String FNme= rs.getString("FName");
//                String LName= rs.getString("LName");
//                String user_name= rs.getString("Username");
//                String password= rs.getString("PW");
//                int mobile =rs.getInt("Mobile");
//                String address= rs.getString("Address");
//                int balance =rs.getInt("Balance");
//                int number =rs.getInt("NumberofOrders");
//                String mob=String.valueOf(mobile);
//                String bal=String.valueOf(balance);
//                String num=String.valueOf(number);

            //}

        }
        catch (Exception e)
        {
            return null;
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

    public static boolean buy (String username,int productID , int quantity){
        PreparedStatement ps;
        ResultSet rs;
        Connection c = connect();
        int balance = getBalance(username);
        String s2 = String.format("SELECT Price,Stock FROM PRODUCTS WHERE ID = %d " ,productID);
        try {
            System.out.println(s2);
            ps = c.prepareStatement(s2);
            rs = ps.executeQuery();
            int price =(rs.getInt("Price"))* quantity;
            int stock =rs.getInt("Stock");
            if (price <= balance){
                if (quantity<=stock) {
                    AddToCart(username, productID);
                    String s3 = String.format("UPDATE PRODUCTS,CLIENTS SET Stock=Stock- %d , Sold = Sold + %d ,Balance = Balance - %d WHERE ID = %d AND Username = \"%s\"", quantity, quantity, price ,productID,username);
                    ps = c.prepareStatement(s3);
                    ps.executeUpdate();
                    return true;
                }
                return false;
            }
            else {
                return false;
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
            return false;
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
