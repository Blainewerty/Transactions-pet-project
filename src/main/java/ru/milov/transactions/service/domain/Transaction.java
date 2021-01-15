package ru.milov.transactions.service.domain;

public class Transaction {

    private Integer transaction_id;
    private Integer user_id;
    private Integer bill_id;
    private String date;
    private String nameOfTransaction;
    int valueOfTransaction;

    public Integer getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(Integer transaction_id) {
        this.transaction_id = transaction_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getBill_id() {
        return bill_id;
    }

    public void setBill_id(Integer bill_id) {
        this.bill_id = bill_id;
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

    public int getValueOfTransaction() {
        return valueOfTransaction;
    }

    public void setValueOfTransaction(int valueOfTransaction) {
        this.valueOfTransaction = valueOfTransaction;
    }

    @Override
    public String toString() {
        return "OperationWithBill{" +
                "date='" + date + '\'' +
                ", nameOfTransaction='" + nameOfTransaction + '\'' +
                ", lastTransaction=" + valueOfTransaction +
                '}';
    }
}
