//package ru.milov.transactions.service.services;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import ru.milov.transactions.dao.response.ResponseUserDto;
//import ru.milov.transactions.service.entity.UserDto;
//
//@Service
//@RequiredArgsConstructor
//public class ServiceSecurity {
//
//    private final UserDao userDao;
//    private final DigestService digestService;
//    private final ServiceConverter converter;
//
//    public UserDto checkIfUserInDb(String email, String password) {
//        ResponseUserDto responseUserDtoCheck = userDao.findByEmail(email);
//        if (responseUserDtoCheck == null) {
//            String passwordHash = digestService.digest(password);
//
//            return converter.convertRegister(email, passwordHash);
//        }
//        return null;
//    }
//
//
//    public UserDto auth(String email, String password) {
//        ResponseUserDto responseUserDto = userDao.findByEmail(email);
//        if (responseUserDto != null) {
//            String passwordHash = digestService.digest(password);
//
//            if (passwordHash.equals(responseUserDto.getPassword())) {
//                return converter.convertAuth(responseUserDto);
//            }
//        }
//        return null;
//    }
//}