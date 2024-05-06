package com.example.expensesmanagerapp.fragment;

import java.util.Date;

//Transaction_Model act as a Model Class for transaction
public class Transaction_Model {

    //all the string type of transaction
    private String type,category,account,note;
//    date of transaction
    private Date date;
    //amount of transaction
    private double amount;

    //Unique id of each transaction
    private long id;

    //empty constructor
    public Transaction_Model() {
    }

    //    Constructor with all parameter of initiated variable
    public Transaction_Model(String type, String category, String account, String note, Date date, double amount, long id) {
        this.type = type;
        this.category = category;
        this.account = account;
        this.note = note;
        this.date = date;
        this.amount = amount;
        this.id = id;
    }

    //getters and setters of all initiated variable
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
