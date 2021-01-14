package ru.milov.transactions.service.domain;

public class Transaction {

    private Integer id;
    private String date;
    private String nameOfTransaction;
    int lastTransaction;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNameOfTransaction() {
        return nameOfTransaction;
    }

    public void setNameOfTransaction(String nameOfTransaction) {
        this.nameOfTransaction = nameOfTransaction;
    }

    public int getLastTransaction() {
        return lastTransaction;
    }

    public void setLastTransaction(int lastTransaction) {
        this.lastTransaction = lastTransaction;
    }

    @Override
    public String toString() {
        return "OperationWithBill{" +
                "date='" + date + '\'' +
                ", nameOfTransaction='" + nameOfTransaction + '\'' +
                ", lastTransaction=" + lastTransaction +
                '}';
    }
}
