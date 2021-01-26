package ru.milov.transactions.servlets.controllers;

import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import ru.milov.transactions.service.domain.UserBill;
import ru.milov.transactions.service.domain.UserDto;
import ru.milov.transactions.service.services.ServiceAppUser;
import ru.milov.transactions.service.services.ServiceConfiguration;
import ru.milov.transactions.servlets.request.LoginRequest;
import ru.milov.transactions.servlets.responce.LoginResponse;
import ru.milov.transactions.view.TypeExceptions;

import java.util.List;

@Service("/login")
@AllArgsConstructor
public class ControllerLogin implements Controller<LoginRequest, List<UserBill>> {

    LoginResponse loginResponse;
    ServiceAppUser serviceAppUser;

    @Override
    public List<UserBill> execute(LoginRequest request) {
        try {
            UserDto userDto = serviceAppUser.authInApp(request.getEmail(), request.getPassword());
            if (userDto != null) {
                return loginResponse.showUserBills(userDto);
            }
        } catch (TypeExceptions typeExceptions) {
            typeExceptions.printStackTrace();
        }return null;
    }

    @Override
    public Class<LoginRequest> getRequestClass() {
        return LoginRequest.class;
    }
}
