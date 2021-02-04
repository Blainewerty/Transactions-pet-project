package ru.milov.transactions.service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.milov.transactions.dao.response.ResponseBill;
import ru.milov.transactions.dao.response.ResponseUser;
import ru.milov.transactions.service.TypeExceptions;
import ru.milov.transactions.service.entity.Bill;
import ru.milov.transactions.service.entity.User;
import ru.milov.transactions.service.services.ServiceAppBill;

import java.math.BigDecimal;
import java.util.List;
import static java.util.stream.Collectors.toList;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
public class ControllerBill {

    @Autowired
    private final ServiceAppBill serviceAppBill;

    @GetMapping("/BillList/{nameOfBill}")
    public ResponseEntity<ResponseBill> getUserBill(@PathVariable("nameOfBill") String nameOfBill){
        ResponseBill responseBill =  serviceAppBill.getInfoAboutUserBill(nameOfBill);
        if(responseBill == null){
            return notFound().build();
        }
        return ok(responseBill);
    }

    @PostMapping("/createBill")
    public ResponseEntity<ResponseBill> createUserBill(User user, String nameOfBill, BigDecimal balance){
        return ok(serviceAppBill.createUserBill(user, nameOfBill, balance));
    }

    @GetMapping("/{email}/userBillList")
    public List<ResponseBill> getUserBillList(ResponseUser responseUser,@PathVariable ("email") String email ) throws TypeExceptions {
        return serviceAppBill.getInfoAboutAllBillsOfUser(responseUser);
    }
}

