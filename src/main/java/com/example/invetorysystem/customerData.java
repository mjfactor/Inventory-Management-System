package com.example.invetorysystem;

import java.util.Date;

public class customerData {
    private String customerName;
    private String productName;
    private String type;
    private Integer quantity;
    private String price;
    private Integer price_int;
    private Date date;

    public customerData(String customerName, String type, String productName, Integer quantity, String price, Integer price_int, Date date ){
        this.customerName = customerName;
        this.type = type;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.price_int = price_int;
        this.date = date;
    }
    public String getCustomerName(){
        return customerName;
    }
    public String getType(){
        return type;
    }
    public String getProductName(){
        return productName;
    }
    public Integer getQuantity(){
        return quantity;
    }
    public String getPrice(){
        return price;
    }
    public Integer getPrice_int(){
        return price_int;
    }
    public Date getDate(){
        return date;
    }
}
