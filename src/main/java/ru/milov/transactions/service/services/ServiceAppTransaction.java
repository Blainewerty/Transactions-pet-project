package ru.milov.transactions.service.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.milov.transactions.dao.Dao;
import ru.milov.transactions.service.domain.Transaction;
import ru.milov.transactions.service.domain.UserBill;
import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceAppTransaction {

    private final DataSource dataSource;
    private final Dao<Transaction, Integer> transactionDao;
    private final ServiceAppBill serviceAppBill;

    public void startingOperationWithBill(UserBill userBill, String nameOfTransaction, BigDecimal valueOfOperation, String command) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            Transaction transaction = new Transaction();
            transaction.setUser_id(userBill.getUser_id());
            transaction.setBill_id(userBill.getBill_id());
            transaction.setNameOfTransaction(nameOfTransaction);
            transaction.setValueOfTransaction(valueOfOperation);

            if (command.equals("1")) {
                userBill.setBalance(userBill.getBalance().add(transaction.getValueOfTransaction()));
                transaction.setTransactionStatus("+");
                transactionDao.insert(transaction, connection);
            }
            if (command.equals("2")) {
                userBill.setBalance(userBill.getBalance().subtract(transaction.getValueOfTransaction()));
                transaction.setTransactionStatus("-");
                transactionDao.insert(transaction, connection);
            }
            updateUserBill(userBill);

            connection.commit();
        } catch (SQLException ignored) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    public void transferFromBillToBill(List<UserBill> billList, int fromBill, int toBill, BigDecimal valueOfTransaction) {

        UserBill fromWhichBill = billList.get(fromBill);
        UserBill toWhichBill = billList.get(toBill);

        Transaction transactionFromFirstBill = new Transaction();
        transactionFromFirstBill.setUser_id(fromWhichBill.getUser_id());
        transactionFromFirstBill.setBill_id(fromWhichBill.getBill_id());
        transactionFromFirstBill.setNameOfTransaction("Transfer from " + fromWhichBill.getName() +
                " to " + toWhichBill.getName());
        transactionFromFirstBill.setValueOfTransaction(valueOfTransaction);
        transactionFromFirstBill.setTransactionStatus("-");

        startingOperationWithBill(fromWhichBill, transactionFromFirstBill.getNameOfTransaction()
                , transactionFromFirstBill.getValueOfTransaction(), "2");

        Transaction transactionToSecondBill = new Transaction();
        transactionToSecondBill.setUser_id(toWhichBill.getUser_id());
        transactionToSecondBill.setBill_id(toWhichBill.getBill_id());
        transactionToSecondBill.setNameOfTransaction("Transfer to " + toWhichBill.getName() +
                " from " + fromWhichBill.getName());
        transactionToSecondBill.setValueOfTransaction(valueOfTransaction);
        transactionFromFirstBill.setTransactionStatus("+");

        startingOperationWithBill(toWhichBill, transactionToSecondBill.getNameOfTransaction()
                , transactionToSecondBill.getValueOfTransaction(), "1");
    }

    public List getInfoAboutBillTransactions(UserBill userBill) {
        List<Transaction> transactionList = new LinkedList<>();
        Transaction transaction = new Transaction();
        transaction.setUser_id(userBill.getUser_id());
        transaction.setBill_id(userBill.getBill_id());
        return transactionDao.findByAll(transaction, transactionList);
    }

    public void updateUserBill(UserBill userBill) {
        serviceAppBill.updateUserBill(userBill);
    }

}
