package ru.milov.transactions.service.services.serviceapp;

import ru.milov.transactions.dao.DaoFactory;
import ru.milov.transactions.dao.UserDao;
import ru.milov.transactions.service.services.ServiceFactory;
import ru.milov.transactions.service.services.ServiceSecurity;
import ru.milov.transactions.service.TypeExceptions;
import ru.milov.transactions.service.domain.UserDto;

public class ServiceAppUser {

    private final UserDao userDao = DaoFactory.getUserDao();

    private final ServiceSecurity serviceSecurity = ServiceFactory.getServiceSecurity();

    public UserDto checkIfUserInDb(String email, String password) throws TypeExceptions {
        UserDto userDto = serviceSecurity.checkIfUserInDb(email, password);
        if (userDto != null) {
            return userDto;
        } else {
            throw new TypeExceptions("Can't register user with this email!");
        }
    }

    public void updateUserInfo(UserDto userDto) {
        userDao.update(userDto);
    }

    public UserDto registerUser(UserDto userDto) {
        return userDao.insert(userDto);
    }

    public UserDto authInApp(String email, String password) throws TypeExceptions {
        UserDto userDto = serviceSecurity.auth(email, password);
        if (userDto != null) {
            return userDao.findById(userDto);
        } else throw new TypeExceptions("There is no such user in DB!");
    }
}
