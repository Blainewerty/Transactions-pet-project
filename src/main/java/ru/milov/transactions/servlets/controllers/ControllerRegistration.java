package ru.milov.transactions.servlets.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.milov.transactions.service.entity.UserDto;
import ru.milov.transactions.service.services.ServiceAppUser;
import ru.milov.transactions.servlets.request.RegisterRequest;
import ru.milov.transactions.servlets.responce.RegistrationResponse;
import ru.milov.transactions.view.TypeExceptions;

@Service("/register")
@AllArgsConstructor
public class ControllerRegistration implements Controller <RegisterRequest, UserDto> {

    ServiceAppUser serviceAppUser;
    RegistrationResponse registrationResponse;

    @Override
    public UserDto execute(RegisterRequest request) {
        try {
            UserDto userDto =  serviceAppUser.checkIfUserInDb(request.getEmail(),request.getPassword());
            userDto.setFirstName(request.getFirstName());
            userDto.setLastName(request.getLastName());
            return registrationResponse.registerUser(userDto);
        } catch (TypeExceptions typeExceptions) {
            typeExceptions.printStackTrace();
        }
        return null;
    }

    @Override
    public Class<RegisterRequest> getRequestClass() {
        return RegisterRequest.class;
    }
}
