package ru.milov.transactions.service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.milov.transactions.dao.response.ResponseUser;
import ru.milov.transactions.service.TypeExceptions;
import ru.milov.transactions.service.entity.User;
import ru.milov.transactions.service.services.ServiceAppBill;
import ru.milov.transactions.service.services.ServiceAppUser;

import java.util.Optional;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.web.servlet.function.ServerResponse.notFound;

@RestController
@RequiredArgsConstructor
public class ControllerUser {

    @Autowired()
    ServiceAppUser serviceAppUser;
    @Autowired
    ServiceAppBill serviceAppBill;

    @GetMapping("/hello")
    public String hello(@ModelAttribute("user") ResponseUser user){
        System.out.println(user);
        return "Hello";
    }

//    @GetMapping("/")
//    public ResponseEntity<ResponseUser> showUser (@ModelAttribute("user") ResponseUser user) throws TypeExceptions {
//        return ok(user);
//    }

    @PostMapping("/login")
    @ModelAttribute
    public ResponseEntity<ResponseUser> findUserDto(@RequestBody User requestUser, Model model) throws TypeExceptions {
        ResponseUser user = serviceAppUser.authInApp(requestUser);
        if (user == null) {
            notFound().build();
        }
        model.addAttribute("user",user);
        System.out.println(model);
        return ok(user);
    }

//    @ModelAttribute
//    public void authUser (ResponseUser responseUser, Model model){
//        model.addAttribute("user", responseUser);
//    }


//    @GetMapping("/login")
//    public RedirectView  findUserDto(@RequestBody User requestUser, Model model) throws TypeExceptions {
//        ResponseUser user = serviceAppUser.authInApp(requestUser);
//        if (user == null) {
//            notFound().build();
//        }
//        RedirectView rv = new RedirectView();
//        model.addAttribute("user",user);
//        rv.setUrl("hello");
//        return rv;
//    }

//    @PostMapping("/reg")
//    public ResponseEntity<ResponseUser> createUserDto(@RequestBody User newUser) {
//        return ok(serviceAppUser.registerUser(newUser));
//    }


//    @GetMapping("/{userDtoName}/userBillList")
//    public ResponseEntity<ResponseUserBill> getUserBill(@PathVariable("userDtoName") String userDtoName) {
//
////        if(userBill == null){
////            return notFound().build();
////        }
////        return ok(converter.convert(userBill));
////    }
//    }
}