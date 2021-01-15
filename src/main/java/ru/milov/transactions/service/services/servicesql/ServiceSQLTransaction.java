package ru.milov.transactions.service.services.servicesql;

import ru.milov.transactions.service.domain.Transaction;
import java.util.List;
import static ru.milov.transactions.dao.DaoFactory.getTransactionDao;

public class ServiceSQLTransaction {

    public void addOperationOfBillToSQL(Transaction transaction){
        getTransactionDao().insert(transaction);
    }

    public List getInfoAboutAllTransactions(Transaction transaction, List transactionList){
        return getTransactionDao().findByAll(transaction, transactionList);
    }

}
