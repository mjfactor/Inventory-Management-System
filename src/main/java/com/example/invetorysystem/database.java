package com.example.invetorysystem;
import java.sql.Connection;
import java.sql.*;
public class database {
    public static Connection connectDb(){
        try {
            Class.forName("com.mysql.jdbc.driver");
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/inventory", "root", "");
            return connect;
        }catch (Exception e){
            e.printStackTrace();
        }
    return null;
    }
}
