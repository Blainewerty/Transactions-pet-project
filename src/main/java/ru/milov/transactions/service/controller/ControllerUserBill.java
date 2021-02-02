package ru.milov.transactions.service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.milov.transactions.dao.response.ResponseUserBill;
import ru.milov.transactions.dao.repository.RepositoryUserBill;
import ru.milov.transactions.dao.converter.ConverterUserBillToUserBillResponse;
import ru.milov.transactions.dao.response.ResponseUserDto;
import ru.milov.transactions.service.entity.UserBill;
import ru.milov.transactions.service.entity.UserDto;

import java.util.List;
import static java.util.stream.Collectors.toList;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
public class ControllerUserBill {

    @Autowired
    private final RepositoryUserBill repositoryUserBill;
    @Autowired
    private final ConverterUserBillToUserBillResponse converter;

    @GetMapping("/{responseUserDto}/BillList/{userBillName}")
    public ResponseEntity<ResponseUserBill> getUserBill(@PathVariable("userBillName") String BillListName, @PathVariable ResponseUserDto responseUserDto){
        UserBill userBill = repositoryUserBill.findByUserDtoAndName(BillListName, responseUserDto);
        if(userBill == null){
            return notFound().build();
        }
        return ok(converter.convert(userBill));
    }

    @PostMapping("/{userDto}/createBill")
    public ResponseEntity<ResponseUserBill> createUserBill(@PathVariable UserDto userDto, UserBill userBill){
        repositoryUserBill.save(userBill);
        return ok(converter.convert(userBill));
    }

    @GetMapping("/userBillList")
    public List<ResponseUserBill> getUserBillList(@PathVariable ResponseUserDto responseUserDto){
        return repositoryUserBill.findAllByUserDto(responseUserDto)
                .stream()
                .map(converter::convert)
                .collect(toList());
    }
}

