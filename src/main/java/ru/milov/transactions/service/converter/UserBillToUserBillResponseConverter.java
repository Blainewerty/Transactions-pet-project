package ru.milov.transactions.service.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.milov.transactions.response.UserBillResponse;
import ru.milov.transactions.service.entity.UserBill;

@Component
public class UserBillToUserBillResponseConverter implements Converter<UserBill, UserBillResponse> {

    @Override
    public UserBillResponse convert(UserBill userBill) {
        return new UserBillResponse().setBill_id(userBill.getBill_id())
                .setUser_id(userBill.getUser_id())
                .setName(userBill.getName())
                .setBalance(userBill.getBalance());
    }
}
