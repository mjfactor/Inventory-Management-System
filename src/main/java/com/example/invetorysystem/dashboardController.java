package com.example.invetorysystem;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.InputStream;
import java.net.URL;
import java.sql.*;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.*;
import java.util.Date;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.skin.TableHeaderRow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.util.converter.IntegerStringConverter;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.*;


public class dashboardController implements Initializable {
    @FXML
    private Button addProduct_add;
    @FXML
    private Button addProduct_button;

    @FXML
    private Button addProduct_delete;

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
    private TableColumn<productData, Integer> column_addProduct_quantity;
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
    private TableColumn<historyData, String> history_column_costumerName;
    @FXML
    private TableColumn<historyData, String> history_column_total;
    @FXML
    private TableColumn<historyData, String> history_column_paid;
    @FXML
    private TableColumn<historyData, String> history_column_change;
    @FXML
    private TableColumn<historyData, Date> history_column_date;
    @FXML
    private TableColumn<historyData, Integer> history_column_transactionNumber;
    @FXML
    private TableView<historyData> history_fullyPaidTable;
    @FXML
    private TableView<historyData> history_withBalanceTable;
    @FXML
    private TableColumn<historyData, Integer> history_withBalance_column_transactionNumber;
    @FXML
    private TableColumn<historyData, String> history_withBalance_column_balance;

    @FXML
    private TableColumn<historyData, String> history_withBalance_column_costumerName;

    @FXML
    private TableColumn<historyData, Date> history_withBalance_column_date;

    @FXML
    private TableColumn<historyData, String> history_withBalance_column_paid;

    @FXML
    private TableColumn<historyData, String> history_withBalance_column_total;

    @FXML
    private AnchorPane home;
    @FXML
    private Label home_availableProducts;

    @FXML
    private Button home_btn;
    @FXML
    private Button history_btn;

    @FXML
    private AnchorPane home_form;
    @FXML
    private AnchorPane history;


    @FXML
    private Label home_numberofOrders;



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
    private Button order_payBtn;

    @FXML
    private ComboBox<String> order_productName;

    @FXML
    private ComboBox<String> order_productType;

    @FXML
    private Spinner<Integer> order_quantity;
    @FXML
    private Spinner<Integer> addProduct_quantity;
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
    private AnchorPane history_form;
    @FXML
    private TextField order_customName;
    @FXML
    private TextField order_customPrice;
    @FXML
    private Button order_addBtn;
    @FXML
    private CheckBox history_fullyPaid;
    @FXML
    private CheckBox history_notFullyPaid;
    @FXML
    private ComboBox<String> history_month;
    @FXML
    private ComboBox<String> history_year;
    @FXML
    private AnchorPane history_haveBalancePayAnchorPane;
    @FXML
    private Button history_print;
    @FXML
    private AnchorPane history_filterAnchorPane;
    @FXML
    private Label history_totalIncome;
    @FXML
    private Label history_balanceLabel;
    @FXML
    private Label history_totalLabel;
    @FXML
    private Label history_changeLabel;
    @FXML
    private Label history_willBeBalance;
    @FXML
    private TextField history_amount;
    @FXML
    private Button history_balancePay;
    @FXML
    private Label history_amountLabel;
    @FXML
    private Button history_deleteRow;
    @FXML
    private Button history_deleteTable;
    @FXML
    private Label history_totalIncomeLabel;
    @FXML
    private TextField transaction_Id;
    @FXML
    private LineChart<?, ?> home_incomedataChart;
    @FXML
    private LineChart <?, ?> home_lineChart;
    @FXML
    private ComboBox <String> dataChart_filter;
    @FXML
    private Label history_transactionId;


    NumberFormat formatWithComma = NumberFormat.getNumberInstance(Locale.US);
    public int productId;
    public String transactionId;
    public int balanceInt;
    public int paidInt;

    private ObservableList<productData> addProductList;

    Connection connect;
    PreparedStatement prepare;
    ResultSet result;
    Statement statement;







