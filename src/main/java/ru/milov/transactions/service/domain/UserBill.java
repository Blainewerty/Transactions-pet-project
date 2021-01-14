package ru.milov.transactions.service.domain;

public class UserBill {

    private Integer id;
    private String name;
    private int balance;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "UserBill{" +
                "name='" + name + '\'' +
                ", balance=" + balance +
                '}';
    }
}
