package ru.milov.transactions.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.milov.transactions.response.ResponseBill;
import ru.milov.transactions.service.entity.Bill;
import ru.milov.transactions.service.entity.User;
import ru.milov.transactions.service.services.ServiceAppBill;
import java.util.List;
import java.util.stream.Stream;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/bill")
public class ControllerBill {

    private final ServiceAppBill serviceAppBill;


    @Autowired
    public ControllerBill(ServiceAppBill serviceAppBill) {
        this.serviceAppBill = serviceAppBill;
    }

    @GetMapping("/getBill/{nameOfBill}")
    public ResponseEntity<Stream<Bill>> getUserBill(@PathVariable("nameOfBill") String nameOfBill){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ok(user.getBill().stream().filter(i-> i.getName().equals(nameOfBill)));
    }

    @PostMapping("/createBill")
    public ResponseEntity<ResponseBill> createUserBill(@RequestBody Bill newBill){
        User user =  (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ok(serviceAppBill.createUserBill(user, newBill));
    }

    @GetMapping("/billList")
    public ResponseEntity<Stream<Bill>> getUserBillList() {
        User user =  (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ok(user.getBill().stream());
    }
}

