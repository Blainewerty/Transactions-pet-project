package ru.milov.transactions.service.services.serviceapp;

import org.junit.Before;
import org.junit.Test;
import ru.milov.transactions.dao.DaoFactory;
import ru.milov.transactions.dao.TransactionDao;
import ru.milov.transactions.dao.UserBillDao;
import ru.milov.transactions.service.domain.Transaction;
import ru.milov.transactions.service.domain.UserBill;
import java.util.LinkedList;
import java.util.List;
import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServiceAppTransactionTest {

    TransactionDao transactionDaoMock;
    TransactionDao transactionDao;
    UserBillDao userBillDao;

    @Before
    public void setUp(){
        System.setProperty("jdbcUrl","jdbc:h2:mem:testDatabase");
        System.setProperty("jdbcUser","user");
        System.setProperty("jdbcPassword","");

        userBillDao = DaoFactory.getUserBillDao();
        transactionDao = DaoFactory.getTransactionDao();
        transactionDaoMock = mock(TransactionDao.class);

    }

    @Test
    public void testStartingOperationWithBill_Ok() {
        Transaction transaction = new Transaction();
        transaction.setUser_id(1);
        transaction.setBill_id(1);
        transaction.setValueOfTransaction(100);

        transactionDao.insert(transaction);
    }

    @Test
    public void testTransferFromBillToBill_Ok() {
        UserBill fromWhichBill = new UserBill();
        UserBill toWhichBill = new UserBill();

        fromWhichBill.setBalance(400);
        fromWhichBill.setUser_id(1);
        fromWhichBill.setBill_id(1);

        toWhichBill.setBalance(0);
        toWhichBill.setUser_id(1);
        toWhichBill.setBill_id(2);

        Transaction transactionFromFirstBill = new Transaction();
        transactionFromFirstBill.setUser_id(fromWhichBill.getUser_id());
        transactionFromFirstBill.setBill_id(fromWhichBill.getBill_id());
        transactionFromFirstBill.setNameOfTransaction("Transfer from " + fromWhichBill.getName() +
                " to " + toWhichBill.getName());
        transactionFromFirstBill.setValueOfTransaction(100);

        fromWhichBill.setBalance(fromWhichBill.getBalance() - 100);

        transactionDao.insert(transactionFromFirstBill);

        userBillDao.update(fromWhichBill);

        Transaction transactionToSecondBill = new Transaction();
        transactionToSecondBill.setUser_id(toWhichBill.getUser_id());
        transactionToSecondBill.setBill_id(toWhichBill.getBill_id());
        transactionToSecondBill.setNameOfTransaction("Transfer to " + toWhichBill.getName() +
                " from " + fromWhichBill.getName());
        transactionToSecondBill.setValueOfTransaction(100);

        toWhichBill.setBalance(toWhichBill.getBalance() + 100);

        userBillDao.update(toWhichBill);

        transactionDao.insert(transactionToSecondBill);

        assertEquals(300,userBillDao.findById(fromWhichBill).getBalance());
        assertEquals(100,userBillDao.findById(toWhichBill).getBalance());
    }

    @Test
    public void testGetInfoAboutBillTransactions_Ok() {
        List<Transaction> transactionList = new LinkedList<>();
        Transaction transaction = new Transaction();

        transaction.setUser_id(1);
        transaction.setBill_id(1);
        transaction.setValueOfTransaction(100);
        transaction.setNameOfTransaction("Someone");
        transactionDao.insert(transaction);

        transactionList.add(transactionDao.findById(transaction));

        transaction.setUser_id(1);
        transaction.setBill_id(1);
        transaction.setValueOfTransaction(300);
        transaction.setNameOfTransaction("Someone");
        transactionDao.insert(transaction);

        transactionList.add(transactionDao.findById(transaction));

        List<Transaction> listFromSql = new LinkedList<>(transactionList);
        when(transactionDaoMock.findByAll(transaction, transactionList)).thenReturn(listFromSql);

        assertEquals(2,listFromSql.size());
    }
}