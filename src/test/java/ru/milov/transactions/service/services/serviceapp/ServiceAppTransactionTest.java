package ru.milov.transactions.service.services.serviceapp;

import org.junit.Before;
import org.junit.Test;
import ru.milov.transactions.dao.TransactionDao;
import ru.milov.transactions.dao.UserBillDao;
import ru.milov.transactions.service.entity.Transaction;
import ru.milov.transactions.service.entity.UserBill;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServiceAppTransactionTest {

    TransactionDao transactionDaoMock;
    TransactionDao transactionDao;
    UserBillDao userBillDao;

    public ServiceAppTransactionTest(TransactionDao transactionDaoMock, TransactionDao transactionDao, UserBillDao userBillDao) {
        this.transactionDaoMock = transactionDaoMock;
        this.transactionDao = transactionDao;
        this.userBillDao = userBillDao;
    }

    @Before
    public void setUp(){
        System.setProperty("jdbcUrl","jdbc:h2:mem:testDatabase");
        System.setProperty("jdbcUser","user");
        System.setProperty("jdbcPassword","");

        transactionDaoMock = mock(TransactionDao.class);

    }

    @Test
    public void testStartingOperationWithBill_Ok() {
        Transaction transaction = new Transaction();
        transaction.setUser_id(1);
        transaction.setBill_id(1);
        transaction.setValueOfTransaction(BigDecimal.valueOf(100));

        transactionDao.insert(transaction);
    }

    @Test
    public void testTransferFromBillToBill_Ok() {
        UserBill fromWhichBill = new UserBill();
        UserBill toWhichBill = new UserBill();

        fromWhichBill.setBalance(BigDecimal.valueOf(400));
        fromWhichBill.setUser_id(1);
        fromWhichBill.setBill_id(1);

        toWhichBill.setBalance(BigDecimal.valueOf(0));
        toWhichBill.setUser_id(1);
        toWhichBill.setBill_id(2);

        Transaction transactionFromFirstBill = new Transaction();
        transactionFromFirstBill.setUser_id(fromWhichBill.getUser_id());
        transactionFromFirstBill.setBill_id(fromWhichBill.getBill_id());
        transactionFromFirstBill.setNameOfTransaction("Transfer from " + fromWhichBill.getName() +
                " to " + toWhichBill.getName());
        transactionFromFirstBill.setValueOfTransaction(BigDecimal.valueOf(100));

        fromWhichBill.setBalance(fromWhichBill.getBalance().subtract(BigDecimal.valueOf(100)));

        transactionDao.insert(transactionFromFirstBill);

        userBillDao.update(fromWhichBill);

        Transaction transactionToSecondBill = new Transaction();
        transactionToSecondBill.setUser_id(toWhichBill.getUser_id());
        transactionToSecondBill.setBill_id(toWhichBill.getBill_id());
        transactionToSecondBill.setNameOfTransaction("Transfer to " + toWhichBill.getName() +
                " from " + fromWhichBill.getName());
        transactionToSecondBill.setValueOfTransaction(BigDecimal.valueOf(100));

        toWhichBill.setBalance(toWhichBill.getBalance().add(BigDecimal.valueOf(100)));

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
        transaction.setValueOfTransaction(BigDecimal.valueOf(100));
        transaction.setNameOfTransaction("Someone");
        transactionDao.insert(transaction);

        transactionList.add(transactionDao.findById(transaction));

        transaction.setUser_id(1);
        transaction.setBill_id(1);
        transaction.setValueOfTransaction(BigDecimal.valueOf(300));
        transaction.setNameOfTransaction("Someone");
        transactionDao.insert(transaction);

        transactionList.add(transactionDao.findById(transaction));

        List<Transaction> listFromSql = new LinkedList<>(transactionList);
        when(transactionDaoMock.findByAll(transaction, transactionList)).thenReturn(listFromSql);

        assertEquals(2,listFromSql.size());
    }
}