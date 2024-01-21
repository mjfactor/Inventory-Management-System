package com.example.invetorysystem;

import javafx.application.Platform;
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

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.UUID;

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
    @FXML
    private Label forgot_password;
    public boolean usernameExists(String username) {
        String sql = "SELECT * FROM admin WHERE username = ?";

        try (Connection connect = database.connectDb();
             PreparedStatement prepare = connect.prepareStatement(sql)) {

            prepare.setString(1, username);
            ResultSet result = prepare.executeQuery();

            if (result.next()) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public String generateTempPassword() {
        // Generate a temporary password
        // This is a simple example, consider using a more secure method
        return UUID.randomUUID().toString().substring(0, 30);
    }
    public void resetPassword(String username) {
        // Reset the password to the original one
        String sql = "UPDATE admin SET password = ? WHERE username = ?";

        try (Connection connect = database.connectDb();
             PreparedStatement prepare = connect.prepareStatement(sql)) {

            prepare.setString(1, "password");
            prepare.setString(2, username);
            prepare.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void forgotPassword() {
        String checkUsername = JOptionPane.showInputDialog("Enter your username");
        // if clicked cancel
        if (checkUsername == null) {
            return;
        }
        if (usernameExists(checkUsername)) {
            String tempPassword = generateTempPassword();
            String sql = "UPDATE admin SET password = ? WHERE username = ?";

            try (Connection connect = database.connectDb();
                 PreparedStatement prepare = connect.prepareStatement(sql)) {

                prepare.setString(1, tempPassword);
                prepare.setString(2, checkUsername);
                prepare.executeUpdate();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Temporary Password");
                alert.setHeaderText(null);

                TextArea textArea = new TextArea("Temp Password: "+tempPassword);
                textArea.setEditable(false);
                textArea.setWrapText(true);

                alert.getDialogPane().setContent(textArea);
                alert.showAndWait();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Username does not exist.");
            alert.showAndWait();
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Contact the developer to reset your password: +639951712402");
            alert.showAndWait();
        }
    }
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
                    if (password.getText().length() == 30) { // Assuming temp passwords are 8 characters long
                        resetPassword(username.getText());
                    }
                    login_button.getScene().getWindow().hide();  // hide login form
                    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("dashboard.fxml")));
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    stage.initStyle(StageStyle.UNDECORATED);  // Remove Stage Buttons (Minimize, maximize, close)
                    stage.getIcons().add(new javafx.scene.image.Image("com/example/invetorysystem/images/logo_white.png"));
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
                    password.setText("");
                    password_show.setText("");
                    username.setText("");
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
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Message");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to exit?");
        Optional<ButtonType> option = alert.showAndWait();
        if (option.get().equals(ButtonType.OK)) {
            System.exit(0);
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        makePasswordDontReadSpace();
        password_checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> showPassword());
        password_show.textProperty().bindBidirectional(password.textProperty());
        loginWhenEnterIsPressed();
        forgot_password.setOnMouseClicked(event -> forgotPassword());

        Platform.runLater(() -> {
            Stage stage = (Stage) main_frame.getScene().getWindow();
            stage.getIcons().add(new javafx.scene.image.Image("com/example/invetorysystem/images/logo_white.png"));
        });

    }
}