package ru.milov.transactions.service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.milov.transactions.service.entity.User;
import ru.milov.transactions.service.services.ServiceAppUser;

@Controller
public class ControllerRegistration {

    private final ServiceAppUser serviceAppUser;

    public ControllerRegistration(ServiceAppUser serviceAppUser) {
        this.serviceAppUser = serviceAppUser;
    }

    @PostMapping("/registration")
    public String addUser(@RequestBody User user) {

        serviceAppUser.registerUser(user);

        return "redirect:/";
    }
}