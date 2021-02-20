package ru.milov.transactions.service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ControllerMain {

    @GetMapping("/")
    public String hello(){
        return "redirect:/bill/billList";
    }

}