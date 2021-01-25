package ru.milov.transactions.service.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.milov.transactions.dao.Dao;
import ru.milov.transactions.view.TypeExceptions;
import ru.milov.transactions.service.domain.UserDto;

@Service
@RequiredArgsConstructor
public class ServiceAppUser {

    private final Dao<UserDto, Integer> userDao;
    private final ServiceSecurity serviceSecurity;

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
