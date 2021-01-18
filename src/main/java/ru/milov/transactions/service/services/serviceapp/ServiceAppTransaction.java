package ru.milov.transactions.service.services.serviceapp;


import ru.milov.transactions.service.domain.Transaction;
import ru.milov.transactions.service.domain.UserBill;
import ru.milov.transactions.service.services.ServiceFactory;
import ru.milov.transactions.service.services.servicesql.ServiceSQLBill;
import ru.milov.transactions.service.services.servicesql.ServiceSQLTransaction;
import java.util.LinkedList;
import java.util.List;

public class ServiceAppTransaction {

    private final ServiceSQLBill serviceSqlBill = ServiceFactory.getServiceSqlBill();
    private final ServiceSQLTransaction serviceSqlTransaction = ServiceFactory.getServiceSqlTransaction();

    public void startingOperationWithBill(UserBill userBill, String nameOfTransaction, int valueOfOperation, String command) {

        Transaction transaction = new Transaction();
        transaction.setUser_id(userBill.getUser_id());
        transaction.setBill_id(userBill.getBill_id());
        transaction.setNameOfTransaction(nameOfTransaction);
        transaction.setValueOfTransaction(valueOfOperation);

        if (command.equals("1")) {
            userBill.setBalance(userBill.getBalance() + transaction.getValueOfTransaction());
            serviceSqlTransaction.addOperationOfBillToSQL(transaction);
        }
        if (command.equals("2")) {
            userBill.setBalance(userBill.getBalance() - transaction.getValueOfTransaction());
            serviceSqlTransaction.addOperationOfBillToSQL(transaction);
        }
        updateUserBill(userBill);
    }

    public void transferFromBillToBill(List<UserBill> billList, int fromBill, int toBill, int valueOfTransaction) {

        UserBill fromWhichBill = billList.get(fromBill);
        UserBill toWhichBill = billList.get(toBill);

        Transaction transactionFromFirstBill = new Transaction();
        transactionFromFirstBill.setUser_id(fromWhichBill.getUser_id());
        transactionFromFirstBill.setBill_id(fromWhichBill.getBill_id());
        transactionFromFirstBill.setNameOfTransaction("Transfer from " + fromWhichBill.getName() +
                " to " + toWhichBill.getName());
        transactionFromFirstBill.setValueOfTransaction(valueOfTransaction);

        fromWhichBill.setBalance(fromWhichBill.getBalance() - valueOfTransaction);
        updateUserBill(fromWhichBill);

        serviceSqlTransaction.addOperationOfBillToSQL(transactionFromFirstBill);

        Transaction transactionToSecondBill = new Transaction();
        transactionToSecondBill.setUser_id(toWhichBill.getUser_id());
        transactionToSecondBill.setBill_id(toWhichBill.getBill_id());
        transactionToSecondBill.setNameOfTransaction("Transfer to " + toWhichBill.getName() +
                " from " + fromWhichBill.getName());
        transactionToSecondBill.setValueOfTransaction(valueOfTransaction);

        toWhichBill.setBalance(toWhichBill.getBalance() + valueOfTransaction);
        updateUserBill(toWhichBill);

        serviceSqlTransaction.addOperationOfBillToSQL(transactionToSecondBill);
    }

    public List getInfoAboutBillTransactions(UserBill userBill){
        List<Transaction> transactionList = new LinkedList<>();
        Transaction transaction = new Transaction();
        transaction.setUser_id(userBill.getUser_id());
        transaction.setBill_id(userBill.getBill_id());
         return serviceSqlTransaction.getInfoAboutAllTransactions(transaction, transactionList);
    }

    public void updateUserBill(UserBill userBill){
        serviceSqlBill.updateBillInfo(userBill);
    }

}
