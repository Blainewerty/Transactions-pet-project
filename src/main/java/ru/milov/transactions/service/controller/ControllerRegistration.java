package ru.milov.transactions.service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.milov.transactions.service.entity.User;
import ru.milov.transactions.service.services.ServiceAppUser;

@Controller
public class ControllerRegistration {

    ServiceAppUser serviceAppUser;

//    @GetMapping("/reg")
//    public String registration(@ModelAttribute("user") User userForm) {
//        System.out.println(userForm);
//        return "reg";
//    }

    @PostMapping("/reg")
    @ModelAttribute
    public String addUser(Model model) {
        User user = new User();
        user.setEmail("jmb12");
        model.addAttribute("user", user);
        System.out.println(model);
//        serviceAppUser.registerUser(userForm);
        return "redirect:/hello";
    }
}