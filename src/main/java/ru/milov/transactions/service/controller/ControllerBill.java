package ru.milov.transactions.service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.milov.transactions.dao.response.ResponseBill;
import ru.milov.transactions.dao.response.ResponseUser;
import ru.milov.transactions.service.TypeExceptions;
import ru.milov.transactions.service.entity.User;
import ru.milov.transactions.service.services.ServiceAppBill;
import java.math.BigDecimal;
import java.util.List;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bill")
public class ControllerBill {

    @Autowired
    private final ServiceAppBill serviceAppBill;

    @GetMapping("/getBill/{nameOfBill}")
    public ResponseEntity<ResponseBill> getUserBill(@PathVariable("nameOfBill") String nameOfBill){
        ResponseBill responseBill =  serviceAppBill.getInfoAboutUserBill(nameOfBill);
        if(responseBill == null){
            return notFound().build();
        }
        return ok(responseBill);
    }

    @PostMapping("/createBill")
    public ResponseEntity<ResponseBill> createUserBill(Long id, String nameOfBill, BigDecimal balance){
        return ok(serviceAppBill.createUserBill(id, nameOfBill, balance));
    }

    @GetMapping("/billList")
    public List<ResponseBill> getUserBillList(ResponseUser responseUser ) throws TypeExceptions {
        return serviceAppBill.getInfoAboutAllBillsOfUser(responseUser);
    }
}

