package ru.milov.transactions.dao.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.milov.transactions.dao.response.ResponseUserBill;
import ru.milov.transactions.service.entity.UserBill;

@Component
public class ConverterUserBillToUserBillResponse implements Converter<UserBill, ResponseUserBill> {

    @Override
    public ResponseUserBill convert(UserBill userBill) {
        return new ResponseUserBill()
                .setBill_id(userBill.getBill_id())
                .setUserDto(userBill.getUserDto())
                .setName(userBill.getName())
                .setBalance(userBill.getBalance());
    }
}
