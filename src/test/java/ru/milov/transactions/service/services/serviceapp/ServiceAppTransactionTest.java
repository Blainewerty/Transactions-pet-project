package ru.milov.transactions.service.services.serviceapp;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ru.milov.transactions.dao.TransactionDao;
import ru.milov.transactions.service.domain.Transaction;
import ru.milov.transactions.service.domain.UserBill;
import ru.milov.transactions.service.services.servicesql.ServiceSQLTransaction;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ServiceAppTransactionTest extends TestCase {

    @Mock
    TransactionDao transactionDao;

    @Mock
    ServiceSQLTransaction serviceSQLTransaction;

    @Test
    public void testStartingOperationWithBill() {
        Transaction transaction = new Transaction();
        transaction.setUser_id(1);
        transaction.setBill_id(1);

        Transaction transactionInserted = new Transaction();

        transactionInserted.setTransaction_id(2);

        when(transactionDao.insert(transaction)).thenReturn(transactionInserted);

        int id = transactionInserted.getTransaction_id();

        assertEquals(2,id);
    }

    @Test
    public void testTransferFromBillToBill() {
        UserBill fromWhichBill = new UserBill();
        UserBill toWhichBill = new UserBill();

        fromWhichBill.setBalance(100);
        toWhichBill.setBalance(0);

        Transaction transactionFromFirstBill = new Transaction();
        transactionFromFirstBill.setValueOfTransaction(100);

        Transaction transactionFromFirstBillReturn = new Transaction();
        transactionFromFirstBillReturn.setValueOfTransaction(0);

        when(transactionDao.insert(transactionFromFirstBill)).thenReturn(transactionFromFirstBillReturn);

        Transaction transactionFromSecondBill = new Transaction();
        transactionFromSecondBill.setValueOfTransaction(0);

        Transaction transactionFromSecondBillReturn = new Transaction();
        transactionFromSecondBillReturn.setValueOfTransaction(100);

        when(transactionDao.insert(transactionFromSecondBill)).thenReturn(transactionFromSecondBillReturn);

        assertEquals(transactionFromFirstBill.getValueOfTransaction(),transactionFromSecondBillReturn.getValueOfTransaction());

    }

    @Test
    public void testGetInfoAboutBillTransactions() {
        List<Transaction> transactionList = new LinkedList<>();
        List<Transaction> transactionListReturned = new LinkedList<>();

        Transaction transaction = new Transaction();
        transactionListReturned.add(transaction);

        when(serviceSQLTransaction.getInfoAboutAllTransactions(transaction,transactionList)).thenReturn(transactionListReturned);

        assertNotNull(transactionList);
    }
}