package ru.milov.transactions.service.domain;

public class Transaction {

    private int transaction_id;
    private int user_id;
    private int bill_id;
    private String date;
    private String nameOfTransaction;
    private String transactionStatus;
    int valueOfTransaction;

    public int getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(int transaction_id) {
        this.transaction_id = transaction_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Integer getBill_id() {
        return bill_id;
    }

    public void setBill_id(int bill_id) {
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

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    @Override
    public String toString() {
        return  date + '\t' + nameOfTransaction + '\t' + valueOfTransaction + '\t' + transactionStatus;
    }
}
