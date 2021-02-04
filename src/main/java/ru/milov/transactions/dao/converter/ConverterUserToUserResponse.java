package ru.milov.transactions.dao.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.milov.transactions.dao.response.ResponseUser;
import ru.milov.transactions.service.entity.User;

@Component
public class ConverterUserToUserResponse implements Converter<User, ResponseUser> {

    @Override
    public ResponseUser convert(User user) {
        return new ResponseUser()
                .setId(user.getId())
                .setEmail(user.getEmail())
                .setPassword(user.getPassword())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setBill(user.getBill());
    }
}
