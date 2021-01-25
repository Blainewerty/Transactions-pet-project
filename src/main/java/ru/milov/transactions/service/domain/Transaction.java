package ru.milov.transactions.service.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Transaction {

    private int transaction_id;
    private int user_id;
    private int bill_id;
    private String date;
    private String nameOfTransaction;
    private String transactionStatus;
    int valueOfTransaction;

    @Override
    public String toString() {
        return  date + '\t' + nameOfTransaction + '\t' + valueOfTransaction + '\t' + transactionStatus;
    }
}
