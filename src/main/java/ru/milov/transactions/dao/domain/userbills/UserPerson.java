package ru.milov.transactions.dao.domain.userbills;

public class UserPerson extends User{

    private int balance;
    private String nameCategory;
    private int transactions;
    private String nameOfBill = "Person";
    private String date;
    private int TransactionsId;
    private int NameBillId = 1;


    @Override
    public Integer getId() {
        return super.getId();
    }

    @Override
    public String getEmail() {
        return super.getEmail();
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public int getBalance() {
        return balance;
    }

    @Override
    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String getNameCategory() {
        return nameCategory;
    }

    @Override
    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    @Override
    public int getTransactions() {
        return transactions;
    }

    @Override
    public void setTransactions(int transactions) {
        this.transactions = transactions;
    }

    @Override
    public String getNameOfBill() {
        return nameOfBill;
    }

    @Override
    public void setNameOfBill(String nameOfBill) {
        this.nameOfBill = nameOfBill;
    }

    @Override
    public String getDate() {
        return date;
    }

    @Override
    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int getTransactionsId() {
        return TransactionsId;
    }

    @Override
    public void setTransactionsId(int transactionsId) {
        TransactionsId = transactionsId;
    }

    @Override
    public int getNameBillId() {
        return NameBillId;
    }

    @Override
    public void setNameBillId(int nameBillId) {
        NameBillId = nameBillId;
    }

    @Override
    public String toString() {
        return  "Balance " + balance + "\t" +
                nameCategory + '\t' +
                "Transactions " + transactions + "\t" +
                nameOfBill + '\t' +
                date + '\t' +
                "Transaction " + transactions + "\t";
    }
}
