package ru.milov.transactions.service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.milov.transactions.dao.response.ResponseUser;
import ru.milov.transactions.service.entity.User;
import ru.milov.transactions.service.services.ServiceAppUser;

import static org.springframework.http.ResponseEntity.ok;

@Controller
public class ControllerRegistration {

    ServiceAppUser serviceAppUser;

    @PostMapping("/reg")
    @ModelAttribute
    public ResponseEntity<ResponseUser> addUser(@RequestBody User user) {
        ResponseUser responseUser = serviceAppUser.registerUser(user);
        return ok(responseUser);
    }
}