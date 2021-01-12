package ru.milov.transactions.domain;

import java.sql.Timestamp;

public class UserDto {

    private Integer id;
    private String email;
    private String password;
    private int balance;
    private String transactionCategory;
    private String nameOfBill;
    private int nameOfBillId;
    private String date;
    private int lastTransaction;
    private int transactionsId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getNameCategory() {
        return transactionCategory;
    }

    public void setNameCategory(String transactionCategory) {
        this.transactionCategory = transactionCategory;
    }

    public String getNameOfBill() {
        return nameOfBill;
    }

    public void setNameOfBill(String nameOfBill) {
        this.nameOfBill = nameOfBill;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getLastTransaction() {
        return lastTransaction;
    }

    public void setLastTransaction(int lastTransaction) {
        this.lastTransaction = lastTransaction;
    }

    @Override
    public String toString() {
        return  "Email= " + email + "\t" +
                "Balance= " + balance + "\t" +
                "Name Category= " + transactionCategory + "\t" +
                "Name Of Bill= " + nameOfBill + "\t" +
                "Date= " + date + "\t" +
                "Last Transaction= " + lastTransaction;
    }

    private void setTransactionId(){
        switch (getNameCategory()){
            case "Salary":
                transactionsId = 1;
                break;
            case "Funnies":
                transactionsId = 2;
                break;
            case "Health":
                transactionsId = 3;
                break;
            case "Automobile":
                transactionsId = 4;
        }
    }

    public int getTransactionsId() {
        setTransactionId();
        return transactionsId;
    }

    public int getNameOfBillId() {
        setNameOfBillId();
        return nameOfBillId;
    }

    private void setNameOfBillId() {
        switch (getNameOfBill()){
            case "Person":
                nameOfBillId = 1;
                break;
            case "Work":
                nameOfBillId = 2;
                break;
            case "Saving":
                nameOfBillId = 3;
                break;
        }
    }
}
