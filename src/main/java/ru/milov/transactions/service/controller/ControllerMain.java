package ru.milov.transactions.service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class ControllerMain {

    @GetMapping("/")
    public String index(Principal principal)
    {
        if(principal != null)
        {
            return "redirect:/bill/billList";
        }
        return "index";
    }
}