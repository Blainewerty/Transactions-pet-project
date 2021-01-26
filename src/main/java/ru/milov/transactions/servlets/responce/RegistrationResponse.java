package ru.milov.transactions.servlets.responce;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.milov.transactions.service.domain.UserDto;
import ru.milov.transactions.service.services.ServiceAppUser;

@Service
@AllArgsConstructor
public class RegistrationResponse {

    private final ServiceAppUser serviceAppUser;

    public UserDto registerUser(UserDto userDto) {
        return serviceAppUser.registerUser(userDto);
    }
}