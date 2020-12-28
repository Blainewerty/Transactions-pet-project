package ru.milov.transactions.dao.domain.userbills;

public class User {

    private Integer id;
    private String email;
    private String password;
    private int balance;
    private String nameCategory;
    private int transactions;
    private String nameOfBill;
    private String date;
    private int TransactionsId;
    private int NameBillId;

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTransactionsId() {
        return TransactionsId;
    }

    public void setTransactionsId(int transactionsId) {
        TransactionsId = transactionsId;
    }

    public int getNameBillId() {
        return NameBillId;
    }

    public void setNameBillId(int nameBillId) {
        NameBillId = nameBillId;
    }

    @Override
    public String toString() {
        return  "Email " + email + "\t" +
                "Balance " + balance + "\t" +
                "Name Category " + nameCategory + "\t" +
                "Transactions " + transactions + "\t" +
                "Name Of Bill " + nameOfBill + "\t" +
                "Date " + date;
    }
}
