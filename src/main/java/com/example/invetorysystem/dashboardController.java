package com.example.invetorysystem;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;

public class dashboardController implements Initializable {
    @FXML
    private Button addProduct_add;

    @FXML
    private TextField addProduct_brand;

    @FXML
    private Button addProduct_button;

    @FXML
    private Button addProduct_delete;

    @FXML
    private TextField addProduct_id;

    @FXML
    private ImageView addProduct_image;

    @FXML
    private TextField addProduct_name;

    @FXML
    private TextField addProduct_price;

    @FXML
    private Button addProduct_reset;

    @FXML
    private TextField addProduct_search;

    @FXML
    private TextField addProduct_status;

    @FXML
    private TableView<?> addProduct_table;

    @FXML
    private ComboBox<?> addProduct_type;

    @FXML
    private Button addProduct_update;

    @FXML
    private AnchorPane addProducts;

    @FXML
    private Button addProducts_btn;

    @FXML
    private AnchorPane addProducts_form;

    @FXML
    private Button logout;

    @FXML
    private Button close;

    @FXML
    private TableColumn<?, ?> column_addProduct_brand;

    @FXML
    private TableColumn<?, ?> column_addProduct_id;

    @FXML
    private TableColumn<?, ?> column_addProduct_name;

    @FXML
    private TableColumn<?, ?> column_addProduct_price;

    @FXML
    private TableColumn<?, ?> column_addProduct_status;

    @FXML
    private TableColumn<?, ?> column_addProduct_type;

    @FXML
    private TableColumn<?, ?> com_order_brand;

    @FXML
    private TableColumn<?, ?> com_order_name;

    @FXML
    private TableColumn<?, ?> com_order_price;

    @FXML
    private TableColumn<?, ?> com_order_quantity;

    @FXML
    private TableColumn<?, ?> com_order_type;

    @FXML
    private AnchorPane home;

    @FXML
    private Label home_availableProducts;

    @FXML
    private Button home_btn;

    @FXML
    private AnchorPane home_form;

    @FXML
    private AreaChart<?, ?> home_incomedataChart;

    @FXML
    private Label home_numberofOrders;

    @FXML
    private BarChart<?, ?> home_orderChart;

    @FXML
    private Label home_totalIncome;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button minimize;

    @FXML
    private TextField order_amount;

    @FXML
    private Label order_balance;

    @FXML
    private ComboBox<?> order_brandName;

    @FXML
    private Button order_payBtn;

    @FXML
    private ComboBox<?> order_productName;

    @FXML
    private ComboBox<?> order_productType;

    @FXML
    private Spinner<?> order_quantity;

    @FXML
    private Button order_recieptBtn;

    @FXML
    private Button order_resettBtn;

    @FXML
    private TableView<?> order_table;

    @FXML
    private Label order_total;

    @FXML
    private AnchorPane orders;

    @FXML
    private Button orders_btn;

    @FXML
    private AnchorPane orders_form;
    @FXML
    private Button order_addBtn;

    public void switchForm(MouseEvent event){
        if(event.getSource() == home_btn || event.getSource() == home){
            home_form.setVisible(true);
            orders_form.setVisible(false);
            addProducts_form.setVisible(false);
            home.setStyle("-fx-background-color: linear-gradient(to bottom right, #8cea50, #3ce03c)");
            orders.setStyle("-fx-background-color: transparent");
            addProducts.setStyle("-fx-background-color: transparent");

        }else if(event.getSource() == orders_btn || event.getSource() == orders){
            home_form.setVisible(false);
            orders_form.setVisible(true);
            addProducts_form.setVisible(false);
            home.setStyle("-fx-background-color: transparent");
            orders.setStyle("-fx-background-color: linear-gradient(to bottom right, #8cea50, #3ce03c)");
            addProducts.setStyle("-fx-background-color: transparent");
        }else if(event.getSource() == addProducts_btn || event.getSource() == addProducts){
            home_form.setVisible(false);
            orders_form.setVisible(false);
            addProducts_form.setVisible(true);
            home.setStyle("-fx-background-color: transparent");
            orders.setStyle("-fx-background-color: transparent");
            addProducts.setStyle("-fx-background-color: linear-gradient(to bottom right, #8cea50, #3ce03c)");
        }

    }

    private double x = 0;
    private double y = 0;


    public void logout(){
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to logout?");


            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)){
                logout.getScene().getWindow().hide();
                FXMLLoader fxmlLoader = new FXMLLoader(main.class.getResource("login.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(fxmlLoader.load(), 752, 551);
                stage.initStyle(StageStyle.UNDECORATED);

                scene.setOnMousePressed(mouseEvent -> {
                    x = mouseEvent.getSceneX();
                    y = mouseEvent.getSceneY();
                    scene.setCursor(Cursor.CLOSED_HAND);
                });
                scene.setOnMouseDragged(mouseEvent -> {
                    stage.setX(mouseEvent.getScreenX() - x);
                    stage.setY(mouseEvent.getScreenY() - y);
                    stage.setOpacity(.9);
                });
                scene.setOnMouseReleased(mouseEvent -> {
                    stage.setOpacity(1);
                    scene.setCursor(Cursor.DEFAULT);
                });

                stage.initStyle(StageStyle.TRANSPARENT);
                stage.setScene(scene);
                stage.show();
                Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
                stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
                stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
            }else{
                return;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void minimize(){

        Stage stage = (Stage)main_form.getScene().getWindow();
        stage.setIconified(true);
    }
    public void close(){
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
