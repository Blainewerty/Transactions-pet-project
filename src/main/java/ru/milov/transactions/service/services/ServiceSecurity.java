package ru.milov.transactions.service.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.milov.transactions.dao.UserDao;
import ru.milov.transactions.service.entity.ServiceUser;
import ru.milov.transactions.service.entity.UserDto;

@Service
@RequiredArgsConstructor
public class ServiceSecurity {

    private final UserDao userDao;
    private final DigestService digestService;
    private final ServiceConverter converter;

    public UserDto checkIfUserInDb(String email, String password) {
        ServiceUser serviceUserCheck = userDao.findByEmail(email);
        if (serviceUserCheck == null) {
            String passwordHash = digestService.digest(password);

            return converter.convertRegister(email, passwordHash);
        }
        return null;
    }


    public UserDto auth(String email, String password) {
        ServiceUser serviceUser = userDao.findByEmail(email);
        if (serviceUser != null) {
            String passwordHash = digestService.digest(password);

            if (passwordHash.equals(serviceUser.getPassword())) {
                return converter.convertAuth(serviceUser);
            }
        }
        return null;
    }
}