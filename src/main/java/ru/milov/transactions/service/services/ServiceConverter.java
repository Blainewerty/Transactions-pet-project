package ru.milov.transactions.service.services;

import ru.milov.transactions.service.domain.ServiceUser;
import ru.milov.transactions.service.domain.UserDto;

public class ServiceConverter {

    public UserDto convertAuth(ServiceUser source){
        UserDto target = new UserDto();

        target.setId(source.getId());
        target.setEmail(source.getEmail());

        return target;
    }

    public UserDto convertRegister(String email, String passwordHash){
        UserDto target = new UserDto();

        target.setEmail(email);
        target.setPassword(passwordHash);

        return target;
    }

}
