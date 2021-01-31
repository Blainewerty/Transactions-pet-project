package ru.milov.transactions.service.entity;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class Transaction {

    private int transaction_id;
    private int user_id;
    private int bill_id;
    private String date;
    private String nameOfTransaction;
    private String transactionStatus;
    BigDecimal valueOfTransaction;

    @Override
    public String toString() {
        return  date + '\t' + nameOfTransaction + '\t' + valueOfTransaction + '\t' + transactionStatus;
    }
}