package ru.milov.transactions.service.sqlactions;

import ru.milov.transactions.service.domain.Transaction;

import static ru.milov.transactions.dao.DaoFactory.getTransactionDao;

public class SQLActionsTransaction {

    public void transactionFromBillToBill(Transaction transaction){
        getTransactionDao().insert(transaction);
    }

    public void addOperationOfBillToSQL (Transaction transaction){

        getTransactionDao().insert(transaction);
    }
}
