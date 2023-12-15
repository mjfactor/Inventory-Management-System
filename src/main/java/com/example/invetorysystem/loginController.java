package com.example.invetorysystem;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;
import java.util.ResourceBundle;

public class loginController implements Initializable {
    @FXML
    private Button close;

    @FXML
    private Button login_button;

    @FXML
    private AnchorPane main_frame;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    private CheckBox password_checkBox;

    @FXML
    private TextField password_show;

    @FXML
    private ImageView login_logo;

    public void makePasswordDontReadSpace(){
        password.textProperty().addListener((observable, oldValue, newValue) -> {
            if (password.getText().contains(" ")){
                password.setText(password.getText().replace(" ", ""));
            }
        });
    }
    public void showPassword(){
        if (password_checkBox.isSelected()){

            password_show.setVisible(true);
            password_show.setEditable(true);
            password.setVisible(false);
        }else{

            password.setVisible(true);
            password_show.setVisible(false);
        }
    }
    public void loginAdmin(){
        String sql = "SELECT * FROM admin WHERE username = ? and password = ?";

        Connection connect = database.connectDb();
        try{
            assert connect != null;
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setString(1, username.getText());
            if (password_checkBox.isSelected()){
                prepare.setString(2, password_show.getText());
            }else{
                prepare.setString(2, password.getText());
            }


            ResultSet result = prepare.executeQuery();
            Alert alert;
            if (username.getText().isEmpty() || password.getText().isEmpty() ){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Fill out Blank Fields");
                alert.showAndWait();
            }else{
                if(result.next()){ // If Authentication is correct
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
                    alert.setHeaderText(null);
                    alert.setContentText("Incorrect Credentials");
                    alert.setTitle("Error");
                    alert.showAndWait();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void loginWhenEnterIsPressed(){
        login_button.defaultButtonProperty().bind(new SimpleBooleanProperty(true));
    }

    // Exit Button
    public void close(){
        System.exit(0);
    }

    public void displayLogo(){
        login_logo.setImage(new javafx.scene.image.Image("file:src/main/resources/com/example/invetorysystem/Images/logo.png"));
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        makePasswordDontReadSpace();
        password_checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> showPassword());
        password_show.textProperty().bindBidirectional(password.textProperty());
        loginWhenEnterIsPressed();
        displayLogo();



    }
}