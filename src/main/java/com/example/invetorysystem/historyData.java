package com.example.invetorysystem;

import java.util.Date;

public class historyData {
    private String transaction_id;
    private String customer_name;
    private String total;
    private String paid;
    private String change_string;
    private String balance;
    private Integer total_int;
    private Integer balance_int;
    private Integer change_int;
    private Date date;
    private Integer year_int;
    private String month_string;

    public historyData(String transaction_id, String customer_name, String total, String paid, String change_string, String balance, Integer total_int, Integer balance_int, Integer change_int, Date date, Integer year_int, String month_string){
        this.transaction_id = transaction_id;
        this.customer_name = customer_name;
        this.total = total;
        this.paid = paid;
        this.change_string = change_string;
        this.balance = balance;
        this.total_int = total_int;
        this.balance_int = balance_int;
        this.change_int = change_int;
        this.date = date;
        this.year_int = year_int;
        this.month_string = month_string;
    }
    public String getTransaction_id(){
        return transaction_id;
    }
    public String getCustomer_name(){
        return customer_name;
    }
    public String getTotal(){
        return total;
    }
    public String getPaid(){
        return paid;
    }
    public String getChange_string(){
        return change_string;
    }
    public String getBalance(){
        return balance;
    }
    public Integer getTotal_int(){
        return total_int;
    }
    public Integer getBalance_int(){
        return balance_int;
    }
    public Integer getChange_int(){
        return change_int;
    }
    public Date getDate(){
        return date;
    }
    public Integer getYear(){
        return year_int;
    }
    public String getMonth(){
        return month_string;
    }
}
