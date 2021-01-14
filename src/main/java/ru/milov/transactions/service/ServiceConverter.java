package ru.milov.transactions.service;

import ru.milov.transactions.service.domain.ServiceUser;
import ru.milov.transactions.service.domain.UserBill;
import ru.milov.transactions.service.domain.UserDto;

public class ServiceConverter {

    public UserDto convertAuth(ServiceUser source){
        UserDto target = new UserDto();

        target.setId(source.getId());
        target.setEmail(source.getEmail());

        return target;
    }

    public UserDto convertRegister(ServiceUser source){
        UserDto target = new UserDto();

        target.setEmail(source.getEmail());
        target.setPassword(source.getPassword());

        return target;
    }

    public UserBill convertAuth(UserBill source){
        UserBill target = new UserBill();

        return target;
    }
}
