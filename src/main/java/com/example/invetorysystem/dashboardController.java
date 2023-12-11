package com.example.invetorysystem;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.*;
import java.text.NumberFormat;
import java.util.*;
import java.util.Date;
import java.util.concurrent.atomic.AtomicReference;

import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.skin.TableHeaderRow;
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
    private TextField customer_name;

    @FXML
    private TableColumn<productData, Integer> column_addProduct_id;

    @FXML
    private TableColumn<productData, String> column_addProduct_name;

    @FXML
    private TableColumn<productData, String> column_addProduct_price;

    @FXML
    private TableColumn<productData, String> column_addProduct_status;

    @FXML
    private TableColumn<customerData, String> com_order_customerName;
    @FXML
    private TableColumn<customerData, String> com_order_name;

    @FXML
    private TableColumn<customerData, String> com_order_price;

    @FXML
    private TableColumn<customerData, String> com_order_quantity;

    @FXML
    private TableColumn<customerData, String> com_order_type;


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
    private Label order_balanceLabel;

    @FXML
    private Label custom_priceLabel;

    @FXML
    private ComboBox<?> order_brandName;

    @FXML
    private Button order_payBtn;

    @FXML
    private ComboBox<String> order_productName;

    @FXML
    private ComboBox<String> order_productType;

    @FXML
    private Spinner<Integer> order_quantity;

    @FXML
    private Button order_recieptBtn;

    @FXML
    private Button order_resettBtn;

    @FXML
    private TableView<customerData> order_table;

    @FXML
    private Label order_totalLabel;
    @FXML
    private Label order_change;

    @FXML
    private AnchorPane orders;

    @FXML
    private Button orders_btn;


    @FXML
    private Button orders_clear;
    @FXML
    private Button orders_reset;

    @FXML
    private AnchorPane orders_form;
    @FXML
    private TextField order_customName;
    @FXML
    private TextField order_customPrice;
    @FXML
    private Button order_addBtn;

    NumberFormat formatWithComma = NumberFormat.getNumberInstance(Locale.US);


    public int productId;
    private ObservableList<productData> addProductList;
    private ObservableList<customerData> orderList;
    Connection connect;
    PreparedStatement prepare;
    ResultSet result;
    Statement statement;


    public void addProductsSearch() {
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
                } else return productData.getPrice().toLowerCase().contains(lowerCaseFilter);

            });
        });
        SortedList<productData> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(addProduct_table.comparatorProperty());
        addProduct_table.setItems(sortedList);

    } // Search product

    public void addProductsAdd() {

        String sql = "INSERT INTO products (productName, price, price_int, status, date)"
                + "VALUES (?, ?, ?, ?, ?)";
        connect = database.connectDb();
        Alert alert;
        try {
            if (addProduct_name.getText().isEmpty()
                    || addProduct_price.getText().isEmpty()
                    || addProduct_status.getSelectionModel().getSelectedItem() == null
                    || Objects.equals(addProduct_status.getSelectionModel().getSelectedItem(), "Choose")) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Fill out all the blank fields");
                alert.showAndWait();
            } else {
                // Check if product already exist

                String checkName = "SELECT * FROM products WHERE productName LIKE '" + addProduct_name.getText() + "%"+ "'";
                assert connect != null;
                statement = connect.createStatement();
                result = statement.executeQuery(checkName);
                if (result.next()) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Product " + addProduct_name.getText() + " already exist");
                    alert.showAndWait();
                    addProduct_name.setText("");

                } else {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added");
                    alert.showAndWait();

                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, addProduct_name.getText());
                    prepare.setString(2, "₱" + formatPrice(addProduct_price.getText()));
                    prepare.setInt(3, Integer.parseInt(addProduct_price.getText()));
                    prepare.setString(4, addProduct_status.getSelectionModel().getSelectedItem());

                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    prepare.setDate(5, sqlDate);

                    prepare.executeUpdate();
                    addProductShowListData();
                    clearTextField();
                }

            }
        } catch (Exception ignored) {
        }

    }  // Add product

    public void addProductUpdate() {
        String sql = "UPDATE products SET productName = '" + addProduct_name.getText() + "', price = '" + "₱" + formatPrice(addProduct_price.getText()) + "', price_int = '" + Integer.parseInt(addProduct_price.getText()) + "', status = '"
                + addProduct_status.getSelectionModel().getSelectedItem() + "' WHERE id = '" + productId + "'";

        connect = database.connectDb();
        Alert alert;

        try {
            if (addProduct_name.getText().isEmpty()
                    || addProduct_price.getText().isEmpty()
                    || addProduct_status.getSelectionModel().getSelectedItem() == null
                    || Objects.equals(addProduct_status.getSelectionModel().getSelectedItem(), "Choose")) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Fill out all the blank fields");
                alert.showAndWait();
            } else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Sure?");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want Update ID = " + productId);
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
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
        } catch (Exception ignored) {

        }
    } // Update product

    public void addProductDelete() {

        String sql = "DELETE from products WHERE id = '" + productId + "'";
        connect = database.connectDb();
        Alert alert;
        try {
            if (addProduct_name.getText().isEmpty()
                    || addProduct_price.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Click on the table to delete");
                alert.showAndWait();
            } else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Sure?");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want Delete ID = " + productId);
                Optional<ButtonType> option = alert.showAndWait();
                if (option.get().equals(ButtonType.OK)) {
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
        } catch (Exception ignored) {

        }
    } // Delete product

    public ObservableList<productData> addProductsGetDataFromSQL() {
        ObservableList<productData> productList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM products";
        connect = database.connectDb();
        try {
            assert connect != null;
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            productData prodD;
            while (result.next()) {
                prodD = new productData(result.getInt("id")
                        , result.getString("productName")
                        , result.getString("price")
                        , result.getInt("price_int")
                        , result.getString("status")
                        , result.getDate("date"));
                productList.add(prodD);
            }

        } catch (Exception ignored) {

        }
        return productList;
    } // Get the data from SQL (Products)

    public void addProductShowListData() {
        addProductList = addProductsGetDataFromSQL();
        column_addProduct_id.setCellValueFactory(new PropertyValueFactory<>("productId"));
        column_addProduct_name.setCellValueFactory(new PropertyValueFactory<>("productName"));
        column_addProduct_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        column_addProduct_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        addProduct_table.setItems(addProductList);

    } // Put the data from SQL to Table (Products)

    public void addProductsSelect() {

        productData prodD = addProduct_table.getSelectionModel().getSelectedItem();
        int num = addProduct_table.getSelectionModel().getSelectedIndex();
        if ((num - 1) < -1) {
            return;
        }

        productId = prodD.getProductId();
        addProduct_name.setText(prodD.getProductName());
        addProduct_price.setText(String.valueOf(prodD.getPrice_int()));
        addProduct_status.setValue(prodD.getStatus());
    } // if you clicked data from table, it will fill the text-fields and combobox (Products)

    public void addProductListStatus() {
        addProduct_status.getItems().removeAll(addProduct_status.getItems());
        addProduct_status.getItems().addAll("Available", "Not Available");
        addProduct_status.getSelectionModel().select("Choose");
    } // Add the data to combobox (Status) (Products)




    public void ordersAdd() {
        int priceInt = 0;
        int qty = order_quantity.getValue();
        String sql = "INSERT INTO customer (customerName, type, productName, quantity, price, price_int, date)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        connect = database.connectDb();
        try {
            if (order_productType.getSelectionModel().getSelectedItem().equalsIgnoreCase("Pre-Made")) {
                if (customer_name.getText().isEmpty()
                        || order_productName.getSelectionModel().getSelectedItem() == null
                        || order_productType.getSelectionModel().getSelectedItem() == null
                        || order_quantity.getValue() == 0) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Fill out all the blank fields");
                    alert.showAndWait();
                } else {
                    // Put it in the same row if the product already exist
                    String checkName = "SELECT productName FROM customer WHERE productName = '" + order_productName.getSelectionModel().getSelectedItem() + "'";
                    assert connect != null;
                    statement = connect.createStatement();
                    result = statement.executeQuery(checkName);
                    if (result.next()) {
                        String totalQTY = String.valueOf(getQtyFromCustomer() + qty);
                        int totalPrice = getPriceFromCustomer() + (qty * getPriceFromProducts());
                        String update = "UPDATE customer SET quantity = '" + totalQTY + "', price = '" + "₱" + formatPrice(String.valueOf(totalPrice)) + "', price_int = '" + totalPrice +
                                "' WHERE customerName = '" + customer_name.getText() + "' AND productName = '" + order_productName.getSelectionModel().getSelectedItem() + "'";
                        statement = connect.createStatement();
                        statement.executeUpdate(update);
                        orderShowListData();
                        orderDisplayTotal();
                    } else {
                        // If product doesn't exist
                        assert connect != null;
                        prepare = connect.prepareStatement(sql);
                        prepare.setString(1, customer_name.getText());
                        prepare.setString(2, order_productType.getSelectionModel().getSelectedItem());
                        prepare.setString(3, order_productName.getSelectionModel().getSelectedItem());
                        prepare.setString(4, String.valueOf(qty));

                        String checkData = "SELECT price_int FROM products WHERE productName = '"
                                + order_productName.getSelectionModel().getSelectedItem() + "'";
                        statement = connect.createStatement();
                        result = statement.executeQuery(checkData);
                        while (result.next()) {
                            priceInt = result.getInt("price_int");
                        }
                        String priceString = String.valueOf(qty * priceInt);  // Compute the price
                        prepare.setString(5, "₱" + formatPrice(priceString));

                        int total = qty * priceInt;
                        prepare.setInt(6, total);

                        Date date = new Date();
                        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                        prepare.setDate(7, sqlDate);

                        prepare.executeUpdate();
                        orderDisplayTotal();
                        customer_name.setEditable(false);
                        customer_name.setDisable(true);
                        orderShowListData();
                    }

                }
            } else if (order_productType.getSelectionModel().getSelectedItem().equalsIgnoreCase("Customized")) {
                if (customer_name.getText().isEmpty()
                        || order_customName.getText().isEmpty()
                        || order_customPrice.getText().isEmpty()
                        || order_productType.getSelectionModel().getSelectedItem() == null
                        || order_quantity.getValue() == 0) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Fill out all the blank fields");
                    alert.showAndWait();
                } else {
                    // Check if product already exist
                    if (order_customName.getText().equalsIgnoreCase(getNameFromProductsToCompare())) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText(null);
                        alert.setContentText("Product " + order_customName.getText() + " is in the Pre-Made list");
                        alert.showAndWait();
                        order_customName.setText("");
                    } else {
                        // Put it in the same row if the product already exist
                        String checkName = "SELECT productName FROM customer WHERE productName = '" + order_customName.getText() + "'";
                        assert connect != null;
                        statement = connect.createStatement();
                        result = statement.executeQuery(checkName);
                        if (result.next()) {
                            String totalQTY = String.valueOf(getQtyFromCustomer() + qty);
                            int totalPrice = getPriceFromCustomer() + (qty * Integer.parseInt(order_customPrice.getText()));
                            String update = "UPDATE customer SET quantity = '" + totalQTY + "', price = '" + "₱" + formatPrice(String.valueOf(totalPrice)) + "', price_int = '" + totalPrice +
                                    "' WHERE customerName = '" + customer_name.getText() + "' AND productName = '" + order_customName.getText() + "'";
                            statement = connect.createStatement();
                            statement.executeUpdate(update);
                            orderShowListData();
                            orderDisplayTotal();
                        } else {
                            assert connect != null;
                            prepare = connect.prepareStatement(sql);

                            prepare.setString(1, customer_name.getText());
                            prepare.setString(2, order_productType.getSelectionModel().getSelectedItem());
                            prepare.setString(3, order_customName.getText());

                            prepare.setString(4, String.valueOf(qty));

                            priceInt = Integer.parseInt(order_customPrice.getText());

                            var priceString = String.valueOf(qty * priceInt);  // Compute the price
                            prepare.setString(5,"₱"+formatPrice(priceString));

                            int total = qty * priceInt;
                            prepare.setInt(6, total);

                            Date date = new Date();
                            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                            prepare.setDate(7, sqlDate);

                            prepare.executeUpdate();
                            orderDisplayTotal();
                            customer_name.setEditable(false);
                            customer_name.setDisable(true);
                            orderShowListData();
                        }
                    }
                }
            }
            ifTotalIsZero();

        } catch (Exception ignored) {

        }
    } // Add order
    public ObservableList<customerData> orderListData() {
        ObservableList<customerData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM customer";
        connect = database.connectDb();

        try {
            customerData customerID;
            assert connect != null;
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                customerID = new customerData
                        (result.getString("customerName")
                                , result.getString("type")
                                , result.getString("productName")
                                , result.getInt("quantity")
                                , result.getString("price")
                                , result.getInt("price_int")
                                , result.getDate("date"));
                listData.add(customerID);
            }
        } catch (Exception ignored) {

        }
        return listData;
    } // Get the data from SQL (Order)
    public void orderShowListData() {
        orderList = orderListData();
        com_order_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        com_order_name.setCellValueFactory(new PropertyValueFactory<>("productName"));
        com_order_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        com_order_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        order_table.setItems(orderList);
    } // Put the data from SQL to Table (Order)
    public void orderSet(ActionEvent e) {
        if (order_productType.getSelectionModel().getSelectedItem().equalsIgnoreCase("Pre-Made")) {
            order_customName.setVisible(false);
            order_customName.setText("");
            order_productName.setVisible(true);
            order_customPrice.setDisable(true);
            order_customPrice.setText("");
            custom_priceLabel.setDisable(true);
        } else if (order_productType.getSelectionModel().getSelectedItem().equalsIgnoreCase("Customized")) {
            order_productName.setVisible(false);
            order_productName.getSelectionModel().select("");
            order_customName.setVisible(true);
            order_customPrice.setDisable(false);
            custom_priceLabel.setDisable(false);

        }
    } // Set the visibility of text-field and combobox (Order)
    public String orderDisplayTotal() {
        String sql = "SELECT SUM(price_int) FROM customer WHERE customerName = '"
                + customer_name.getText() + "'";
        connect = database.connectDb();
        try {
            assert connect != null;
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            int total = 0;
            while (result.next()) {
                total = result.getInt("SUM(price_int)");
            }
            order_totalLabel.setText("₱" + formatPrice(String.valueOf(total)));

        } catch (Exception ignored) {

        }

        return order_totalLabel.getText();
    }  // Display the total price (Order)
    public void orderResetTable() {
        String sql = "DELETE FROM customer";
        connect = database.connectDb();
        Alert alert;
        try {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Sure?");
            alert.setHeaderText(null);
            alert.setContentText("This will remove all the data in the table. Are you sure?");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get().equals(ButtonType.OK)) {
                assert connect != null;
                statement = connect.createStatement();
                statement.executeUpdate(sql);
                orderClear();
                ifTotalIsZero();
                orderShowListData();
            } else {
                option.get();
            }


        } catch (Exception ignored) {

        }
    }  // Reset the table with yes or no (Order)
    public void orderResetTableWithoutAsking() {
        String sql = "DELETE FROM customer";
        connect = database.connectDb();
        try {
            assert connect != null;
            statement = connect.createStatement();
            statement.executeUpdate(sql);
            orderClear();
            orderShowListData();
        } catch (Exception ignored) {

        }
    }  // Reset the table without yes or no (Order)
    public void typeOfPurchased() {
        order_productType.getItems().removeAll(order_productType.getItems());
        order_productType.getItems().addAll("Pre-Made", "Customized");
        order_productType.getSelectionModel().select("Pre-Made");
    } // Add the data to combobox (Type of Purchased) (Order)
    public void orderPreMade() {
        String SQL = "SELECT productName FROM products WHERE status = 'Available'";
        connect = database.connectDb();

        try {
            assert connect != null;
            prepare = connect.prepareStatement(SQL);
            result = prepare.executeQuery();

            ObservableList<String> list = FXCollections.observableArrayList();

            while (result.next()) {
                list.add(result.getString("productName"));
            }
            order_productName.setItems(list);
        } catch (Exception ignored) {

        }
    } // Add the data to combobox (Pre-Made) (Order)
    public void orderSpinner() {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000, 1);
        order_quantity.setValueFactory(valueFactory);
    } // Set the value of spinner (Order)
    public void orderClear() {
        customer_name.setEditable(true);
        customer_name.setDisable(false);
        customer_name.setText("");
        order_productType.getSelectionModel().select("Pre-Made");
        order_productName.getSelectionModel().select("");
        order_customName.setText("");
        order_quantity.getValueFactory().setValue(1);
        order_customPrice.setText("");
        order_totalLabel.setText("₱0");
        order_amount.setText("");

        ifTotalIsZero();
    } // Reset all text-fields and combobox (Order)
    public void ifTotalIsZero(){
        if (order_totalLabel.getText().equalsIgnoreCase("₱0")){
            order_amount.setDisable(true);
            order_payBtn.setDisable(true);
        }else if (!order_totalLabel.getText().equalsIgnoreCase("₱0")){
            order_amount.setDisable(false);
            order_payBtn.setDisable(false);
        }
    } // If the total is zero, disable the pay button (Order)
    public void calculateBalanceAndChange(){
        int balance = 0;
        int change = 0;
        if (order_amount.getText().isEmpty()){
            order_balanceLabel.setText("₱0");
        }else{
            int total = Integer.parseInt(order_totalLabel.getText().substring(1).replaceAll(",", ""));
            int amount = Integer.parseInt(order_amount.getText());
            balance = total - amount;
            change = amount - total;
            if (balance < 0 ) { // If the balance is negative, it will display the change
                order_balanceLabel.setText("₱0");
            }else{
                order_balanceLabel.setText("₱" + formatPrice(String.valueOf(balance)));
            }
            if (change < 0){ // If the change is negative, it will display the balance
                order_change.setText("₱0");
            }else{
                order_change.setText("₱" + formatPrice(String.valueOf(change)));
            }

        }
    } // Calculate the balance (Order)
    public void orderPay(){
        String sql = "INSERT INTO customer_receipt (customer_name, total, paid, balance, total_int, balance_int,    date)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        connect = database.connectDb();
        Alert alert;
        try {
            if (order_amount.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Enter the amount");
                alert.showAndWait();
            } else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Sure?");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want this to process?");
                Optional<ButtonType> option = alert.showAndWait();
                if (option.get().equals(ButtonType.OK)) {

                    assert connect != null;
                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, customer_name.getText());
                    prepare.setString(2, order_totalLabel.getText());
                    prepare.setString(3, "₱" + formatPrice(order_amount.getText()));
                    prepare.setString(4, order_balanceLabel.getText());
                    prepare.setInt(5, Integer.parseInt(order_totalLabel.getText().substring(1).replaceAll(",", "")));
                    prepare.setInt(6, Integer.parseInt(order_balanceLabel.getText().substring(1).replaceAll(",", "")));

                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    prepare.setDate(7, sqlDate);

                    prepare.executeUpdate();
                    orderResetTableWithoutAsking();
                    orderClear();
                    orderShowListData();
                    orderDisplayTotal();

                }
            }


        } catch (Exception ignored) {

        }

    } // Pay the order (Order)


    public void makeTableNotReOrder() {
        addProduct_table.skinProperty().addListener((obs, oldSkin, newSkin) -> {
            final TableHeaderRow header = (TableHeaderRow) addProduct_table.lookup("TableHeaderRow");
            header.reorderingProperty().addListener((o, oldVal, newVal) -> header.setReordering(false));
        });
        order_table.skinProperty().addListener((obs, oldSkin, newSkin) -> {
            final TableHeaderRow header = (TableHeaderRow) order_table.lookup("TableHeaderRow");
            header.reorderingProperty().addListener((o, oldVal, newVal) -> header.setReordering(false));
        });
    } // Make the table not reorder able (Products) (Order)
    public int getQtyFromCustomer() throws SQLException {
        String getQty = "SELECT quantity FROM customer";
        statement = connect.createStatement();
        result = statement.executeQuery(getQty);
        int qty = 0;

        while (result.next()) {
            qty = result.getInt("quantity");
        }
        return qty;
    }
    public int getPriceFromCustomer() throws SQLException {
        String getPrice = "SELECT price_int FROM customer";
        statement = connect.createStatement();
        result = statement.executeQuery(getPrice);
        int price_int = 0;

        while (result.next()) {
            price_int = result.getInt("price_int");

        }
        return price_int;
    }
    public int getPriceFromProducts() throws SQLException {
        String getPrice = "SELECT price_int FROM products WHERE productName = '"
                + order_productName.getSelectionModel().getSelectedItem() + "'";
        statement = connect.createStatement();
        result = statement.executeQuery(getPrice);
        int price_int = 0;
        while (result.next()) {
            price_int = result.getInt("price_int");

        }
        return price_int;
    }
    public String getNameFromProductsToCompare() throws SQLException {
        String getName = "SELECT productName FROM products WHERE productName = '"
                + order_customName.getText() + "'";
        statement = connect.createStatement();
        result = statement.executeQuery(getName);
        String name = "";
        while (result.next()) {
            name = result.getString("productName");
        }
        return name;
    }
    public int checkIfCurrentTableEmpty() throws SQLException {
        String sql = "SELECT COUNT(*) FROM customer";
        connect = database.connectDb();
        assert connect != null;
        prepare = connect.prepareStatement(sql);
        result = prepare.executeQuery();
        int count = 0;
        while (result.next()) {
            count = result.getInt("COUNT(*)");
        }
        return count;


    } // Check if the table is empty (Home)
    public String formatPrice(String price) {
        long priceLong = Long.parseLong(price);
        return formatWithComma.format(priceLong);
    }
    public void onlyNumInTextField() {
        addProduct_price.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (!t1.matches("\\d*")) {
                    addProduct_price.setText(t1.replaceAll("[^\\d]", ""));
                }
            }
        });
        order_customPrice.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (!t1.matches("\\d*")) {
                    order_customPrice.setText(t1.replaceAll("[^\\d]", ""));
                }
            }
        });
        order_amount.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (!t1.matches("\\d*")) {
                    order_amount.setText(t1.replaceAll("[^\\d]", ""));
                }
            }
        });


    } // Only numbers allow in Price text-field
    public void clearTextField() {
        addProduct_name.setText("");
        addProduct_price.setText("");
        addProductListStatus();
    } // Clear all text-fields
    public void logout() throws SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        if (checkIfCurrentTableEmpty() != 0) {
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("This will logout and remove all the data in the table. Are you sure?");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get().equals(ButtonType.OK)) {
                orderResetTableWithoutAsking();
                AtomicReference<Double> x = new AtomicReference<>((double) 0);
                AtomicReference<Double> y = new AtomicReference<>((double) 0);
                try {
                    logout.getScene().getWindow().hide();
                    FXMLLoader fxmlLoader = new FXMLLoader(main.class.getResource("login.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(fxmlLoader.load(), 752, 551);
                    stage.initStyle(StageStyle.UNDECORATED);

                    scene.setOnMousePressed(mouseEvent -> {
                        x.set(mouseEvent.getSceneX());
                        y.set(mouseEvent.getSceneY());
                        scene.setCursor(Cursor.CLOSED_HAND);
                    });
                    scene.setOnMouseDragged(mouseEvent -> {
                        stage.setX(mouseEvent.getScreenX() - x.get());
                        stage.setY(mouseEvent.getScreenY() - y.get());
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
                } catch (Exception ignored) {

                }
            }
        } else if (checkIfCurrentTableEmpty() == 0) {
            AtomicReference<Double> x = new AtomicReference<>((double) 0);
            AtomicReference<Double> y = new AtomicReference<>((double) 0);
            try {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to logout?");
                Optional<ButtonType> option1 = alert.showAndWait();
                assert option1.orElse(null) != null;
                if (option1.orElse(null).equals(ButtonType.OK)) {
                    logout.getScene().getWindow().hide();
                    FXMLLoader fxmlLoader = new FXMLLoader(main.class.getResource("login.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(fxmlLoader.load(), 752, 551);
                    stage.initStyle(StageStyle.UNDECORATED);

                    scene.setOnMousePressed(mouseEvent -> {
                        x.set(mouseEvent.getSceneX());
                        y.set(mouseEvent.getSceneY());
                        scene.setCursor(Cursor.CLOSED_HAND);
                    });
                    scene.setOnMouseDragged(mouseEvent -> {
                        stage.setX(mouseEvent.getScreenX() - x.get());
                        stage.setY(mouseEvent.getScreenY() - y.get());
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
                } else {
                    return;
                }

            } catch (Exception ignored) {

            }
        }


    } // Logout
    public void minimize() {
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    } // Minimize
    public void switchForm(MouseEvent event) {
        if (event.getSource() == home_btn || event.getSource() == home) {
            home_form.setVisible(true);
            orders_form.setVisible(false);
            addProducts_form.setVisible(false);
            home.setStyle("-fx-background-color: linear-gradient(to bottom right, #8cea50, #3ce03c)");
            orders.setStyle("-fx-background-color: transparent");
            addProducts.setStyle("-fx-background-color: transparent");

        } else if (event.getSource() == addProducts_btn || event.getSource() == addProducts) {
            home_form.setVisible(false);
            orders_form.setVisible(false);
            addProducts_form.setVisible(true);
            home.setStyle("-fx-background-color: transparent");
            orders.setStyle("-fx-background-color: transparent");
            addProducts.setStyle("-fx-background-color: linear-gradient(to bottom right, #8cea50, #3ce03c)");

            addProductShowListData();
            addProductListStatus();
            addProductsSearch();
        } else if (event.getSource() == orders_btn || event.getSource() == orders) {
            home_form.setVisible(false);
            orders_form.setVisible(true);
            addProducts_form.setVisible(false);
            home.setStyle("-fx-background-color: transparent");
            orders.setStyle("-fx-background-color: linear-gradient(to bottom right, #8cea50, #3ce03c)");
            addProducts.setStyle("-fx-background-color: transparent");
            orderShowListData(); // Put the data from SQL to Table (Order)
            typeOfPurchased(); // Add the data to combobox (Type of Purchased)
            orderPreMade(); // Add the data to combobox (Pre-Made)
            orderSpinner(); // Set the value of spinner (Order)
            orderDisplayTotal(); // Display the total price (Order)
        }

    }  // Switch between forms
    public void close() throws SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        if (checkIfCurrentTableEmpty() != 0) {
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("You still have the data in the table. Are you sure you want to exit?");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get().equals(ButtonType.OK)) {
                orderResetTableWithoutAsking();
                System.exit(0);
            }
        } else if (checkIfCurrentTableEmpty() == 0) {
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to exit?");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get().equals(ButtonType.OK)) {
                System.exit(0);
            }
        }

    } // Close


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addProductShowListData(); // Put the data from SQL to Table
        addProductListStatus(); // Add the data to combobox (Status)
        typeOfPurchased(); // Add the data to combobox (Type of Purchased)
        orderPreMade(); // Add the data to combobox (Pre-Made)
        orderSpinner(); // Set the value of spinner (Order)
        orderShowListData(); // Put the data from SQL to Table (Order)
        orderDisplayTotal(); // Display the total price (Order)
        makeTableNotReOrder(); // Make the table not reorder able (Products) (Order)
        onlyNumInTextField(); // Only numbers allow in Price text-field
        ifTotalIsZero(); // If total is zero, disable the pay button and amount text-field
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                orderResetTableWithoutAsking();

            }
        });


    } // Initialize
}
