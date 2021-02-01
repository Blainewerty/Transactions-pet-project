package ru.milov.transactions.service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.milov.transactions.response.UserBillResponse;
import ru.milov.transactions.repository.UserBillRepository;
import ru.milov.transactions.service.converter.UserBillToUserBillResponseConverter;
import ru.milov.transactions.service.entity.UserBill;

import java.util.List;
import static java.util.stream.Collectors.toList;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
public class UserBillController {

    private final UserBillRepository userBillRepository;
    private final UserBillToUserBillResponseConverter converter;

    @GetMapping("/userBillList/{userBillName}")
    public ResponseEntity<UserBillResponse> getUserBill(@PathVariable("userBillName") String userBillName){
        UserBill userBill = userBillRepository.findByName(userBillName);
        if(userBill == null){
            return notFound().build();
        }
        return ok(converter.convert(userBill));
    }

    @GetMapping("/userBillList")
    public List<UserBillResponse> getUserBillList(){
        return userBillRepository.findAll()
                .stream()
                .map(converter::convert)
                .collect(toList());
    }
}

