package ru.milov.transactions.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.milov.transactions.response.ResponseUser;
import ru.milov.transactions.service.entity.User;

@Component
public class ConverterUserToUserResponse implements Converter<User, ResponseUser> {

    @Override
    public ResponseUser convert(User user) {
        return new ResponseUser()
                .setEmail(user.getEmail())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setBill(user.getBill());
    }
}
