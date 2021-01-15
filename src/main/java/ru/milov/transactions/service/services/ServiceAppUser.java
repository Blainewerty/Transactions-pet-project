package ru.milov.transactions.service.services;

import ru.milov.transactions.service.sqlactions.SQLActionsUser;
import ru.milov.transactions.service.TypeExceptions;
import ru.milov.transactions.service.domain.UserDto;

public class ServiceAppUser {

    SQLActionsUser sqlActionsUser = new SQLActionsUser();
    ServiceSecurity serviceSecurity = ServiceFactory.getServiceSecurity();

    public UserDto checkIfUserInDb(String email, String password) throws TypeExceptions {
        UserDto userDto = serviceSecurity.checkIfUserInDb(email, password);
        if (userDto != null) {
            return userDto;
        } else {
            throw new TypeExceptions("Can't register user with this email!");
        }
    }

    public void updateUserInfo(UserDto userDto) {
        sqlActionsUser.updateUserInfo(userDto);
    }

    public UserDto registerUser(UserDto userDto) {
        return sqlActionsUser.insertUserToDb(userDto);
    }

    public UserDto authInApp(String email, String password) throws TypeExceptions {
        UserDto userDto = serviceSecurity.auth(email, password);
        if (userDto != null) {
            return sqlActionsUser.fillingUserInfo(userDto);
        } else throw new TypeExceptions("There is no such user in DB!");
    }
}
