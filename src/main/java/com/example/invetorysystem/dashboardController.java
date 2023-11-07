package com.example.invetorysystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
    private TableView<productData> addProduct_table;

    @FXML
    private ComboBox<?> addProduct_type;

    @FXML
    private ComboBox<?> addProduct_status;

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
    private TableColumn<productData, String> column_addProduct_brand;

    @FXML
    private TableColumn<productData, Integer> column_addProduct_id;

    @FXML
    private TableColumn<productData, String> column_addProduct_name;

    @FXML
    private TableColumn<productData, Double> column_addProduct_price;

    @FXML
    private TableColumn<productData, String> column_addProduct_status;

    @FXML
    private TableColumn<productData, String> column_addProduct_type;

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

    public void addProductsAdd(){
        String sql = "INSERT INTO product (product_id, type, brand, productName, price, status, image, date)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        Connection connect = database.connectDb();
        try {
            Alert alert;

            if (addProduct_id.getText().isEmpty() || addProduct_type.getSelectionModel().getSelectedItem() == null || addProduct_brand.getText().isEmpty() || addProduct_name.getText().isEmpty()
                    || addProduct_price.getText().isEmpty() ||
                    addProduct_status.getSelectionModel().getSelectedItem() == null || Objects.equals(getData.path, "")) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("error");
                alert.setHeaderText(null);
                alert.setContentText("Fill out all the blank fields");
                alert.showAndWait();
            } else {
                assert connect != null;
                PreparedStatement prepare = connect.prepareStatement(sql);
                prepare.setInt(1, Integer.parseInt(addProduct_id.getText()));
                prepare.setString(2, (String) addProduct_type.getSelectionModel().getSelectedItem());
                prepare.setString(3, addProduct_brand.getText());
                prepare.setString(4, addProduct_name.getText());
                prepare.setDouble(5, Double.parseDouble(addProduct_price.getText()));
                prepare.setString(6, (String) addProduct_status.getSelectionModel().getSelectedItem());

                String uri = getData.path;
                uri = uri.replace("\\", "\\\\");
                prepare.setString(7, uri);

                Date date = new Date();
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                prepare.setDate(8, sqlDate);
                prepare.executeUpdate();
                addProductShowListData();
                clearTextField();

            }
        }catch (Exception ignored){
        }
    }

    public void clearTextField(){
        addProduct_id.setText("");
        addProduct_type.getSelectionModel().getSelectedItem();
        addProduct_brand.setText("");
        addProduct_name.setText("");
        addProduct_price.setText("");
        addProduct_status.getSelectionModel().getSelectedItem();
        addProduct_image.setImage(null);
        getData.path = "";
    }

    public void addProductsImportImage(){
        FileChooser open = new FileChooser();
        open.setTitle("Open image file");
        open.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image File", "*jpg" , "*png"));

        File file = open.showOpenDialog(main_form.getScene().getWindow());
        if(file != null){
            getData.path = file.getAbsolutePath();
            Image image = new Image(file.toURI().toString(), 172, 154, false, true);
            addProduct_image.setImage(image);
        }
    }

    public ObservableList<productData> addProductsListData(){
        ObservableList<productData> productList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM product";
        Connection connect = database.connectDb();
        try {
            assert connect != null;
            // DATABASE TOOL
            PreparedStatement prepare = connect.prepareStatement(sql);
            ResultSet result = prepare.executeQuery();
            productData prodD;
            while(result.next()){
                prodD = new productData(result.getInt("product_id")
                        , result.getString("type")
                        , result.getString("brand")
                        , result.getString("productName")
                        , result.getDouble("price")
                        , result.getString("status")
                        , result.getString("image")
                        , result.getDate("date"));

                productList.add(prodD);
            }

        }catch (Exception ignored){

        }
        return productList;
    }

    public void addProductShowListData(){
        ObservableList<productData> addProductList = addProductsListData();
        column_addProduct_id.setCellValueFactory(new PropertyValueFactory<>("productId"));
        column_addProduct_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        column_addProduct_brand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        column_addProduct_name.setCellValueFactory(new PropertyValueFactory<>("productName"));
        column_addProduct_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        column_addProduct_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        addProduct_table.setItems(addProductList);

    }

    public void addProductsSelect(){

    }
    public void switchForm(MouseEvent event){
        if(event.getSource() == home_btn || event.getSource() == home){
            home_form.setVisible(true);
            orders_form.setVisible(false);
            addProducts_form.setVisible(false);
            home.setStyle("-fx-background-color: linear-gradient(to bottom right, #8cea50, #3ce03c)");
            orders.setStyle("-fx-background-color: transparent");
            addProducts.setStyle("-fx-background-color: transparent");

        }else if(event.getSource() == addProducts_btn || event.getSource() == addProducts){
            home_form.setVisible(false);
            orders_form.setVisible(false);
            addProducts_form.setVisible(true);
            home.setStyle("-fx-background-color: transparent");
            orders.setStyle("-fx-background-color: transparent");
            addProducts.setStyle("-fx-background-color: linear-gradient(to bottom right, #8cea50, #3ce03c)");
            addProductShowListData();

        }else if(event.getSource() == orders_btn || event.getSource() == orders){
            home_form.setVisible(false);
            orders_form.setVisible(true);
            addProducts_form.setVisible(false);
            home.setStyle("-fx-background-color: transparent");
            orders.setStyle("-fx-background-color: linear-gradient(to bottom right, #8cea50, #3ce03c)");
            addProducts.setStyle("-fx-background-color: transparent");
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
        addProductShowListData();
    }
}
