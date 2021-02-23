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
import static org.springframework.http.ResponseEntity.notFound;
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
    public ResponseEntity<ResponseBill> getUserBill(@PathVariable("nameOfBill") String nameOfBill){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ResponseBill responseBill =  serviceAppBill.getInfoAboutUserBill(user.getId(), nameOfBill);
        if(responseBill == null){
            return notFound().build();
        }
        return ok(responseBill);
    }

    @PostMapping("/createBill")
    public ResponseEntity<ResponseBill> createUserBill(@RequestBody Bill newBill){
        User user =  (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ok(serviceAppBill.createUserBill(user, newBill));
    }

    @GetMapping("/billList")
    public List<ResponseBill> getUserBillList() {
        User user =  (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return serviceAppBill.getInfoAboutAllBillsOfUser(user.getId());
    }
}

