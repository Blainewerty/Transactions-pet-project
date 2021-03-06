package ru.milov.transactions.service.services;

import org.springframework.stereotype.Service;
import ru.milov.transactions.dao.UserDao;
import ru.milov.transactions.service.TypeExceptions;
import ru.milov.transactions.service.domain.UserDto;

@Service
public class ServiceAppUser {

    private final UserDao userDao;
    private final ServiceSecurity serviceSecurity;

    public ServiceAppUser(UserDao userDao, ServiceSecurity serviceSecurity) {
        this.userDao = userDao;
        this.serviceSecurity = serviceSecurity;
    }


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

    public boolean deleteUserFromDb(UserDto userDto){
        return userDao.delete(userDto.getId());
    }
}
