package com.example.invetorysystem;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.*;

import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class dashboardController implements Initializable {
    @FXML
    private Button addProduct_add;



    @FXML
    private Button addProduct_button;

    @FXML
    private Button addProduct_delete;
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
    private ComboBox<String> addProduct_status;

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
    private TableColumn<productData, String> column_addProduct_price;

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
    NumberFormat formatWithComma = NumberFormat.getNumberInstance(Locale.US);
    public int productId;
    private ObservableList<productData> addProductList;
    Connection connect;
    PreparedStatement prepare;
    ResultSet result;
    Statement statement;
    public void addProductsSearch(){
        FilteredList<productData> filteredList = new FilteredList<>(addProductList, e -> true);
        addProduct_search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(productData -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (productData.getProductName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (productData.getStatus().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (productData.getPrice().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }else{
                    return false;
                }

            });
        });
        SortedList<productData> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(addProduct_table.comparatorProperty());
        addProduct_table.setItems(sortedList);

    }
    public void addProductsAdd(){
        long priceLong = Long.parseLong(addProduct_price.getText());
        String formattedPrice = formatWithComma.format(priceLong);
        System.out.println(formattedPrice);
        String sql = "INSERT INTO products (productName, price, status, date)"
                + "VALUES (?, ?, ?, ?)";
        connect = database.connectDb();
        Alert alert;
        try {
            if (addProduct_name.getText().isEmpty()
                    || addProduct_price.getText().isEmpty()
                    || addProduct_status.getSelectionModel().getSelectedItem() == null
                    || Objects.equals(addProduct_status.getSelectionModel().getSelectedItem(), "Choose") ) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Fill out all the blank fields");
                alert.showAndWait();
            } else {
                // Check if product already exist
                String checkName = "SELECT productName FROM products WHERE productName = '" + addProduct_name.getText()+ "'";
                assert connect != null;
                statement = connect.createStatement();
                result = statement.executeQuery(checkName);
                if(result.next()){
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Product "+ addProduct_name.getText() +" already exist");
                    alert.showAndWait();
                    addProduct_name.setText("");

                }else{
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added");
                    alert.showAndWait();

                    PreparedStatement prepare = connect.prepareStatement(sql);
                    prepare.setString(1, addProduct_name.getText());
                    prepare.setString(2, addProduct_price.getText());
                    prepare.setString(3, addProduct_status.getSelectionModel().getSelectedItem());

                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    prepare.setDate(4, sqlDate);

                    prepare.executeUpdate();
                    addProductShowListData();
                    clearTextField();
                }

            }
        }catch (Exception ignored){
        }
    }

    public void addProductUpdate(){
        String sql = "UPDATE products SET productName = '" + addProduct_name.getText() + "', price = '" + addProduct_price.getText()+ "', status = '"
                + addProduct_status.getSelectionModel().getSelectedItem() + "' WHERE id = '" + productId + "'";

        connect = database.connectDb();
        Alert alert;

        try{
            if (addProduct_name.getText().isEmpty()
                    || addProduct_price.getText().isEmpty()
                    || addProduct_status.getSelectionModel().getSelectedItem() == null
                    || Objects.equals(addProduct_status.getSelectionModel().getSelectedItem(), "Choose")) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Fill out all the blank fields");
                alert.showAndWait();
            }else{
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Sure?");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want Update ID = " + productId);
                Optional <ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)){
                    assert connect != null;
                    statement = connect.createStatement();
                    statement.executeUpdate(sql);

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated");
                    alert.showAndWait();

                    addProductShowListData();
                    clearTextField();
                }
            }
        }catch (Exception ignored){

        }
    } // Update product
    public void addProductDelete(){
        String sql = "DELETE from products WHERE id = '" + productId + "'";
        connect = database.connectDb();
        Alert alert;
        try{
            if (addProduct_name.getText().isEmpty()
                    || addProduct_price.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Fill out all the blank fields");
                alert.showAndWait();
            }else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Sure?");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want Delete ID = " + productId);
                Optional<ButtonType> option = alert.showAndWait();
                if (option.get().equals(ButtonType.OK)){
                    assert connect != null;
                    statement = connect.createStatement();
                    statement.executeUpdate(sql);
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted");
                    alert.showAndWait();

                    addProductShowListData();
                    clearTextField();
                }
            }
        }catch (Exception ignored){

        }
    } // Delete product

    public void clearTextField(){
        addProduct_name.setText("");
        addProduct_price.setText("");
        addProduct_status.setValue("Choose");
    } // Clear all text-fields

    public void onlyNumInTextField(){
        addProduct_price.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (!t1.matches("\\d*")) {
                    addProduct_price.setText(t1.replaceAll("[^\\d]", ""));
                }
            }
        });
    } // Only numbers allow in Price textfield

    public ObservableList<productData> addProductsGetDataFromSQL(){
        ObservableList<productData> productList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM products";
        connect = database.connectDb();
        try {
            assert connect != null;
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            productData prodD;
            while(result.next()){
                prodD = new productData(result.getInt("id")
                        , result.getString("productName")
                        , result.getString("price")
                        , result.getString("status")
                        , result.getDate("date"));

                productList.add(prodD);
            }

        }catch (Exception ignored){

        }
        return productList;
    } //Get the data from the SQL

    public void addProductShowListData(){
        addProductList = addProductsGetDataFromSQL();
        column_addProduct_id.setCellValueFactory(new PropertyValueFactory<productData, Integer>("productId"));
        column_addProduct_name.setCellValueFactory(new PropertyValueFactory<productData, String>("productName"));
        column_addProduct_price.setCellValueFactory(new PropertyValueFactory<productData, String>("price"));
        column_addProduct_status.setCellValueFactory(new PropertyValueFactory<productData, String>("status"));
        addProduct_table.setItems(addProductList);

    } // Put the data from SQL to Table

    public void addProductsSelect(){
        productData prodD = addProduct_table.getSelectionModel().getSelectedItem();
        int num = addProduct_table.getSelectionModel().getSelectedIndex();
        if((num - 1) <- 1){
            return;
        }
        productId = prodD.getProductId();
        addProduct_name.setText(prodD.getProductName());
        addProduct_price.setText(String.valueOf(prodD.getPrice()));
        addProduct_status.setValue(prodD.getStatus());
    } // if you clicked data from table, it will fill the text-fields and combobox

    private final String[] listStatus = {"Available", "Not Available"};
    public void addProductListStatus(){
        List<String> listS = new ArrayList<>(Arrays.asList(listStatus));
        ObservableList<String> listData = FXCollections.observableArrayList(listS);
        addProduct_status.setItems(listData);
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
            addProductListStatus();
            addProductsSearch();
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
        addProductListStatus();
    }
}
