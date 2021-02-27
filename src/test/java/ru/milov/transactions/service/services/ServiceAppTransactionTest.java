package ru.milov.transactions.service.services;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ru.milov.transactions.converter.ConverterTransactionToTransactionResponse;
import ru.milov.transactions.repository.RepositoryBill;
import ru.milov.transactions.repository.RepositoryTransaction;
import ru.milov.transactions.repository.RepositoryUser;
import ru.milov.transactions.service.entity.Bill;
import ru.milov.transactions.service.entity.Transaction;
import ru.milov.transactions.service.entity.User;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@DataJpaTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class ServiceAppTransactionTest {

    @Autowired
    private RepositoryBill repositoryBill;

    @Autowired
    private RepositoryUser repositoryUser;

    @Autowired
    private RepositoryTransaction repositoryTransaction;

    @Mock
    private ConverterTransactionToTransactionResponse mockConverter;

    private ServiceAppTransaction serviceAppTransactionUnderTest;

    @BeforeEach
    public void setUp() {
//        serviceAppTransactionUnderTest = new ServiceAppTransaction(mockServiceAppBill, mockRepositoryTransaction, mockConverter);
    }

    @Test
    public void testStartingOperationWithBill() {

        User user = new User().setEmail("email")
                .setLastName("test")
                .setFirstName("test")
                .setPassword("pass");

        Bill bill = new Bill()
                .setBalance(new BigDecimal("0.00"))
                .setUser(user)
                .setName("test");

        repositoryUser.save(user);

        repositoryBill.save(bill);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        Transaction transaction = new Transaction().setBill(bill)
                .setValueOfTransaction(new BigDecimal("12.00"))
                .setDate(timestamp)
                .setNameOfTransaction("test")
                .setUser(user);

        bill.setBalance(bill.getBalance().add(transaction.getValueOfTransaction()));

        repositoryTransaction.save(transaction);

        repositoryBill.save(bill);

        assertEquals(transaction, repositoryTransaction.findByUserAndBill(user,bill).get(0));

        assertEquals(repositoryTransaction.findByUserAndBill(user,bill).get(0).getValueOfTransaction(),
                repositoryBill.findByUserAndName(user,"test").getBalance());
    }

    @Test
    public void testStartingOperationWithBill_ConverterTransactionToTransactionResponseReturnsNull() {

    }

    @Test
    public void testTransferFromBillToBill() {

    }

    @Test
    public void testTransferFromBillToBill_ConverterTransactionToTransactionResponseReturnsNull() {

    }

    @Test
    public void testGetInfoAboutAllUsersTransactions() {
        User user = new User().setEmail("email")
                .setLastName("test")
                .setFirstName("test")
                .setPassword("pass");

        Bill bill = new Bill()
                .setBalance(new BigDecimal("0.00"))
                .setUser(user)
                .setName("test");

        repositoryUser.save(user);

        repositoryBill.save(bill);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        Transaction transaction = new Transaction().setBill(bill)
                .setValueOfTransaction(new BigDecimal("12.00"))
                .setDate(timestamp)
                .setNameOfTransaction("test")
                .setUser(user);

        bill.setBalance(bill.getBalance().add(transaction.getValueOfTransaction()));

        repositoryTransaction.save(transaction);

        repositoryTransaction.findByUser(user);

        assertEquals(1,repositoryTransaction.findByUser(user).size());
    }

    @Test
    public void testGetInfoAboutAllUsersTransactions_RepositoryTransactionReturnsNoItems() {
    }

    @Test
    public void testGetInfoAboutAllUsersTransactions_ConverterTransactionToTransactionResponseReturnsNull() {

    }

    @Test
    public void testCreateTransaction_ConverterTransactionToTransactionResponseReturnsNull() {

    }

    @Test
    public void testGetInfoAboutBillTransactions() {

    }

    @Test
    public void testGetInfoAboutBillTransactions_RepositoryTransactionReturnsNoItems() {

    }

    @Test
    public void testGetInfoAboutBillTransactions_ConverterTransactionToTransactionResponseReturnsNull() {

    }
}
