package ru.milov.transactions.service.domain;

public class UserBill {

    String name;
    int balance;

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
