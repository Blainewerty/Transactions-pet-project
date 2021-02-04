package ru.milov.transactions.dao.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.milov.transactions.dao.response.ResponseBill;
import ru.milov.transactions.service.entity.Bill;

@Component
public class ConverterBillToBillResponse implements Converter<Bill, ResponseBill> {

    @Override
    public ResponseBill convert(Bill bill) {
        return new ResponseBill()
                .setId(bill.getId())
                .setName(bill.getName())
                .setBalance(bill.getBalance());
    }
}
