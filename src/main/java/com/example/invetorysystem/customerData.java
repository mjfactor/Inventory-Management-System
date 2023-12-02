package com.example.invetorysystem;

import java.util.Date;

public class customerData {
    private Integer costumerId;
    private String type;
    private String price;
    private Date date;

    public customerData(Integer costumerId, String type, String price, Date date ){
        this.costumerId = costumerId;
        this.type = type;
        this.price = price;
        this.date = date;
    }
    public Integer getCostumerId(){
        return costumerId;
    }
    public String getType(){
        return type;
    }
    public String getPrice(){
        return price;
    }
    public Date getDate(){
        return date;
    }
}
