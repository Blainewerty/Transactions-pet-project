package ru.milov.transactions.service.services.servicesql;

import ru.milov.transactions.service.domain.UserDto;

import static ru.milov.transactions.dao.DaoFactory.getUserDao;

public class ServiceSQLUser {

    public UserDto insertUserToDb(UserDto userDto){
        return getUserDao().insert(userDto);
    }

    public UserDto fillingUserInfo(UserDto userDto){
        return getUserDao().findById(userDto);
    }

    public void updateUserInfo(UserDto userDto){
        getUserDao().update(userDto);
    }

}