    public void homeNumberOfOrder(){
        String sql = "SELECT COUNT(*) FROM customer_receipt";
        connect = database.connectDb();
        try {
            assert connect != null;
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()){
                home_numberofOrders.setText(result.getString("COUNT(*)"));
            }
        }catch (Exception ignored){

        }
    } // Get the number of orders (Home)
    public void homeTotalIncome(){
        String sql = "SELECT SUM(total_int) FROM customer_receipt";
        connect = database.connectDb();
        try {
            assert connect != null;
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()){
                home_totalIncome.setText("₱" + formatPrice(String.valueOf(result.getInt("SUM(total_int)"))));
            }
        }catch (Exception ignored){

        }
    } // Get the total income (Home)
    public void homeAvailableProducts(){
        String sql = "SELECT COUNT(*) FROM products WHERE status = 'Available'";
        connect = database.connectDb();
        try {
            assert connect != null;
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()){
                home_availableProducts.setText(result.getString("COUNT(*)"));
            }
        }catch (Exception ignored){

        }
    } // Get the number of available products (Home)
    public void addDataToDataChartFilter(){
        dataChart_filter.getItems().removeAll(dataChart_filter.getItems());
        // Display Only 3 years, increment when year increase
        dataChart_filter.getItems().addAll(String.valueOf(Year.now().getValue() - 2), String.valueOf(Year.now().getValue() - 1), String.valueOf(Year.now().getValue()));
        dataChart_filter.getSelectionModel().select(String.valueOf(Year.now().getValue()));
    } // Add the data to combobox (Data Chart Filter) (Home)

    public void homeIncomeDataChart(){
        // Compare sales from month to month based on the combobox year
        XYChart.Series series = new XYChart.Series();
        series.setName("Income");
        series.getData().add(new XYChart.Data("Jan", getIncomeFromMonth("JANUARY")));
        series.getData().add(new XYChart.Data("Feb", getIncomeFromMonth("FEBRUARY")));
        series.getData().add(new XYChart.Data("Mar", getIncomeFromMonth("MARCH")));
        series.getData().add(new XYChart.Data("Apr", getIncomeFromMonth("APRIL")));
        series.getData().add(new XYChart.Data("May", getIncomeFromMonth("MAY")));
        series.getData().add(new XYChart.Data("Jun", getIncomeFromMonth("JUNE")));
        series.getData().add(new XYChart.Data("Jul", getIncomeFromMonth("JULY")));
        series.getData().add(new XYChart.Data("Aug", getIncomeFromMonth("AUGUST")));
        series.getData().add(new XYChart.Data("Sep", getIncomeFromMonth("SEPTEMBER")));
        series.getData().add(new XYChart.Data("Oct", getIncomeFromMonth("OCTOBER")));
        series.getData().add(new XYChart.Data("Nov", getIncomeFromMonth("NOVEMBER")));
        series.getData().add(new XYChart.Data("Dec", getIncomeFromMonth("DECEMBER")));
        home_incomedataChart.getData().addAll(series);
        // Remove Previous Line
        if (home_incomedataChart.getData().size() > 1){
            home_incomedataChart.getData().remove(0);
        }


    } // Display the income data chart (Home)
    public int getIncomeFromMonth(String month){
        // Get the income from month when year is equal to the combobox year
        String sql = "SELECT SUM(total_int) FROM customer_receipt WHERE month_string = '"
                + month + "' AND year_int = '" + dataChart_filter.getSelectionModel().getSelectedItem() + "'";
        connect = database.connectDb();
        int total = 0;
        try {

            assert connect != null;
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()){
                total = result.getInt("SUM(total_int)");
            }
        }catch (Exception ignored){

        }
        return total;
    } // Get the income from month to month (Home)
    public void homeIncomeDataChartForYear(){
        // Compare sales from year to year
        // Auto Increment the year
        XYChart.Series series = new XYChart.Series();
        series.setName("Income");
        // Display Only 3 years, increment when year increase
        series.getData().add(new XYChart.Data(String.valueOf(Year.now().getValue() - 2), getIncomeFromYear(String.valueOf(Year.now().getValue() - 2))));
        series.getData().add(new XYChart.Data(String.valueOf(Year.now().getValue() - 1), getIncomeFromYear(String.valueOf(Year.now().getValue() - 1))));
        series.getData().add(new XYChart.Data(String.valueOf(Year.now().getValue()), getIncomeFromYear(String.valueOf(Year.now().getValue()))));

        home_lineChart.getData().addAll(series);
        // Remove Previous Line
        if (home_lineChart.getData().size() > 1){
            home_lineChart.getData().remove(0);
        }
    } // Display the income data chart for year (Home)
    public int getIncomeFromYear(String year){
        // Get the income from year to year
        String sql = "SELECT SUM(total_int) FROM customer_receipt WHERE date LIKE '%"
                + year + "%'";
        connect = database.connectDb();
        int total = 0;
        try {
            assert connect != null;
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()){
                total = result.getInt("SUM(total_int)");
            }
        }catch (Exception ignored){

        }
        return total;
    } // Get the income from year to year (Home)



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

        String sql = "INSERT INTO products (productName, price, quantity, price_int, status, date)"
                + "VALUES (?, ?, ?, ?, ?, ?)";
        connect = database.connectDb();
        Alert alert;
        try {
            if (addProduct_name.getText().isEmpty()
                    || addProduct_price.getText().isEmpty()
                    || addProduct_price.getText().startsWith("0")
                    || addProduct_quantity.getValue() == null
                    || Objects.equals(addProduct_status.getSelectionModel().getSelectedItem(), "Choose")) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Fill out all the blank fields");
                alert.showAndWait();
            } else {
                // Check if product already exist

                String checkName = "SELECT * FROM products WHERE productName LIKE '" + addProduct_name.getText() + "%" + "'";
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
                    prepare.setInt(3, addProduct_quantity.getValue());
                    prepare.setInt(4, Integer.parseInt(addProduct_price.getText()));
                    prepare.setString(5, addProduct_status.getSelectionModel().getSelectedItem());

                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    prepare.setDate(6, sqlDate);

                    prepare.executeUpdate();
                    addProductShowListData();
                    clearTextField();
                }

            }
        } catch (Exception ignored) {
        }

    }  // Add product
    public void addProductUpdateMultipleRows() throws SQLException {
        // Check if any row is selected
        if (addProduct_table.getSelectionModel().getSelectedItems().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No rows selected");
            alert.showAndWait();
            return;
        }

        // Check if status and quantity are provided
        if (addProduct_status.getSelectionModel().getSelectedItem() == null
                || Objects.equals(addProduct_status.getSelectionModel().getSelectedItem(), "Choose")
                || addProduct_quantity.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please provide status and quantity");
            alert.showAndWait();
            return;
        }

        // Confirmation alert
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation");
        confirmationAlert.setHeaderText(null);
        confirmationAlert.setContentText("Are you sure you want to update?");

        Optional<ButtonType> result = confirmationAlert.showAndWait();
        if (result.get() != ButtonType.OK){
            return; // User chose not to proceed
        }

        // Get the new status and quantity
        String newStatus = addProduct_status.getSelectionModel().getSelectedItem();
        int newQuantity = addProduct_quantity.getValue();

        // Update each selected row
        for (productData product : addProduct_table.getSelectionModel().getSelectedItems()) {
            String sql = "UPDATE products SET status = ?, quantity = ? WHERE id = ?";

            try {
                assert connect != null;
                PreparedStatement prepare = connect.prepareStatement(sql);
                prepare.setString(1, newStatus);
                prepare.setInt(2, newQuantity);
                prepare.setInt(3, product.getProductId());
                prepare.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Show success message
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Successfully Updated");
        alert.showAndWait();

        // Refresh the table view to reflect the changes
        updateStatusFromProducts(); // Update the status from products
        addProductShowListData();
        clearTextField(); // Clear the text-fields
    } // Update multiple rows (Products)
    public void addProductUpdate() {
        if (addProduct_table.getSelectionModel().getSelectedItems().size() > 1) {
            try {
                addProductUpdateMultipleRows();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return;
        }
        String sql = "UPDATE products SET productName = '" + addProduct_name.getText() + "', price = '" + "₱" + formatPrice(addProduct_price.getText()) + "', price_int = '" + Integer.parseInt(addProduct_price.getText()) + "'" +
                ", quantity = '" + addProduct_quantity.getValue() + "'" +
                ",  status = '" + addProduct_status.getSelectionModel().getSelectedItem() + "' WHERE id = '" + productId + "'";

        connect = database.connectDb();
        Alert alert;

        try {
            if (addProduct_name.getText().isEmpty()
                    || addProduct_price.getText().isEmpty()
                    || addProduct_status.getSelectionModel().getSelectedItem() == null
                    || addProduct_table.getSelectionModel().getSelectedItem() == null
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
                    updateStatusFromProducts();
                    addProductShowListData();
                    clearTextField();
                } else {
                    addProduct_table.getSelectionModel().clearSelection();
                }
            }

        } catch (Exception NumberFormatException) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Enter a valid price");
        }
    } // Update product
    public void addProductDeleteMultipleRows(){
        // Check if any row is selected
        if (addProduct_table.getSelectionModel().getSelectedItems().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No rows selected");
            alert.showAndWait();
            return;
        }

        // Confirmation alert
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation");
        confirmationAlert.setHeaderText(null);
        confirmationAlert.setContentText("Are you sure you want to delete the selected rows?");

        Optional<ButtonType> result = confirmationAlert.showAndWait();
        if (result.get() != ButtonType.OK){
            return; // User chose not to proceed
        }

        // Delete each selected row
        for (productData product : addProduct_table.getSelectionModel().getSelectedItems()) {
            String sql = "DELETE from products WHERE id = ?";

            try {
                assert connect != null;
                PreparedStatement prepare = connect.prepareStatement(sql);
                prepare.setInt(1, product.getProductId());
                prepare.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Show success message
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Successfully Deleted");
        alert.showAndWait();

        // Refresh the table view to reflect the changes
        addProductShowListData();
        clearTextField(); // Clear the text-fields
    }
    public void addProductDelete() {
        if (addProduct_table.getSelectionModel().getSelectedItems().size() > 1) {
            addProductDeleteMultipleRows();
            return;
        }

        String sql = "DELETE from products WHERE id = '" + productId + "'";
        connect = database.connectDb();
        Alert alert;
        try {
            if (addProduct_name.getText().isEmpty()
                    || addProduct_price.getText().isEmpty() || addProduct_table.getSelectionModel().getSelectedItem() == null) {
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
                } else {
                    addProduct_table.getSelectionModel().clearSelection();
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
                        , result.getInt("quantity")
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
        column_addProduct_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
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
        addProduct_quantity.getValueFactory().setValue(prodD.getQuantity());
    } // if you clicked data from table, it will fill the text-fields and combobox (Products)

    public void addProductListStatus() {
        addProduct_status.getItems().removeAll(addProduct_status.getItems());
        addProduct_status.getItems().addAll("Available", "Not Available");
        addProduct_status.getSelectionModel().select("Choose");
    } // Add the data to combobox (Status) (Products)

    public void addProductStatusAffectQuantity(ActionEvent e) {
        if (addProduct_status.getSelectionModel().getSelectedItem().equalsIgnoreCase("Not Available")) {
            addProduct_quantity.getValueFactory().setValue(0);
            addProduct_quantity.setDisable(true);
        } else if (addProduct_status.getSelectionModel().getSelectedItem().equalsIgnoreCase("Available")) {
            addProduct_quantity.getValueFactory().setValue(1);
            addProduct_quantity.setDisable(false);
        }
    } // If the status is not available, the quantity will be zero (Products)
    public void productsIfMultipleSelected(){
        addProduct_table.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<productData>() {
            @Override
            public void onChanged(Change<? extends productData> change) {
                // Check if multiple rows are selected
                if (addProduct_table.getSelectionModel().getSelectedItems().size() > 1) {
                    // Disable the TextField and Button
                    addProduct_name.setDisable(true);
                    addProduct_price.setDisable(true);
                    addProduct_add.setDisable(true);

                    // Make the TextField values empty
                    addProduct_name.setText("");
                    addProduct_price.setText("");
                } else {
                    // Enable the TextField and Button
                    addProduct_name.setDisable(false);
                    addProduct_price.setDisable(false);
                    addProduct_add.setDisable(false);
                }
            }
        });
    }
    public void disableUpdateIfRowIsNotSelected() {
        addProduct_table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection == null) {
                addProduct_update.setDisable(true);
                addProduct_delete.setDisable(true);
            } else {
                addProduct_update.setDisable(false);
                addProduct_delete.setDisable(false);
            }
        });

    } // Disable the update and delete button if the row is not selected (Products)
    public void addProductSpinner() {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 1);
        addProduct_quantity.setValueFactory(valueFactory);
        addProduct_quantity.setEditable(true);

        // Add a TextFormatter to restrict input
        TextFormatter<Integer> formatter = new TextFormatter<>(new IntegerStringConverter(), 1, change -> {
            try {
                String newText = change.getControlNewText();
                if (newText.isEmpty()) {
                    return change;
                } else if (newText.startsWith("0")) {
                    return null;
                }
                int newValue = Integer.parseInt(newText);
                return newValue <= 1000 ? change : null;
            } catch (NumberFormatException e) {
                return null;
            }
        });

        addProduct_quantity.getEditor().setTextFormatter(formatter);
    }




    final Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1),
            new EventHandler()
            {
                @Override
                public void handle(Event event) {
                    Date date = new Date();
                    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                    transaction_Id.setText(formatter.format(date).replaceAll("/", "").replaceAll(":", "").replaceAll(" ", ""));
                }

            }));  // Get the current date and time


    String transaction = "";
    public void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    public void ordersAdd() throws SQLException {
        if (Objects.equals(transaction, "")){
            transaction = transaction_Id.getText();
        }
        int priceInt = 0;
        int qty = order_quantity.getValue();
        String sql = "INSERT INTO customer (transaction_id, customerName, type, productName, quantity, price, price_int, date)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

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
                } else if (qty > getQtyFromProducts()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Only " + getQtyFromProducts() + " left from " + order_productName.getSelectionModel().getSelectedItem());
                    alert.showAndWait();

                } else {

                    // Put it in the same row if the product already exist
                    String checkName = "SELECT productName FROM customer WHERE productName = '" + order_productName.getSelectionModel().getSelectedItem() + "'";
                    assert connect != null;
                    statement = connect.createStatement();
                    result = statement.executeQuery(checkName);
                    if (result.next()) {
                        int TotalQTY = getQtyFromCustomer() + qty;
                        String totalQTY = String.valueOf(TotalQTY);
                        int left = getQtyFromProducts() - getQtyFromCustomer();
                        if (TotalQTY > getQtyFromProducts()) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error");
                            alert.setHeaderText(null);
                            alert.setContentText("Only " + left + " left from " + order_productName.getSelectionModel().getSelectedItem());
                            alert.showAndWait();
                        } else {
                            int totalPrice = getPriceFromCustomer() + (qty * getPriceFromProducts());
                            String update = "UPDATE customer SET quantity = '" + totalQTY + "', price = '" + "₱" + formatPrice(String.valueOf(totalPrice)) + "', price_int = '" + totalPrice +
                                    "' WHERE customerName = '" + customer_name.getText() + "' AND productName = '" + order_productName.getSelectionModel().getSelectedItem() + "'";
                            statement = connect.createStatement();
                            statement.executeUpdate(update);
                            orderShowListData();
                            orderDisplayTotal();
                        }


                    } else {
                        // If product doesn't exist
                        assert connect != null;
                        prepare = connect.prepareStatement(sql);
                        prepare.setString(1, transaction);
                        prepare.setString(2, customer_name.getText());
                        prepare.setString(3, order_productType.getSelectionModel().getSelectedItem());
                        prepare.setString(4, order_productName.getSelectionModel().getSelectedItem());
                        prepare.setString(5, String.valueOf(qty));

                        String checkData = "SELECT price_int FROM products WHERE productName = '"
                                + order_productName.getSelectionModel().getSelectedItem() + "'";
                        statement = connect.createStatement();
                        result = statement.executeQuery(checkData);
                        while (result.next()) {
                            priceInt = result.getInt("price_int");
                        }
                        String priceString = String.valueOf(qty * priceInt);  // Compute the price
                        prepare.setString(6, "₱" + formatPrice(priceString));

                        int total = qty * priceInt;
                        prepare.setInt(7, total);

                        Date date = new Date();
                        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                        prepare.setDate(8, sqlDate);

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
                        || order_customPrice.getText().startsWith("0")
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
                            String totalQTY = String.valueOf(getQtyFromCustomer2() + qty);
                            int totalPrice = getPriceFromCustomer() + (qty * Integer.parseInt(order_customPrice.getText()));
                            String update = "UPDATE customer SET quantity = '" + totalQTY + "', price = '" + "₱" + formatPrice(String.valueOf(totalPrice)) + "', price_int = '" + totalPrice +
                                    "' WHERE customerName = '" + customer_name.getText() + "' AND productName = '" + order_customName.getText() + "'";
                            statement = connect.createStatement();
                            statement.executeUpdate(update);
                            orderShowListData();
                            orderDisplayTotal();
                            System.out.println(getQtyFromCustomer());
                        } else {
                            assert connect != null;
                            prepare = connect.prepareStatement(sql);
                            prepare.setString(1, transaction);
                            prepare.setString(2, customer_name.getText());
                            prepare.setString(3, order_productType.getSelectionModel().getSelectedItem());
                            prepare.setString(4, order_customName.getText());

                            prepare.setString(5, String.valueOf(qty));

                            priceInt = Integer.parseInt(order_customPrice.getText());

                            var priceString = String.valueOf(qty * priceInt);  // Compute the price
                            prepare.setString(6, "₱" + formatPrice(priceString));

                            int total = qty * priceInt;
                            prepare.setInt(7, total);

                            Date date = new Date();
                            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                            prepare.setDate(8, sqlDate);

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

        } finally {

            if (connect != null) {
                try {
                    connect.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
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
                        (
                                result.getString("customerName")
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
        ObservableList<customerData> orderList = orderListData();
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
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 1);
        order_quantity.setValueFactory(valueFactory);
        order_quantity.setEditable(true);

        // Add a TextFormatter to restrict input
        TextFormatter<Integer> formatter = new TextFormatter<>(new IntegerStringConverter(), 1, change -> {
            try {
                String newText = change.getControlNewText();
                if (newText.isEmpty()) {
                    return change;
                } else if (newText.startsWith("0")) {

                    return null;
                }
                int newValue = Integer.parseInt(newText);
                return newValue <= 1000 ? change : null;
            } catch (NumberFormatException e) {
                return null;
            }
        });

        order_quantity.getEditor().setTextFormatter(formatter);
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

    public void ifTotalIsZero() {
        if (order_totalLabel.getText().equalsIgnoreCase("₱0")) {
            order_amount.setDisable(true);
            order_payBtn.setDisable(true);
        } else if (!order_totalLabel.getText().equalsIgnoreCase("₱0")) {
            order_amount.setDisable(false);
            order_payBtn.setDisable(false);
        }
    } // If the total is zero, disable the pay button (Order)

    public void calculateBalanceAndChange() {
        int balance = 0;
        int change = 0;
        if (order_amount.getText().isEmpty()) {
            order_balanceLabel.setText("₱0");
        } else {
            int total = Integer.parseInt(order_totalLabel.getText().substring(1).replaceAll(",", ""));
            int amount = Integer.parseInt(order_amount.getText());
            balance = total - amount;
            change = amount - total;
            if (balance < 0) { // If the balance is negative, it will display the change
                order_balanceLabel.setText("₱0");
            } else {
                order_balanceLabel.setText("₱" + formatPrice(String.valueOf(balance)));
            }
            if (change < 0) { // If the change is negative, it will display the balance
                order_change.setText("₱0");
            } else {
                order_change.setText("₱" + formatPrice(String.valueOf(change)));
            }

        }
    } // Calculate the balance (Order)
    public void insertCustomerTableToHistoryPayBalanceTable(){
        // Copy all the columns from customer table to history balance table
        String sql = "INSERT INTO historybalancepay (transaction_id, customerName, type, productName, quantity, price, price_int, date)"
                + "SELECT transaction_id, customerName, type, productName, quantity, price, price_int, date FROM customer";
        connect = database.connectDb();
        try {
            assert connect != null;
            statement = connect.createStatement();
            statement.executeUpdate(sql);
        }catch (Exception ignored){

        }

    } // Insert the data from customer table to history balance table (Order)
    public void orderPay() {
        String sql = "INSERT INTO customer_receipt (transaction_id, customer_name, total, paid, change_string, balance, paid_int, total_int, balance_int, change_int, date, year_int, month_string)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        connect = database.connectDb();
        Alert alert;
        try {
            if (order_amount.getText().isEmpty()|| order_amount.getText().startsWith("0")) {
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
                    prepare.setString(1, transaction);
                    prepare.setString(2, customer_name.getText());
                    prepare.setString(3, order_totalLabel.getText());
                    prepare.setString(4, "₱" + formatPrice(order_amount.getText()));
                    prepare.setString(5, order_change.getText());
                    prepare.setString(6, order_balanceLabel.getText());
                    prepare.setInt(7, Integer.parseInt(order_amount.getText()));
                    prepare.setInt(8, Integer.parseInt(order_totalLabel.getText().substring(1).replaceAll(",", "")));
                    prepare.setInt(9, Integer.parseInt(order_balanceLabel.getText().substring(1).replaceAll(",", "")));
                    prepare.setInt(10, Integer.parseInt(order_change.getText().substring(1).replaceAll(",", "")));
                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    prepare.setDate(11, sqlDate);
                    prepare.setInt(12, LocalDate.now().getYear());
                    prepare.setString(13, String.valueOf(LocalDate.now().getMonth()));
                    prepare.executeUpdate();
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Paid");
                    alert.showAndWait();
                    insertCustomerTableToHistoryPayBalanceTable();
                    orderReceipt();
                    updateQtyFromProducts();
                    updateStatusFromProducts();
                    orderResetTableWithoutAsking();
                    orderClear();
                    addProductShowListData();
                    addProductListStatus();
                    orderShowListData();
                    orderDisplayTotal();
                    transaction = "";
                }
            }


        } catch (Exception ignored) {

        }

    } // Pay the order (Order)
    public void orderReceipt() throws JRException {
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("transaction", transaction);
        parameters.put("total", order_totalLabel.getText());
        parameters.put("paid", "₱" + formatPrice(order_amount.getText()));
        parameters.put("change", order_change.getText());
        parameters.put("balance", order_balanceLabel.getText());
        InputStream inputStream = getClass().getResourceAsStream("/com/example/invetorysystem/receipt.jrxml");
        JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, database.connectDb());
        JasperViewer.viewReport(jasperPrint, false);
    } // Print the receipt (Order)



    public void historyFullyPaid(ActionEvent e) {
        if (history_fullyPaid.isSelected()) {
            history_notFullyPaid.setSelected(false);
            history_fullyPaidTable.setVisible(true);
            history_withBalanceTable.setVisible(false);

            history_haveBalancePayAnchorPane.setVisible(false);
            history_print.setVisible(true);
            history_fullyPaidTable.getSelectionModel().clearSelection();
            transactionId = "0";
            history_withBalanceTable.getSelectionModel().clearSelection();
            history_filterAnchorPane.setVisible(true);
            history_fullyPaid.setDisable(true);

            history_notFullyPaid.setDisable(false); // Must be in the last line
        }

    }
    public void historyNotFullyPaid(ActionEvent e) {
        if (history_notFullyPaid.isSelected()) {
            history_transactionId.setText("");
            history_fullyPaid.setSelected(false);
            history_withBalanceTable.setVisible(true);


            history_fullyPaidTable.setVisible(false);
            history_fullyPaidTable.getSelectionModel().clearSelection();
            history_withBalanceTable.getSelectionModel().clearSelection();
            transactionId = "0";
            history_haveBalancePayAnchorPane.setVisible(true);
            history_print.setVisible(true);
            history_filterAnchorPane.setVisible(false);
            history_notFullyPaid.setDisable(true);
            history_fullyPaid.setDisable(false); // Must be in the last line

        }
    }
    public ObservableList<historyData> historyWithBalanceList() {
        ObservableList<historyData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM customer_receipt WHERE balance_int != 0";
        connect = database.connectDb();
        try {
            historyData historyID;
            assert connect != null;
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                historyID = new historyData
                        (result.getString("transaction_id"),
                                result.getString("customer_name")
                                , result.getString("total")
                                , result.getString("paid")
                                , result.getString("change_string")
                                , result.getString("balance")
                                , result.getInt("total_int")
                                , result.getInt("balance_int")
                                , result.getInt("change_int")
                                , result.getDate("date")
                                , result.getInt("year_int")
                                , result.getString("month_string"));

                listData.add(historyID);
            }
        } catch (Exception ignored) {

        }
        return listData;
    } // Get the data from SQL (History)
    public void historyShowWithBalanceData() {
        ObservableList<historyData> historyWithBalanceList = historyWithBalanceList();
        history_withBalance_column_transactionNumber.setCellValueFactory(new PropertyValueFactory<>("transaction_id"));
        history_withBalance_column_costumerName.setCellValueFactory(new PropertyValueFactory<>("customer_name"));
        history_withBalance_column_total.setCellValueFactory(new PropertyValueFactory<>("total"));
        history_withBalance_column_paid.setCellValueFactory(new PropertyValueFactory<>("paid"));
        history_withBalance_column_balance.setCellValueFactory(new PropertyValueFactory<>("balance"));
        history_withBalance_column_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        history_withBalanceTable.setItems(historyWithBalanceList);
    } // Put the data from SQL with balance to Table (History)
    public void historyAddMonth() {
        history_month.getItems().removeAll(history_month.getItems());
        history_month.getItems().addAll("All", "JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE"
                , "JULY", "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER");
        Month month = LocalDate.now().getMonth();
        history_month.getSelectionModel().select(month.toString());
    } // Add the data to combobox (Month) (History)
    public void historyAddYear() {
        int pastYears = 1; // Number of previous years to include
        int futureYears = 9; // Number of future years to include
        int currentYear = Year.now().getValue();
        history_year.getItems().setAll(
                IntStream.rangeClosed(currentYear - pastYears, currentYear + futureYears)
                        .mapToObj(Integer::toString)
                        .collect(Collectors.toList())
        );

        history_year.getSelectionModel().select(Integer.toString(currentYear));

        // Check if the current year has changed
        int newCurrentYear = Year.now().getValue();
        if (newCurrentYear != currentYear) {
            // Update the current year
            currentYear = newCurrentYear;
            // Increment future and past years
            pastYears++;
            futureYears++;
            // Update the list of years
            history_year.getItems().setAll(
                    IntStream.rangeClosed(currentYear - pastYears, currentYear + futureYears)
                            .mapToObj(Integer::toString)
                            .collect(Collectors.toList())
            );
            // Select the new current year
            history_year.getSelectionModel().select(Integer.toString(currentYear));
        }
    } // Add the data to combobox (Year) (History)
    public void disableYearIfMonthIsAll(){
        history_month.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null && newSelection.equals("All")) {
                history_year.setDisable(true);
            } else {
                history_year.setDisable(false);
            }
        });
    }
    public void historyDisplayTableFromMonths() {
        ObservableList<historyData> monthList = FXCollections.observableArrayList();

        if (history_month.getSelectionModel().getSelectedItem().equalsIgnoreCase("January")) {
            String sql = "SELECT * FROM customer_receipt WHERE MONTH(date) = 1 AND balance_int = 0 AND YEAR(date) = '" + history_year.getSelectionModel().getSelectedItem() + "' AND YEAR(date) = '" + history_year.getSelectionModel().getSelectedItem() + "'";
            connect = database.connectDb();
            try {
                historyData historyID;
                assert connect != null;
                prepare = connect.prepareStatement(sql);
                result = prepare.executeQuery();

                while (result.next()) {
                    historyID = new historyData
                            (result.getString("transaction_id"),
                                    result.getString("customer_name")
                                    , result.getString("total")
                                    , result.getString("paid")
                                    , result.getString("change_string")
                                    , result.getString("balance")
                                    , result.getInt("total_int")
                                    , result.getInt("balance_int")
                                    , result.getInt("change_int")
                                    , result.getDate("date")
                                    , result.getInt("year_int")
                                    , result.getString("month_string"));
                    monthList.add(historyID);
                }
            } catch (Exception ignored) {

            }
        } else if (history_month.getSelectionModel().getSelectedItem().equalsIgnoreCase("February")) {
            String sql = "SELECT * FROM customer_receipt WHERE MONTH(date) = 2 AND balance_int = 0 AND YEAR(date) = '" + history_year.getSelectionModel().getSelectedItem() + "'";
            connect = database.connectDb();
            try {
                historyData historyID;
                assert connect != null;
                prepare = connect.prepareStatement(sql);
                result = prepare.executeQuery();

                while (result.next()) {
                    historyID = new historyData
                            (result.getString("transaction_id"),
                                    result.getString("customer_name")
                                    , result.getString("total")
                                    , result.getString("paid")
                                    , result.getString("change_string")
                                    , result.getString("balance")
                                    , result.getInt("total_int")
                                    , result.getInt("balance_int")
                                    , result.getInt("change_int")
                                    , result.getDate("date")
                                    , result.getInt("year_int")
                                    , result.getString("month_string"));
                    monthList.add(historyID);
                }
            } catch (Exception ignored) {

            }
        } else if (history_month.getSelectionModel().getSelectedItem().equalsIgnoreCase("March")) {
            String sql = "SELECT * FROM customer_receipt WHERE MONTH(date) = 3 AND balance_int = 0 AND YEAR(date) = '" + history_year.getSelectionModel().getSelectedItem() + "'";
            connect = database.connectDb();
            try {
                historyData historyID;
                assert connect != null;
                prepare = connect.prepareStatement(sql);
                result = prepare.executeQuery();

                while (result.next()) {
                    historyID = new historyData
                            (result.getString("transaction_id"),
                                    result.getString("customer_name")
                                    , result.getString("total")
                                    , result.getString("paid")
                                    , result.getString("change_string")
                                    , result.getString("balance")
                                    , result.getInt("total_int")
                                    , result.getInt("balance_int")
                                    , result.getInt("change_int")
                                    , result.getDate("date")
                                    , result.getInt("year_int")
                                    , result.getString("month_string"));
                    monthList.add(historyID);
                }
            } catch (Exception ignored) {

            }
        } else if (history_month.getSelectionModel().getSelectedItem().equalsIgnoreCase("April")) {
            String sql = "SELECT * FROM customer_receipt WHERE MONTH(date) = 4 AND balance_int = 0 AND YEAR(date) = '" + history_year.getSelectionModel().getSelectedItem() + "'";
            connect = database.connectDb();
            try {
                historyData historyID;
                assert connect != null;
                prepare = connect.prepareStatement(sql);
                result = prepare.executeQuery();

                while (result.next()) {
                    historyID = new historyData
                            (result.getString("transaction_id"),
                                    result.getString("customer_name")
                                    , result.getString("total")
                                    , result.getString("paid")
                                    , result.getString("change_string")
                                    , result.getString("balance")
                                    , result.getInt("total_int")
                                    , result.getInt("balance_int")
                                    , result.getInt("change_int")
                                    , result.getDate("date")
                                    , result.getInt("year_int")
                                    , result.getString("month_string"));
                    monthList.add(historyID);
                }
            } catch (Exception ignored) {

            }
        } else if (history_month.getSelectionModel().getSelectedItem().equalsIgnoreCase("May")) {
            String sql = "SELECT * FROM customer_receipt WHERE MONTH(date) = 5 AND balance_int = 0 AND YEAR(date) = '" + history_year.getSelectionModel().getSelectedItem() + "'";
            connect = database.connectDb();
            try {
                historyData historyID;
                assert connect != null;
                prepare = connect.prepareStatement(sql);
                result = prepare.executeQuery();

                while (result.next()) {
                    historyID = new historyData
                            (result.getString("transaction_id"),
                                    result.getString("customer_name")
                                    , result.getString("total")
                                    , result.getString("paid")
                                    , result.getString("change_string")
                                    , result.getString("balance")
                                    , result.getInt("total_int")
                                    , result.getInt("balance_int")
                                    , result.getInt("change_int")
                                    , result.getDate("date")
                                    , result.getInt("year_int")
                                    , result.getString("month_string"));
                    monthList.add(historyID);
                }
            } catch (Exception ignored) {

            }
        } else if (history_month.getSelectionModel().getSelectedItem().equalsIgnoreCase("June")) {
            String sql = "SELECT * FROM customer_receipt WHERE MONTH(date) = 6 AND balance_int = 0 AND YEAR(date) = '" + history_year.getSelectionModel().getSelectedItem() + "'";
            connect = database.connectDb();
            try {
                historyData historyID;
                assert connect != null;
                prepare = connect.prepareStatement(sql);
                result = prepare.executeQuery();

                while (result.next()) {
                    historyID = new historyData
                            (result.getString("transaction_id"),
                                    result.getString("customer_name")
                                    , result.getString("total")
                                    , result.getString("paid")
                                    , result.getString("change_string")
                                    , result.getString("balance")
                                    , result.getInt("total_int")
                                    , result.getInt("balance_int")
                                    , result.getInt("change_int")
                                    , result.getDate("date")
                                    , result.getInt("year_int")
                                    , result.getString("month_string"));
                    monthList.add(historyID);
                }
            } catch (Exception ignored) {

            }
        } else if (history_month.getSelectionModel().getSelectedItem().equalsIgnoreCase("July")) {
            String sql = "SELECT * FROM customer_receipt WHERE MONTH(date) = 7 AND balance_int = 0 AND YEAR(date) = '" + history_year.getSelectionModel().getSelectedItem() + "'";
            connect = database.connectDb();
            try {
                historyData historyID;
                assert connect != null;
                prepare = connect.prepareStatement(sql);
                result = prepare.executeQuery();

                while (result.next()) {
                    historyID = new historyData
                            (result.getString("transaction_id"),
                                    result.getString("customer_name")
                                    , result.getString("total")
                                    , result.getString("paid")
                                    , result.getString("change_string")
                                    , result.getString("balance")
                                    , result.getInt("total_int")
                                    , result.getInt("balance_int")
                                    , result.getInt("change_int")
                                    , result.getDate("date")
                                    , result.getInt("year_int")
                                    , result.getString("month_string"));
                    monthList.add(historyID);
                }
            } catch (Exception ignored) {

            }
        } else if (history_month.getSelectionModel().getSelectedItem().equalsIgnoreCase("August")) {
            String sql = "SELECT * FROM customer_receipt WHERE MONTH(date) = 8 AND balance_int = 0 AND YEAR(date) = '" + history_year.getSelectionModel().getSelectedItem() + "'";
            connect = database.connectDb();
            try {
                historyData historyID;
                assert connect != null;
                prepare = connect.prepareStatement(sql);
                result = prepare.executeQuery();
                while (result.next()) {
                    historyID = new historyData
                            (result.getString("transaction_id"),
                                    result.getString("customer_name")
                                    , result.getString("total")
                                    , result.getString("paid")
                                    , result.getString("change_string")
                                    , result.getString("balance")
                                    , result.getInt("total_int")
                                    , result.getInt("balance_int")
                                    , result.getInt("change_int")
                                    , result.getDate("date")
                                    , result.getInt("year_int")
                                    , result.getString("month_string"));

                    monthList.add(historyID);
                }
            } catch (Exception ignored) {

            }
        } else if (history_month.getSelectionModel().getSelectedItem().equalsIgnoreCase("September")) {
            String sql = "SELECT * FROM customer_receipt WHERE MONTH(date) = 9 AND balance_int = 0 AND YEAR(date) = '" + history_year.getSelectionModel().getSelectedItem() + "'";
            connect = database.connectDb();
            try {
                historyData historyID;
                assert connect != null;
                prepare = connect.prepareStatement(sql);
                result = prepare.executeQuery();
                while (result.next()) {
                    historyID = new historyData
                            (result.getString("transaction_id"),
                                    result.getString("customer_name"),
                                    result.getString("total"),
                                    result.getString("paid"),
                                    result.getString("change_string"),
                                    result.getString("balance"),
                                    result.getInt("total_int"),
                                    result.getInt("balance_int"), result.getInt("change_int")
                                    , result.getDate("date")
                                    , result.getInt("year_int")
                                    , result.getString("month_string"));
                    monthList.add(historyID);
                }
            } catch (Exception ignored) {

            }
        } else if (history_month.getSelectionModel().getSelectedItem().equalsIgnoreCase("October")) {
            String sql = "SELECT * FROM customer_receipt WHERE MONTH(date) = 10 AND balance_int = 0 AND YEAR(date) = '" + history_year.getSelectionModel().getSelectedItem() + "'";
            connect = database.connectDb();
            try {
                historyData historyID;
                assert connect != null;
                prepare = connect.prepareStatement(sql);
                result = prepare.executeQuery();
                while (result.next()) {
                    historyID = new historyData
                            (result.getString("transaction_id"),
                                    result.getString("customer_name"),
                                    result.getString("total"),
                                    result.getString("paid"),
                                    result.getString("change_string"),
                                    result.getString("balance"),
                                    result.getInt("total_int"),
                                    result.getInt("balance_int"), result.getInt("change_int")
                                    , result.getDate("date")
                                    , result.getInt("year_int")
                                    , result.getString("month_string"));

                    monthList.add(historyID);
                }
            } catch (Exception ignored) {

            }
        } else if (history_month.getSelectionModel().getSelectedItem().equalsIgnoreCase("November")) {
            String sql = "SELECT * FROM customer_receipt WHERE MONTH(date) = 11 AND balance_int = 0 AND YEAR(date) = '" + history_year.getSelectionModel().getSelectedItem() + "'";
            connect = database.connectDb();
            try {
                historyData historyID;
                assert connect != null;
                prepare = connect.prepareStatement(sql);
                result = prepare.executeQuery();
                while (result.next()) {
                    historyID = new historyData
                            (result.getString("transaction_id"),
                                    result.getString("customer_name"),
                                    result.getString("total"),
                                    result.getString("paid"),
                                    result.getString("change_string"),
                                    result.getString("balance"),
                                    result.getInt("total_int"),
                                    result.getInt("balance_int"), result.getInt("change_int")
                                    , result.getDate("date")
                                    , result.getInt("year_int")
                                    , result.getString("month_string"));

                    monthList.add(historyID);
                }
            } catch (Exception ignored) {

            }
        } else if (history_month.getSelectionModel().getSelectedItem().equalsIgnoreCase("December")) {
            String sql = "SELECT * FROM customer_receipt WHERE MONTH(date) = 12 AND balance_int = 0 AND YEAR(date) = '" + history_year.getSelectionModel().getSelectedItem() + "'";
            connect = database.connectDb();
            try {
                historyData historyID;
                assert connect != null;
                prepare = connect.prepareStatement(sql);
                result = prepare.executeQuery();
                while (result.next()) {
                    historyID = new historyData
                            (result.getString("transaction_id"),
                                    result.getString("customer_name"),
                                    result.getString("total"),
                                    result.getString("paid"),
                                    result.getString("change_string"),
                                    result.getString("balance"),
                                    result.getInt("total_int"),
                                    result.getInt("balance_int"),
                                    result.getInt("change_int")
                                    , result.getDate("date")
                                    , result.getInt("year_int")
                                    , result.getString("month_string"));
                    monthList.add(historyID);
                }
            } catch (Exception ignored) {

            }
        } else if (history_month.getSelectionModel().getSelectedItem().equalsIgnoreCase("All") || history_month.getSelectionModel().getSelectedItem().equalsIgnoreCase("Select Month")) {
            String sql = "SELECT * FROM customer_receipt WHERE balance_int = 0";
            connect = database.connectDb();
            try {
                historyData historyID;
                assert connect != null;
                prepare = connect.prepareStatement(sql);
                result = prepare.executeQuery();
                while (result.next()) {
                    historyID = new historyData
                            (result.getString("transaction_id"),
                                    result.getString("customer_name"),
                                    result.getString("total"),
                                    result.getString("paid"),
                                    result.getString("change_string"),
                                    result.getString("balance"),
                                    result.getInt("total_int"),
                                    result.getInt("balance_int"), result.getInt("change_int")
                                    , result.getDate("date")
                                    , result.getInt("year_int")
                                    , result.getString("month_string"));

                    monthList.add(historyID);
                }
            } catch (Exception ignored) {

            }
        }
        historyFullyPaidDisplayTotal();
        history_column_transactionNumber.setCellValueFactory(new PropertyValueFactory<>("transaction_id"));
        history_column_costumerName.setCellValueFactory(new PropertyValueFactory<>("customer_name"));
        history_column_total.setCellValueFactory(new PropertyValueFactory<>("total"));
        history_column_paid.setCellValueFactory(new PropertyValueFactory<>("paid"));
        history_column_change.setCellValueFactory(new PropertyValueFactory<>("change_string"));
        history_column_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        history_fullyPaidTable.setItems(monthList);

    } // Display the table from months (History)
    public String historyFullyPaidDisplayTotal() {
        if (convertMonthIntoInt() == 0) {
            String sql = "SELECT SUM(total_int) FROM customer_receipt WHERE balance_int = 0 AND YEAR(date) = '" + history_year.getSelectionModel().getSelectedItem() + "'";
            connect = database.connectDb();

            try {
                assert connect != null;
                prepare = connect.prepareStatement(sql);
                result = prepare.executeQuery();
                int totalFullyPaid = 0;
                while (result.next()) {
                    totalFullyPaid = result.getInt("SUM(total_int)");
                }
                history_totalIncome.setText("₱" + formatPrice(String.valueOf(totalFullyPaid)));

            } catch (Exception ignored) {

            }
        } else {
            String sql = "SELECT SUM(total_int) FROM customer_receipt WHERE balance_int = 0 AND MONTH(date) = '" + convertMonthIntoInt() + "' AND YEAR(date) = '" + history_year.getSelectionModel().getSelectedItem() + "'";
            connect = database.connectDb();

            try {
                assert connect != null;
                prepare = connect.prepareStatement(sql);
                result = prepare.executeQuery();
                int totalFullyPaid = 0;
                while (result.next()) {
                    totalFullyPaid = result.getInt("SUM(total_int)");
                }
                history_totalIncome.setText("₱" + formatPrice(String.valueOf(totalFullyPaid)));

            } catch (Exception ignored) {

            }

        }
        return history_totalIncome.getText();
    } // Display the total income (History)
    public String historyNotFullyPaidDisplayTotal() {
        String sql = "SELECT SUM(paid_int) FROM customer_receipt WHERE balance_int != 0";
        connect = database.connectDb();
        int totalNotFullyPaid = 0;
        try {
            assert connect != null;
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()) {
                totalNotFullyPaid = result.getInt("SUM(paid_int)");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "₱" + formatPrice(String.valueOf(totalNotFullyPaid));
    } // Display the total income (History)
    public String historyNotFullyPaidCalculateTotalBalance(){
        String sql = "SELECT SUM(balance_int) FROM customer_receipt WHERE balance_int != 0";
        connect = database.connectDb();
        int totalNotFullyPaid = 0;
        try {
            assert connect != null;
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()) {
                totalNotFullyPaid = result.getInt("SUM(balance_int)");
            }

        }catch (Exception ignored){

        }
        return "₱" + formatPrice(String.valueOf(totalNotFullyPaid));
    }
    String total = "";
    public void fillFieldWithDataFromWithBalanceTable() {
        history_withBalanceTable.setOnMouseClicked(e -> {
            total = history_withBalanceTable.getItems().get(history_withBalanceTable.getSelectionModel().getSelectedIndex()).getTotal();
            transactionId = history_withBalanceTable.getItems().get(history_withBalanceTable.getSelectionModel().getSelectedIndex()).getTransaction_id();
            historyData historyData = history_withBalanceTable.getItems().get(history_withBalanceTable.getSelectionModel().getSelectedIndex());
            history_balanceLabel.setText(historyData.getBalance());
            balanceInt = historyData.getBalance_int();
            paidInt = Integer.parseInt(historyData.getPaid().substring(1).replaceAll(",", ""));
            history_transactionId.setText(historyData.getTransaction_id());
        });
        history_fullyPaidTable.setOnMouseClicked(e -> {

            transactionId = history_fullyPaidTable.getItems().get(history_fullyPaidTable.getSelectionModel().getSelectedIndex()).getTransaction_id();
            historyData historyData = history_fullyPaidTable.getItems().get(history_fullyPaidTable.getSelectionModel().getSelectedIndex());
            history_balanceLabel.setText(historyData.getBalance());
            balanceInt = historyData.getBalance_int();
            paidInt = Integer.parseInt(historyData.getPaid().substring(1).replaceAll(",", ""));
        });

    } // Fill the text-field with data from table (History)

    public void historyPay() {
        int totalPaid = paidInt + Integer.parseInt(history_amount.getText());
        int totalBalance = history_willBeBalance.getText().equalsIgnoreCase("₱0") ? 0 : Integer.parseInt(history_willBeBalance.getText().substring(1).replaceAll(",", ""));
        int totalChange = history_changeLabel.getText().equalsIgnoreCase("₱0") ? 0 : Integer.parseInt(history_changeLabel.getText().substring(1).replaceAll(",", ""));
        String sql = "UPDATE customer_receipt SET paid = '₱" + formatPrice(String.valueOf(totalPaid)) + "', balance = '₱" + formatPrice(String.valueOf(totalBalance)) + "', change_string = '₱" + formatPrice(String.valueOf(totalChange)) + "', balance_int = '" + totalBalance + "', change_int = '" + totalChange + "', paid_int = '" + totalPaid + "' WHERE transaction_id = '" + transactionId + "'";
        connect = database.connectDb();
        Alert alert;
        try {
            if (history_amount.getText().isEmpty()||history_amount.getText().startsWith("0")) {
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
                    statement = connect.createStatement();
                    statement.executeUpdate(sql);
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Paid");
                    alert.showAndWait();
                    historyPayReceipt();
                    historyShowWithBalanceData();
                    historyDisplayTableFromMonths();
                    history_transactionId.setText("");
                    history_balanceLabel.setText("₱0");
                    history_amount.setText("");
                    history_willBeBalance.setText("₱0");
                    history_changeLabel.setText("₱0");
                }
            }
        } catch (Exception ignored) {

        }
    } // Pay the balance (History)
    public void historyPayReceipt() throws JRException {
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("transaction", transactionId);
        parameters.put("total", total);
        parameters.put("lbalance", history_balanceLabel.getText());
        parameters.put("paid", "₱" + formatPrice(history_amount.getText()));
        parameters.put("change", history_changeLabel.getText());
        parameters.put("balance", history_willBeBalance.getText());

        InputStream inputStream = getClass().getResourceAsStream("/com/example/invetorysystem/receiptforHistory.jrxml");
        JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, database.connectDb());
        JasperViewer.viewReport(jasperPrint, false);
    }
    public void historyDeleteMultipleRows() {
        // Check if any row is selected in history_fullyPaidTable
        if (history_fullyPaidTable.getSelectionModel().getSelectedItems().isEmpty() && history_withBalanceTable.getSelectionModel().getSelectedItems().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No rows selected");
            alert.showAndWait();
            return;
        }

        // Confirmation alert
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation");
        confirmationAlert.setHeaderText(null);
        confirmationAlert.setContentText("Are you sure you want to delete the selected rows?");

        Optional<ButtonType> result = confirmationAlert.showAndWait();
        if (result.get() != ButtonType.OK){
            return; // User chose not to proceed
        }

        // Delete each selected row from history_fullyPaidTable
        for (historyData history : history_fullyPaidTable.getSelectionModel().getSelectedItems()) {
            String sql = "DELETE from customer_receipt WHERE transaction_id = ?";

            try {
                PreparedStatement statement = connect.prepareStatement(sql);
                statement.setString(1, history.getTransaction_id());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Delete each selected row from history_withBalanceTable
        for (historyData history : history_withBalanceTable.getSelectionModel().getSelectedItems()) {
            String sql = "DELETE from customer_receipt WHERE transaction_id = ?";

            try {
                PreparedStatement statement = connect.prepareStatement(sql);
                statement.setString(1, history.getTransaction_id());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Show success message
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Successfully Deleted");
        alert.showAndWait();

        // Refresh the table view to reflect the changes
        historyShowWithBalanceData();
        historyDisplayTableFromMonths();
        history_balanceLabel.setText("₱0");
        history_amount.setText("");
        history_willBeBalance.setText("₱0");
        history_changeLabel.setText("₱0");
        transactionId = "";
    } // Delete multiple rows (History)
    public void historyDelete(){
        if (history_fullyPaidTable.getSelectionModel().getSelectedItems().size() > 1 || history_withBalanceTable.getSelectionModel().getSelectedItems().size() > 1) {
            historyDeleteMultipleRows();
            return;
        }

        String sql = "DELETE FROM customer_receipt WHERE transaction_id = '"+transactionId+"'";
        connect = database.connectDb();
        Alert alert;
        try {
            if (transactionId == null || transactionId.isEmpty() || transactionId.equalsIgnoreCase("0")) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Select a row first");
                alert.showAndWait();
            }else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Sure?");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you to delete this row "+ transactionId +"?");
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
                    historyShowWithBalanceData();
                    historyDisplayTableFromMonths();
                    history_balanceLabel.setText("₱0");
                    history_amount.setText("");
                    history_willBeBalance.setText("₱0");
                    history_changeLabel.setText("₱0");
                    transactionId = "";

                }

            }
        }catch (Exception ignored){

        }
    } // Delete the data from table (History)
    public void historyDeleteAllTable(){
        String sql = "DELETE FROM customer_receipt";
        connect = database.connectDb();
        Alert alert;
        try {
            if(history_withBalanceTable.getItems().isEmpty() && history_fullyPaidTable.getItems().isEmpty() ) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("No data to delete");
                alert.showAndWait();
            }else{
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Sure?");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to delete all the data in the table?");
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

                    historyShowWithBalanceData();
                    historyDisplayTableFromMonths();
                    history_balanceLabel.setText("₱0");
                    history_amount.setText("");
                    history_willBeBalance.setText("₱0");
                    history_changeLabel.setText("₱0");
                    transactionId = "";
                }
            }


        }catch (Exception ignored){

        }
    } // Delete all the data from table (History)
    public void calculateHistoryBalance() {

        int balance = 0;
        int change = 0;
        if (history_amount.getText().isEmpty()) {
            history_willBeBalance.setText("₱0");
        } else {
            int total = Integer.parseInt(history_balanceLabel.getText().substring(1).replaceAll(",", ""));
            int amount = Integer.parseInt(history_amount.getText());
            balance = total - amount;
            change = amount - total;

            if (balance < 0) { // If the balance is negative, it will display the change
                history_willBeBalance.setText("₱0");
            } else {
                history_willBeBalance.setText("₱" + formatPrice(String.valueOf(balance)));
            }
            if (change < 0) { // If the change is negative, it will display the balance
                history_changeLabel.setText("₱0");
            } else {
                history_changeLabel.setText("₱" + formatPrice(String.valueOf(change)));
            }

        }
    } // Calculate the balance (History)
    public void disableHistoryPayIfTableRowIsNotSelected() {
        history_withBalanceTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection == null) {
                history_amount.setDisable(true);
                history_balancePay.setDisable(true);
            } else {
                history_amount.setDisable(false);
                history_balancePay.setDisable(false);
            }
        });
    } // Disable the pay button if the table row is not selected (History)
    public void clearHistory() {
        history_balanceLabel.setText("₱0");
        history_amount.setText("");
        history_willBeBalance.setText("₱0");
        history_changeLabel.setText("₱0");
    } // Clear all text-fields (History)
    public Integer convertMonthIntoInt() {
        int month = 0;
        if (history_month.getSelectionModel().getSelectedItem().equalsIgnoreCase("January")) {
            month = 1;
        } else if (history_month.getSelectionModel().getSelectedItem().equalsIgnoreCase("February")) {
            month = 2;
        } else if (history_month.getSelectionModel().getSelectedItem().equalsIgnoreCase("March")) {
            month = 3;
        } else if (history_month.getSelectionModel().getSelectedItem().equalsIgnoreCase("April")) {
            month = 4;
        } else if (history_month.getSelectionModel().getSelectedItem().equalsIgnoreCase("May")) {
            month = 5;
        } else if (history_month.getSelectionModel().getSelectedItem().equalsIgnoreCase("June")) {
            month = 6;
        } else if (history_month.getSelectionModel().getSelectedItem().equalsIgnoreCase("July")) {
            month = 7;
        } else if (history_month.getSelectionModel().getSelectedItem().equalsIgnoreCase("August")) {
            month = 8;
        } else if (history_month.getSelectionModel().getSelectedItem().equalsIgnoreCase("September")) {
            month = 9;
        } else if (history_month.getSelectionModel().getSelectedItem().equalsIgnoreCase("October")) {
            month = 10;
        } else if (history_month.getSelectionModel().getSelectedItem().equalsIgnoreCase("November")) {
            month = 11;
        } else if (history_month.getSelectionModel().getSelectedItem().equalsIgnoreCase("December")) {
            month = 12;
        } else if (history_month.getSelectionModel().getSelectedItem().equalsIgnoreCase("All")) {
            month = 0;
        }
        return month;
    }  // Convert the month into integer (History)
    public void printAll() throws JRException {

        Integer year = Integer.valueOf(history_year.getSelectionModel().getSelectedItem());
        System.out.println(year);
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("pesoSign", "₱");
        parameters.put("total", historyFullyPaidDisplayTotal());
        parameters.put("Year", year);
        InputStream is = getClass().getResourceAsStream("/com/example/invetorysystem/generateReportForAll.jrxml");
        JasperDesign jasperDesign = JRXmlLoader.load(is);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, database.connectDb());
        JasperViewer.viewReport(jasperPrint, false);
    }  // Print all the data (History)
    public void printTable() throws JRException {
        if(history_fullyPaid.isSelected()){
            if(history_totalIncome.getText().equalsIgnoreCase("₱0")){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("No data to print");
                alert.showAndWait();
            }else{
                if(history_month.getSelectionModel().getSelectedItem().equalsIgnoreCase("ALL")){
                    printAll();
                }else {
                    String month = history_month.getSelectionModel().getSelectedItem();
                    Integer year = Integer.valueOf(history_year.getSelectionModel().getSelectedItem());
                    System.out.println(month);
                    System.out.println(year);
                    HashMap<String, Object> parameters = new HashMap<>();
                    parameters.put("pesoSign", "₱");
                    parameters.put("total", historyFullyPaidDisplayTotal());
                    parameters.put("Month", month);
                    parameters.put("Year", year);
                    InputStream is = getClass().getResourceAsStream("/com/example/invetorysystem/generateReport.jrxml");
                    JasperDesign jasperDesign = JRXmlLoader.load(is);
                    JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
                    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, database.connectDb());
                    JasperViewer.viewReport(jasperPrint, false);
                }
            }
        }else{
            printNotFullyPaid(); // Print the data from table (History)
        }



    }  // Print the data from table (History)

    public void printNotFullyPaid(){
        if (history_withBalanceTable.getItems().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No data to print");
            alert.showAndWait();
        }else{
            String month = history_month.getSelectionModel().getSelectedItem();
            Integer year = Integer.valueOf(history_year.getSelectionModel().getSelectedItem());
            System.out.println(month);
            System.out.println(year);
            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put("pesoSign", "₱");
            parameters.put("balance_total", historyNotFullyPaidCalculateTotalBalance());
            parameters.put("total", historyNotFullyPaidDisplayTotal());
            parameters.put("Month", month);
            parameters.put("Year", year);
            InputStream is = getClass().getResourceAsStream("/com/example/invetorysystem/generateReportForNotFullyPaid.jrxml");
            JasperDesign jasperDesign = null;
            try {
                jasperDesign = JRXmlLoader.load(is);
            } catch (JRException e) {
                e.printStackTrace();
            }
            JasperReport jasperReport = null;
            try {
                jasperReport = JasperCompileManager.compileReport(jasperDesign);
            } catch (JRException e) {
                e.printStackTrace();
            }
            JasperPrint jasperPrint = null;
            try {
                jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, database.connectDb());
            } catch (JRException e) {
                e.printStackTrace();
            }
            JasperViewer.viewReport(jasperPrint, false);
        }
    }



    public void makeTableNotReOrder() {
        addProduct_table.skinProperty().addListener((obs, oldSkin, newSkin) -> {
            final TableHeaderRow header = (TableHeaderRow) addProduct_table.lookup("TableHeaderRow");
            header.reorderingProperty().addListener((o, oldVal, newVal) -> header.setReordering(false));
        });
        order_table.skinProperty().addListener((obs, oldSkin, newSkin) -> {
            final TableHeaderRow header = (TableHeaderRow) order_table.lookup("TableHeaderRow");
            header.reorderingProperty().addListener((o, oldVal, newVal) -> header.setReordering(false));
        });
        history_fullyPaidTable.skinProperty().addListener((obs, oldSkin, newSkin) -> {
            final TableHeaderRow header = (TableHeaderRow) history_fullyPaidTable.lookup("TableHeaderRow");
            header.reorderingProperty().addListener((o, oldVal, newVal) -> header.setReordering(false));
        });
        history_withBalanceTable.skinProperty().addListener((obs, oldSkin, newSkin) -> {
            final TableHeaderRow header = (TableHeaderRow) history_withBalanceTable.lookup("TableHeaderRow");
            header.reorderingProperty().addListener((o, oldVal, newVal) -> header.setReordering(false));
        });
    } // Make the table not reorder able (Products) (Order) (History)

    public int getQtyFromCustomer() throws SQLException {
        String getQty = "SELECT quantity FROM customer WHERE productName = '"
                + order_productName.getSelectionModel().getSelectedItem() + "'" ;
        statement = connect.createStatement();
        result = statement.executeQuery(getQty);
        int qty = 0;

        while (result.next()) {
            qty = result.getInt("quantity");
        }
        return qty;
    } // Get the quantity from customer (Order)

    public int getQtyFromCustomer2() throws SQLException {
        String getQty = "SELECT quantity FROM customer WHERE productName = '"
                + order_customName.getText() + "'";
        statement = connect.createStatement();
        result = statement.executeQuery(getQty);
        int qty = 0;

        while (result.next()) {
            qty = result.getInt("quantity");
        }
        return qty;
    } // Get the quantity from customer (Order)

    public int getPriceFromCustomer() throws SQLException {
        String getPrice = "SELECT price_int FROM customer";
        statement = connect.createStatement();
        result = statement.executeQuery(getPrice);
        int price_int = 0;

        while (result.next()) {
            price_int = result.getInt("price_int");

        }
        return price_int;
    } // Get the price from customer (Order)

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
    } // Get the price from products (Order)

    public int getQtyFromProducts() throws SQLException {
        String getQty = "SELECT quantity FROM products WHERE productName = '"
                + order_productName.getSelectionModel().getSelectedItem() + "'";
        statement = connect.createStatement();
        result = statement.executeQuery(getQty);
        int qty = 0;
        while (result.next()) {
            qty = result.getInt("quantity");
        }
        return qty;
    } // Get the quantity from products (Order)

    public void updateQtyFromProducts() throws SQLException {
        int qty = 0;
        for (int i = 0; i < order_table.getItems().size(); i++) {
            String getQty = "SELECT quantity FROM products WHERE productName = '"
                    + order_table.getItems().get(i).getProductName() + "'";
            statement = connect.createStatement();
            result = statement.executeQuery(getQty);
            while (result.next()) {
                qty = result.getInt("quantity");
            }
            int totalQty = qty - order_table.getItems().get(i).getQuantity();
            String update = "UPDATE products SET quantity = '" + totalQty + "' WHERE productName = '" + order_table.getItems().get(i).getProductName() + "'";
            statement = connect.createStatement();
            statement.executeUpdate(update);
        }

    }  // Update the quantity from products (Order)

    public void updateStatusFromProducts() throws SQLException {


        String sql = "UPDATE products SET status = 'Not Available'  WHERE quantity = 0";
        connect = database.connectDb();
        try {
            assert connect != null;
            statement = connect.createStatement();
            statement.executeUpdate(sql);
        } catch (Exception ignored) {

        }
        String sql2 = "UPDATE products SET status = 'Available'  WHERE quantity != 0";
        connect = database.connectDb();
        try {
            assert connect != null;
            statement = connect.createStatement();
            statement.executeUpdate(sql2);
        } catch (Exception ignored) {

        }


    } // Update the status from products (Order)

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
    }  // Get the name from products to compare (Order)

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
    } // Format the price (Home) (Products) (Order) (History)

    public void onlyNumInTextField() {
        addProduct_price.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (!t1.matches("\\d*") || Integer.parseInt(t1) > 100000) {
                    addProduct_price.setText(t1.replaceAll("[^\\d]", ""));
                    if(Integer.parseInt(addProduct_price.getText()) > 100000){
                        addProduct_price.setText("100000");
                    }
                }
            }
        });
        order_customPrice.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (!t1.matches("\\d*") || Integer.parseInt(t1) > 100000) {
                    order_customPrice.setText(t1.replaceAll("[^\\d]", ""));
                    if(Integer.parseInt(order_customPrice.getText()) > 100000){
                        order_customPrice.setText("100000");
                    }
                }
            }
        });
        order_amount.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (!t1.matches("\\d*") || Integer.parseInt(t1) > 10000000) {
                    order_amount.setText(t1.replaceAll("[^\\d]", ""));
                    if(Integer.parseInt(order_amount.getText()) > 10000000){
                        order_amount.setText("10000000");
                    }
                }
            }
        });
        history_amount.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (!t1.matches("\\d*") || Integer.parseInt(t1) > 10000000) {
                    history_amount.setText(t1.replaceAll("[^\\d]", ""));
                    if(Integer.parseInt(history_amount.getText()) > 10000000){
                        history_amount.setText("10000000");
                    }
                }
            }
        });
        calculateBalanceAndChange();
        calculateHistoryBalance();
    } // Only numbers allow in Price text-field

    public void clearTextField() {
        addProduct_name.setText("");
        addProduct_price.setText("");
        addProduct_status.getSelectionModel().select("Choose");
        addProduct_update.setDisable(true);
        addProduct_delete.setDisable(true);
        addProduct_table.getSelectionModel().clearSelection();
        addProduct_quantity.getValueFactory().setValue(1);

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
            if(order_table.getItems().isEmpty()) {
                home_form.setVisible(true);
                orders_form.setVisible(false);
                addProducts_form.setVisible(false);
                history_form.setVisible(false);
                home.setStyle("-fx-background-color: linear-gradient(to bottom right, #e7e3e3, #e1e7e1)");
                orders.setStyle("-fx-background-color: transparent");
                addProducts.setStyle("-fx-background-color: transparent");
                history.setStyle("-fx-background-color: transparent");
                homeNumberOfOrder(); // Display the number of order (Home)
                homeTotalIncome(); // Display the total income (Home)
                homeAvailableProducts(); // Display the number of available products (Home)
                homeIncomeDataChart(); // Display the income data chart (Home)
                homeIncomeDataChartForYear(); // Display the income data chart for year (Home)
            }else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("There is still data in the order table. If you exit now, this data will be deleted. Are you sure you want to proceed?");
                Optional<ButtonType> option = alert.showAndWait();
                if (option.get().equals(ButtonType.OK)) {
                    orderResetTableWithoutAsking();
                    home_form.setVisible(true);
                    orders_form.setVisible(false);
                    addProducts_form.setVisible(false);
                    history_form.setVisible(false);
                    home.setStyle("-fx-background-color: linear-gradient(to bottom right, #e7e3e3, #e1e7e1)");
                    orders.setStyle("-fx-background-color: transparent");
                    addProducts.setStyle("-fx-background-color: transparent");
                    history.setStyle("-fx-background-color: transparent");
                    homeNumberOfOrder(); // Display the number of order (Home)
                    homeTotalIncome(); // Display the total income (Home)
                    homeAvailableProducts(); // Display the number of available products (Home)
                    homeIncomeDataChart(); // Display the income data chart (Home)
                    homeIncomeDataChartForYear(); // Display the income data chart for year (Home)
                }
            }
        } else if (event.getSource() == addProducts_btn || event.getSource() == addProducts) {
            if (order_table.getItems().isEmpty()) {
                home_form.setVisible(false);
                orders_form.setVisible(false);
                addProducts_form.setVisible(true);
                history_form.setVisible(false);
                home.setStyle("-fx-background-color: transparent");
                orders.setStyle("-fx-background-color: transparent");
                addProducts.setStyle("-fx-background-color: linear-gradient(to bottom right, #e7e3e3, #e1e7e1)");
                history.setStyle("-fx-background-color: transparent");
                addProductShowListData();
                addProductListStatus();
                addProductsSearch();
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("There is still data in the order table. If you exit now, this data will be deleted. Are you sure you want to proceed?");

                Optional<ButtonType> option = alert.showAndWait();
                if (option.get().equals(ButtonType.OK)) {
                    orderResetTableWithoutAsking();
                    home_form.setVisible(false);
                    orders_form.setVisible(false);
                    addProducts_form.setVisible(true);
                    history_form.setVisible(false);
                    home.setStyle("-fx-background-color: transparent");
                    orders.setStyle("-fx-background-color: transparent");
                    addProducts.setStyle("-fx-background-color: linear-gradient(to bottom right, #e7e3e3, #e1e7e1)");
                    history.setStyle("-fx-background-color: transparent");
                    addProductShowListData();
                    addProductListStatus();
                    addProductsSearch();
                }

            }

        } else if (event.getSource() == orders_btn || event.getSource() == orders) {
            home_form.setVisible(false);
            orders_form.setVisible(true);
            addProducts_form.setVisible(false);
            history_form.setVisible(false);
            home.setStyle("-fx-background-color: transparent");
            orders.setStyle("-fx-background-color: linear-gradient(to bottom right, #e7e3e3, #e1e7e1)");
            addProducts.setStyle("-fx-background-color: transparent");
            history.setStyle("-fx-background-color: transparent");
            orderShowListData(); // Put the data from SQL to Table (Order)
            typeOfPurchased(); // Add the data to combobox (Type of Purchased)
            orderPreMade(); // Add the data to combobox (Pre-Made)
            orderSpinner(); // Set the value of spinner (Order)
            orderDisplayTotal(); // Display the total price (Order)

        } else if (event.getSource() == history_btn || event.getSource() == history) {
            if(order_table.getItems().isEmpty()){
                home_form.setVisible(false);
                orders_form.setVisible(false);
                addProducts_form.setVisible(false);
                history_form.setVisible(true);
                home.setStyle("-fx-background-color: transparent");
                orders.setStyle("-fx-background-color: transparent");
                addProducts.setStyle("-fx-background-color: transparent");
                history.setStyle("-fx-background-color: linear-gradient(to bottom right, #e7e3e3, #e1e7e1)");
                clearHistory(); // Clear all text-fields (History)
                disableHistoryPayIfTableRowIsNotSelected(); // Disable the pay button if the table row is not selected (History)
                historyShowWithBalanceData(); // Put the data from SQL with balance to Table (History)
                historyAddMonth(); // Add the data to combobox (Month) (History)
                historyDisplayTableFromMonths(); // Display the table from months (History)
            }else{
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("There is still data in the order table. If you exit now, this data will be deleted. Are you sure you want to proceed?");
                Optional<ButtonType> option = alert.showAndWait();
                if (option.get().equals(ButtonType.OK)) {
                    orderResetTableWithoutAsking();
                    home_form.setVisible(false);
                    orders_form.setVisible(false);
                    addProducts_form.setVisible(false);
                    history_form.setVisible(true);
                    home.setStyle("-fx-background-color: transparent");
                    orders.setStyle("-fx-background-color: transparent");
                    addProducts.setStyle("-fx-background-color: transparent");
                    history.setStyle("-fx-background-color: linear-gradient(to bottom right, #e7e3e3, #e1e7e1)");
                    clearHistory(); // Clear all text-fields (History)
                    disableHistoryPayIfTableRowIsNotSelected(); // Disable the pay button if the table row is not selected (History)
                    historyShowWithBalanceData(); // Put the data from SQL with balance to Table (History)
                    historyAddMonth(); // Add the data to combobox (Month) (History)
                    historyDisplayTableFromMonths(); // Display the table from months (History)
                }
            }
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
        addProduct_table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        order_table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        history_fullyPaidTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        addDataToDataChartFilter(); // Add the data to data chart filter (Home)

        homeIncomeDataChartForYear(); // Display the income data chart for year (Home)
        homeNumberOfOrder(); // Display the number of order (Home)
        homeTotalIncome(); // Display the total income (Home)
        homeAvailableProducts(); // Display the number of available products (Home)
        homeIncomeDataChart(); // Display the income data chart (Home)



        productsIfMultipleSelected(); // If multiple row is selected, disable the update and delete button (Products)
        addProductShowListData(); // Put the data from SQL to Table
        addProductListStatus(); // Add the data to combobox (Status)
        ifTotalIsZero(); // If total is zero, disable the pay button and amount text-field (Products)
        addProductSpinner(); // Set the value of spinner

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play(); // Play the timeline (Home)


        typeOfPurchased(); // Add the data to combobox (Type of Purchased)
        orderPreMade(); // Add the data to combobox (Pre-Made)
        orderSpinner(); // Set the value of spinner (Order)
        orderShowListData(); // Put the data from SQL to Table (Order)
        orderDisplayTotal(); // Display the total price (Order)


        disableYearIfMonthIsAll(); // Disable the year if the month is all (History)
        disableHistoryPayIfTableRowIsNotSelected(); // Disable the pay button if the table row is not selected (History)
        historyShowWithBalanceData(); // Put the data from SQL with balance to Table (History)
        historyAddMonth(); // Add the data to combobox (Month) (History)
        historyAddYear(); // Add the data to combobox (Year) (History)
        historyDisplayTableFromMonths(); // Display the table from months (History)
        historyFullyPaidDisplayTotal(); // Display the total price (History)
        fillFieldWithDataFromWithBalanceTable(); // Fill the text-field (History)

        history_fullyPaid.setSelected(true);
        history_fullyPaid.setDisable(true);
        history_haveBalancePayAnchorPane.setVisible(false);
        history_filterAnchorPane.setVisible(true);

        makeTableNotReOrder(); // Make the table not reorder able (Products) (Order) (History)
        disableUpdateIfRowIsNotSelected(); // Disable the update and delete button if the row is not selected
        onlyNumInTextField(); // Only numbers allow in Price text-field


        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                orderResetTableWithoutAsking();
            }
        }); // If the program is closed, it will reset the table
    } // Initialize
}
