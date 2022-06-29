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

}
