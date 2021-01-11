package ru.milov.transactions.service;

import ru.milov.transactions.domain.ServiceUser;
import ru.milov.transactions.domain.UserDto;

public class ServiceConverter {

    public UserDto convert(ServiceUser source){
        UserDto target = new UserDto();

        target.setId(source.getId());
        target.setEmail(source.getEmail());

        return target;
    }
}
