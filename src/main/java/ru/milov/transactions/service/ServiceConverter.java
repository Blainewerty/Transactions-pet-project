package ru.milov.transactions.service;

import ru.milov.transactions.service.domain.ServiceUser;
import ru.milov.transactions.service.domain.UserBill;
import ru.milov.transactions.service.domain.UserDto;

public class ServiceConverter {

    public UserDto convert(ServiceUser source){
        UserDto target = new UserDto();

        target.setId(source.getId());
        target.setEmail(source.getEmail());

        return target;
    }

    public UserBill convert (UserBill source){
        UserBill target = new UserBill();

        return target;
    }
}
