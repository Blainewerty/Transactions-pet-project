package ru.milov.transactions.dao.domain;

public class UserBills {

    private int id;
    private int balance;
    private String nameCategory;
    private int transactions;
    private String nameOfBill;
    private String  date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public int getTransactions() {
        return transactions;
    }

    public void setTransactions(int transactions) {
        this.transactions = transactions;
    }

    public String getNameOfBill() {
        return nameOfBill;
    }

    public void setNameOfBill(String nameOfBill) {
        this.nameOfBill = nameOfBill;
    }

    @Override
    public String toString() {
        return "UserBills{" +
                "id=" + id +
                ", balance=" + balance +
                ", nameCategory='" + nameCategory + '\'' +
                ", transactions=" + transactions +
                ", nameOfBill='" + nameOfBill + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
