package ru.milov.transactions.service.controller;

import com.fasterxml.jackson.databind.util.BeanUtil;
import liquibase.pro.packaged.U;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.milov.transactions.dao.converter.ConverterUserDtoToUserDtoResponse;
import ru.milov.transactions.dao.repository.RepositoryUserDto;
import ru.milov.transactions.dao.response.ResponseUserDto;
import ru.milov.transactions.service.entity.UserDto;
import ru.milov.transactions.service.services.DigestService;

import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
public class ControllerUserDto {

    @Autowired()
    RepositoryUserDto repositoryUserDto;
    @Autowired()
    DigestService digestService;
    @Autowired()
    ConverterUserDtoToUserDtoResponse converter;

    @GetMapping("/login")
    public ResponseEntity<ResponseUserDto> findUserDto(@RequestBody UserDto requestUserDto) {
        requestUserDto.setPassword(digestService.digest(requestUserDto.getPassword()));
        UserDto userDto = repositoryUserDto.findByEmailAndPassword(requestUserDto.getEmail(), requestUserDto.getPassword());
        if (userDto == null) {
            return notFound().build();
        }
        return ok(converter.convert(requestUserDto));
    }

    @PostMapping("/reg")
    public ResponseEntity<ResponseUserDto> createUserDto(@RequestBody UserDto newUserDto) {
        newUserDto.setPassword(digestService.digest(newUserDto.getPassword()));
        repositoryUserDto.save(newUserDto);
        return ok(converter.convert(newUserDto));
    }


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