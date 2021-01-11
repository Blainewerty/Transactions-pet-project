package ru.milov.transactions.domain;

public class UserDto {

    private Integer id;
    private String email;
    private String password;
    private int balance;
    private String transactionCategory;
    private String nameOfBill;
    private String date;
    private int lastTransaction;

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
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", balance=" + balance +
                ", nameCategory='" + transactionCategory + '\'' +
                ", nameOfBill='" + nameOfBill + '\'' +
                ", date='" + date + '\'' +
                ", lastTransaction=" + lastTransaction +
                '}';
    }
}
