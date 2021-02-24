package ru.milov.transactions.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.milov.transactions.response.ResponseTransaction;
import ru.milov.transactions.service.entity.Transaction;

@Component
public class ConverterTransactionToTransactionResponse implements Converter<Transaction, ResponseTransaction> {
    @Override
    public ResponseTransaction convert(Transaction transaction) {
        return new ResponseTransaction()
                .setTransaction_id(transaction.getId())
                .setDate(transaction.getDate())
                .setBill(transaction.getBill())
                .setNameOfTransaction(transaction.getNameOfTransaction())
                .setValueOfTransaction(transaction.getValueOfTransaction());
    }
}
