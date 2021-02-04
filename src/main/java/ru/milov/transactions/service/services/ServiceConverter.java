package ru.milov.transactions.service.services;

import org.springframework.stereotype.Service;
import ru.milov.transactions.dao.response.ResponseUser;
import ru.milov.transactions.service.entity.User;

@Service
public class ServiceConverter {

    public User convertAuth(ResponseUser source){
        User target = new User();

        target.setId(source.getId());
        target.setEmail(source.getEmail());

        return target;
    }

    public User convertRegister(String email, String passwordHash){
        User target = new User();

        target.setEmail(email);
        target.setPassword(passwordHash);

        return target;
    }

}
