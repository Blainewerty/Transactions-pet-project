package ru.milov.transactions.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.milov.transactions.response.ResponseUser;
import ru.milov.transactions.service.entity.User;
import ru.milov.transactions.service.services.ServiceAppUser;

import static org.springframework.http.ResponseEntity.ok;

@RestController
public class ControllerUser {

    private final ServiceAppUser serviceAppUser;

    @Autowired
    public ControllerUser(ServiceAppUser serviceAppUser) {
        this.serviceAppUser = serviceAppUser;
    }

    @GetMapping("/hello")
    public ResponseEntity<ResponseUser> hello(@RequestBody User user){
        return ok(serviceAppUser.authInApp(user));
    }

}