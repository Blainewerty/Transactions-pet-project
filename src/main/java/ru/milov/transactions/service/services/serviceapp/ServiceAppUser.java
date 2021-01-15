package ru.milov.transactions.service.services.serviceapp;

import ru.milov.transactions.service.services.ServiceFactory;
import ru.milov.transactions.service.services.ServiceSecurity;
import ru.milov.transactions.service.services.servicesql.ServiceSQLUser;
import ru.milov.transactions.service.TypeExceptions;
import ru.milov.transactions.service.domain.UserDto;

public class ServiceAppUser {

    private final ServiceSQLUser serviceSqlUser = ServiceFactory.getServiceSqlUser();
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
        serviceSqlUser.updateUserInfo(userDto);
    }

    public UserDto registerUser(UserDto userDto) {
        return serviceSqlUser.insertUserToDb(userDto);
    }

    public UserDto authInApp(String email, String password) throws TypeExceptions {
        UserDto userDto = serviceSecurity.auth(email, password);
        if (userDto != null) {
            return serviceSqlUser.fillingUserInfo(userDto);
        } else throw new TypeExceptions("There is no such user in DB!");
    }
}
