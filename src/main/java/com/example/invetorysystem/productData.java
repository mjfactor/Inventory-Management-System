package com.example.invetorysystem;


import java.util.Date;

public class productData {
    private Integer productId;
    private String productName;
    private String price;
    private String status;

    private Date date;

    public productData(Integer productId, String productName, String price, String status, Date date ){
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.status = status;
        this.date = date;
    }
    public Integer getProductId(){
        return productId;
    }

    public String getProductName(){
        return productName;
    }
    public String getPrice(){
        return price;
    }
    public String getStatus(){
        return status;
    }

    public Date getDate(){
        return date;
    }
}
