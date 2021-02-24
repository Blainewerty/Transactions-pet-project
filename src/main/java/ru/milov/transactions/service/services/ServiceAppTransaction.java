package ru.milov.transactions.service.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.milov.transactions.converter.ConverterTransactionToTransactionResponse;
import ru.milov.transactions.repository.RepositoryTransaction;
import ru.milov.transactions.response.ResponseTransaction;
import ru.milov.transactions.service.entity.Bill;
import ru.milov.transactions.service.entity.Transaction;
import ru.milov.transactions.service.entity.User;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class ServiceAppTransaction {

    private final ServiceAppBill serviceAppBill;
    private final RepositoryTransaction repositoryTransaction;
    private final ConverterTransactionToTransactionResponse converter;

    @Transactional
    public ResponseTransaction startingOperationWithBill(Bill bill, Transaction transaction, String command) {

        transaction.setUser(bill.getUser());
        transaction.setBill(bill);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        transaction.setDate(timestamp);

        if (command.equals("add")) {
            bill.setBalance(bill.getBalance().add(transaction.getValueOfTransaction()));
        }
        if (command.equals("sub")) {
            bill.setBalance(bill.getBalance().subtract(transaction.getValueOfTransaction()));
        }
        updateBill(bill);

        return createTransaction(transaction);
    }

    public List<ResponseTransaction> transferFromBillToBill(List<Bill> billList, String fromBill, String toBill, BigDecimal valueOfTransaction) {

        List<ResponseTransaction> responseList = new ArrayList<>();

        Optional<Bill> matchingFromObject =  billList.stream().filter(i -> i.getName().equals(fromBill)).findFirst();

        Bill fromWhichBill = matchingFromObject.get();

        Optional<Bill> matchingToObject = billList.stream().filter(i -> i.getName().equals(toBill)).findFirst();

        Bill toWhichBill = matchingToObject.get();

        Transaction transactionFromFirstBill = new Transaction();
        transactionFromFirstBill.setUser(fromWhichBill.getUser());
        transactionFromFirstBill.setBill(fromWhichBill);
        transactionFromFirstBill.setNameOfTransaction("Transfer from " + fromWhichBill.getName() +
                " to " + toWhichBill.getName());
        transactionFromFirstBill.setValueOfTransaction(valueOfTransaction);

        responseList.add(startingOperationWithBill(fromWhichBill, transactionFromFirstBill, "sub"));

        Transaction transactionToSecondBill = new Transaction();
        transactionToSecondBill.setUser(toWhichBill.getUser());
        transactionToSecondBill.setBill(toWhichBill);
        transactionToSecondBill.setNameOfTransaction("Transfer to " + toWhichBill.getName() +
                " from " + fromWhichBill.getName());
        transactionToSecondBill.setValueOfTransaction(valueOfTransaction);

        responseList.add(startingOperationWithBill(toWhichBill, transactionToSecondBill, "add"));

        return responseList;
    }

    @Transactional(readOnly = true)
    public List<ResponseTransaction> getInfoAboutAllUsersTransactions(User user) {
        return repositoryTransaction.findByUser(user)
                .stream().map(converter::convert)
                .collect(toList());
    }

    public void updateBill(Bill bill) {
        serviceAppBill.updateBill(bill);
    }

    public ResponseTransaction createTransaction(Transaction transaction) {
        return converter.convert(repositoryTransaction.save(transaction));
    }

    @Transactional(readOnly = true)
    public List<ResponseTransaction> getInfoAboutBillTransactions(User user, String nameOfBill){
        Optional<Bill> manageObj = user.getBill().stream().filter(i -> i.getName().equals(nameOfBill)).findAny();
        Bill bill = manageObj.get();
        return repositoryTransaction.findByUserAndBill(user, bill)
                .stream().map(converter::convert)
                .collect(Collectors.toList());
    }
}
