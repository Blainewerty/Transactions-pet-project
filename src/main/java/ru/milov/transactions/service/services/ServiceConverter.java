package ru.milov.transactions.service.services;

import org.springframework.stereotype.Service;
import ru.milov.transactions.service.entity.ServiceUser;
import ru.milov.transactions.service.entity.UserDto;

@Service
public class ServiceConverter {

    public UserDto convertAuth(ServiceUser source){
        UserDto target = new UserDto();

        target.setUser_id(source.getId());
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
