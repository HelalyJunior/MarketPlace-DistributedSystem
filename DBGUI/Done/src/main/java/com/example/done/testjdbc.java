package com.example.done;

import java.sql.*;


public class testjdbc {
    public static Connection connect(){
        Connection con = null;
        try{
            String url = "jdbc:sqlite:DBGUI/Done/src/DB/MarketPlace.db";
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection(url); //connecting to our database
            System.out.println("Connected!");
        } catch(SQLException | ClassNotFoundException e){
            //TODO Aauto-generated catch block
            System.out.println(e+"");
        }
        return con;
    }


    //   c.close();

    public static void connect(String a) {
        Connection conn = null;
        try {

            String url = "jdbc:sqlite:DBGUI/Done/src/DB/MarketPlace.db";
            conn = DriverManager.getConnection(url);
            Statement s = conn.createStatement();

            //INSERT INTO `sql11418209`.`signup` (`mail`, `Name`, `Surname`, `Password`, `address`,`gender`) VALUES ('"+s1+"','"+s2+"', '"+s3+"', '"+s4+"', '"+s5+"','"+s6+"');"
            //User user = new User("mohamed","mostafa ragab","010002222222");
            // s.executeUpdate("INSERT INTO `main`.`Users`(`Mail`,`Name`,`Password`) VALUES ('omar',NULL,NULL);");
            //"UPDATE `main`.`User` SET `Borrowed Books` = '" + k + "' WHERE (`Name` = '" + Name + "');"
            s.executeUpdate(a);
            conn.close();

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

}