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

    public static void AddToCart(String username,int productId,int amount)
    {
        PreparedStatement ps;
        Connection c = connect();
        String s1 = String.format("INSERT INTO Cart values(\"%s\",%d,%d)",username,productId,amount);
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

    public static boolean Register (String[] s){
        PreparedStatement ps;
        ResultSet rs;
        Connection c = connect();
        String s1 = String.format("SELECT *  FROM CLIENTS WHERE username = \"%s\"",s[2]);
        System.out.println(s1);

        try {
            ps = c.prepareStatement(s1);
            rs=ps.executeQuery();
            if (rs.next()){
                return false;
            }
            else {

//                sb.append(FName_txt.getText()+"_");
//                sb.append(LName_txt.getText()+"_");
//                sb.append(Username_txt.getText()+"_");
//                sb.append(address_txt.getText()+"_");
//                sb.append(mob_txt.getText()+"_");
//                sb.append(pw_txt.getText());
//                HelloApplication.client.output.println(sb);
                String s2 = String.format("INSERT INTO CLIENTS (FName, LName, Username,PW,Mobile,Address) values(\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\")",s[1],s[2],s[3],s[6],s[5],s[4]);
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


    //    public static boolean buy (String username,int productID , int quantity){
//        PreparedStatement ps;
//        ResultSet rs;
//        Connection c = connect();
//        int balance = getBalance(username);
//        String s2 = String.format("SELECT Price,Stock FROM PRODUCTS WHERE ID = %d " ,productID);
//        try {
//            System.out.println(s2);
//            ps = c.prepareStatement(s2);
//            rs = ps.executeQuery();
//            int price =(rs.getInt("Price"))* quantity;
//            int stock =rs.getInt("Stock");
//            if (price <= balance){
//                if (quantity<=stock) {
//                    AddToCart(username, productID);
//                    String s3 = String.format("UPDATE PRODUCTS,CLIENTS SET Stock=Stock- %d , Sold = Sold + %d ,Balance = Balance - %d WHERE ID = %d AND Username = \"%s\"", quantity, quantity, price ,productID,username);
//                    ps = c.prepareStatement(s3);
//                    ps.executeUpdate();
//                    return true;
//                }
//                return false;
//            }
//            else {
//                return false;
//            }
//        }
//        catch (Exception e)
//        {
//            System.out.println(e);
//            return false;
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
//
//
//    }

    public static boolean buy (String username,int totalAmount){
        PreparedStatement ps;
        ResultSet rs;
        Connection c = connect();
        int balance = getBalance(username);
        ArrayList<Integer> ids = new ArrayList<Integer>();
        ArrayList<Integer> amounts = new ArrayList<Integer>();


        if(balance<totalAmount)
        {
            return false;
        }
        List<Map<String, String>> cart = getCart(username);

        for(int i=0;i<cart.size();i++)
        {
            if(Integer.valueOf(cart.get(i).get("amount"))>Integer.valueOf(cart.get(i).get("Stock")))
            {
                return false;
            }
            ids.add(Integer.valueOf(cart.get(i).get("Id")));
            amounts.add(Integer.valueOf(cart.get(i).get("amount")));

        }

        //Update Products
        for(int i=0;i<cart.size();i++)
        {
            String s2 = String.format("UPDATE Products SET Stock = Stock-%d , Sold=Sold+%d WHERE Id=%d" ,amounts.get(i),amounts.get(i),ids.get(i));
            try {
                ps = c.prepareStatement(s2);
                ps.executeUpdate();
            }
            catch (Exception e)
            {
                System.out.println(e);
                return false;
            }
        }
        try {
            c.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        //Clear Cart
        Api.clearAll(username);

        //Update balance
        Api.addBalance(username,-totalAmount);

        // Update Orders

        updateOrders(username,totalAmount);
return true;


    }


    public static List<Map<String, String>> getOrders(String username)
    {
        PreparedStatement ps;
        ResultSet rs=null;
        Connection c = connect();
        String s1 =String.format("SELECT * FROM Orders Where ClientName=\"%s\"",username);
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

    public static void updateOrders(String username,int totalAmount)
    {
        PreparedStatement ps;
        Connection c = connect();
        String s1 = String.format("INSERT INTO Orders(ClientName,Total_Amount) values(\"%s\",%d)",username,totalAmount);
        try {
            ps = c.prepareStatement(s1);
            ps.executeUpdate();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        finally {
            try {
                c.close();
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
        }
    }

    public static void clearAll(String username)
    {
        PreparedStatement ps;
        Connection c = connect();
        String s1 = String.format("DELETE FROM Cart WHERE username =\"%s\"",username);
        try {
            ps = c.prepareStatement(s1);
            ps.executeUpdate();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        finally {
            try {
                c.close();
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
        }
    }


    public static boolean addBalance(String username,int balance)
    {
        PreparedStatement ps;
        Connection c = connect();
        String s1 = String.format("UPDATE Clients SET Balance= Balance+%d WHERE Username=\"%s\"",balance,username);
        try {
            ps = c.prepareStatement(s1);
            ps.executeUpdate();
            return true;
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
                System.out.println(e);
            }
        }
    }

    public static List<Map<String, String>> Search(String searchItem) {

        PreparedStatement ps;
        ResultSet rs;
        Connection c = connect();
        String s1 = String.format("SELECT * FROM PRODUCTS WHERE Category like \"%s%%\" OR ProductName like \"%s%%\"", searchItem, searchItem);
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
        } catch (Exception e) {
            return null;
        } finally {
            try {
                c.close();
            } catch (Exception e) {

            }
        }
    }

    public static void addProducts(int ID ,String ProductName,int Price , int Stock , int Sold ,String image,String Category)
    {
        PreparedStatement ps;
        Connection c = connect();
        String s1 = String.format("INSERT INTO Products values(%d,\"%s\",%d,%d,%d,\"%s\",\"%s\")",ID,ProductName,Price,Stock,Sold,image,Category);
        System.out.println(s1);
        try {
            ps = c.prepareStatement(s1);
            ps.executeUpdate();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        finally {
            try {
                c.close();
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
        }
    }

}
