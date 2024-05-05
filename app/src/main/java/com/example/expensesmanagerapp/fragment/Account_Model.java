package com.example.expensesmanagerapp.fragment;

//Account Model Class for Selection of Account from the BottomSheetFragment

public class Account_Model {

    //initiating Layout element in java code
    private double accountAmount;
    private String accountName;

    //constructor with none parameter
    public Account_Model() {
    }

    //constructor with parameter
    public Account_Model(double accountAmount, String accountName) {
        this.accountAmount = accountAmount;
        this.accountName = accountName;
    }

    //getters and setters of the layout element
    public double getAccountAmount() {
        return accountAmount;
    }

    public void setAccountAmount(double accountAmount) {
        this.accountAmount = accountAmount;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
}
