package com.example.invetorysystem;
import java.util.Date;

public class productData {
    private Integer productId;
    private String productName;
    private String price;
    private Integer quantity;
    private Integer price_int;
    private String status;
    private Date date;

    public productData(Integer productId, String productName, String price, Integer quantity, Integer price_int, String status, Date date ){
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.price_int = price_int;
        this.status = status;
        this.date = date;
    }
    public Integer getProductId(){
        return productId;
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
    public String getStatus(){
        return status;
    }

    public Date getDate(){
        return date;
    }
}
