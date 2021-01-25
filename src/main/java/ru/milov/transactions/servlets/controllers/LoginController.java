package ru.milov.transactions.servlets.controllers;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ru.milov.transactions.servlets.Controller;
import ru.milov.transactions.servlets.request.LoginRequest;
import ru.milov.transactions.servlets.responce.LoginResponse;

@Service
@Profile("/login")
public class LoginController implements Controller<LoginRequest, LoginResponse> {
    @Override
    public LoginResponse execute(LoginRequest request) {
        if (("jb@mail.ru").equals(request.getEmail())){
            return new LoginResponse(true);
        }
        return new LoginResponse(false);
    }
}
