package ru.milov.transactions.service.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import ru.milov.transactions.dao.UserDao;
import ru.milov.transactions.service.domain.ServiceUser;
import ru.milov.transactions.service.domain.UserDto;

@Service
public class ServiceSecurity {

    private final UserDao userDao;
    private final DigestService digestService;
    private final ServiceConverter converter;
    private static final Logger log = LogManager.getLogger(UserDao.class.getName());

    public ServiceSecurity(UserDao userDao, DigestService digestService, ServiceConverter converter) {
        this.userDao = userDao;
        this.digestService = digestService;
        this.converter = converter;
    }


    public UserDto checkIfUserInDb(String email, String password) {
        ServiceUser serviceUserCheck = userDao.findByEmail(email);
        if (serviceUserCheck == null) {
            String passwordHash = digestService.digest(password);

            return converter.convertRegister(email, passwordHash);
        }
        return null;
    }


    public UserDto auth(String email, String password) {
        log.trace("Starting authorization " + email);
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