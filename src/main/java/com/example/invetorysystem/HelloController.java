package com.example.invetorysystem;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;

public class HelloController {
    @FXML
    private Button close;

    @FXML
    private Button login_button;

    @FXML
    private AnchorPane main_frame;

    @FXML
    private TextField password;

    @FXML
    private TextField username;


    //DATABASE TOOLS

    public void loginAdmin(){
        String sql = "SELECT * FROM admin WHERE username = ? and password = ?";
        Connection connect = database.connectDb();
        try{
            assert connect != null;
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setString(1, username.getText());
            prepare.setString(2, password.getText());
            ResultSet result = prepare.executeQuery();
            Alert alert;

            if (username.getText().isEmpty() || password.getText().isEmpty()){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Fill out Blank Fields");
                alert.showAndWait();
            }else{
                if(result.next()){ // If Authentication is correct
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Login!");
                    login_button.getScene().getWindow().hide();  // hide login form
                    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("dashboard.fxml")));
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    stage.initStyle(StageStyle.UNDECORATED);  // Remove Stage Buttons (Minimize, maximize, close)
                    stage.setScene(scene);
                    stage.show();

                    // Appear on center
                    Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
                    stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
                    stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);

                }else{
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("Incorrect Credentials");
                    alert.showAndWait();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // Exit Button
    public void close(){
        System.exit(0);
    }
}