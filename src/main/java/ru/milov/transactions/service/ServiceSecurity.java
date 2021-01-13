package ru.milov.transactions.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.milov.transactions.dao.UserDao;
import ru.milov.transactions.service.domain.ServiceUser;
import ru.milov.transactions.service.domain.UserDto;

public class ServiceSecurity {

    private final UserDao userDao;
    private final DigestService digestService;
    private final ServiceConverter converter;
    private static Logger log = LogManager.getLogger(UserDao.class.getName());

    public ServiceSecurity(UserDao userDao, DigestService digestService, ServiceConverter converter) {
        this.userDao = userDao;
        this.digestService = digestService;
        this.converter = converter;
    }


    public UserDto auth(String email, String password) {
        log.trace("Starting authorization " + email);
        ServiceUser serviceUser = userDao.findByEmail(email);

        if (serviceUser != null) {
            String passwordHash = digestService.digest(password);

            if (passwordHash.equals(serviceUser.getPassword())) {
                return converter.convert(serviceUser);
            }
        }
        return null;
    }
}