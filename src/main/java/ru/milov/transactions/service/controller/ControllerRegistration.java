package ru.milov.transactions.service.controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.milov.transactions.response.ResponseUser;
import ru.milov.transactions.service.entity.User;
import ru.milov.transactions.service.services.ServiceAppUser;

@RestController
public class ControllerRegistration {

    ServiceAppUser serviceAppUser;

    @PostMapping("/registration")
    @ModelAttribute
    public String addUser(User user) {
        ResponseUser responseUser = serviceAppUser.registerUser(user);
        return "redirect:/bill";
    }
}