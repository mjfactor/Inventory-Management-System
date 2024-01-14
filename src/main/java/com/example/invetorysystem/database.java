package com.example.invetorysystem;
import java.sql.Connection;
import java.sql.*;
public class database {

    public static Connection connectDb(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory", "root", "your_password");

            return connect;

        }catch (Exception e){
            e.printStackTrace();

        }
    return null;
    }
}
