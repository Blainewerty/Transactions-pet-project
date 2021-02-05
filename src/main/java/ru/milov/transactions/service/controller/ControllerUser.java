package ru.milov.transactions.service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.milov.transactions.service.services.ServiceAppBill;
import ru.milov.transactions.service.services.ServiceAppUser;

@RestController
@RequiredArgsConstructor
public class ControllerUser {

    @Autowired()
    ServiceAppUser serviceAppUser;
    @Autowired
    ServiceAppBill serviceAppBill;

    @GetMapping("/hello")
    public String hello(){
        return "Hello";
    }

}