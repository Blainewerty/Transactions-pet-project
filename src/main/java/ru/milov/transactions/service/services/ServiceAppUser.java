package ru.milov.transactions.service.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.milov.transactions.dao.converter.ConverterUserToUserResponse;
import ru.milov.transactions.dao.repository.RepositoryUser;
import ru.milov.transactions.dao.response.ResponseUser;
import ru.milov.transactions.service.TypeExceptions;
import ru.milov.transactions.service.entity.User;

@Service
@RequiredArgsConstructor
public class ServiceAppUser {

    @Autowired
    RepositoryUser repositoryUser;
    @Autowired
    DigestService digestService;
    @Autowired
    ConverterUserToUserResponse converter;

    public User checkIfUserInDb(User requestUser) throws TypeExceptions {
        User user = repositoryUser.findByEmail(requestUser.getEmail());
        if (user != null) {
            return user;
        } else {
            throw new TypeExceptions("Can't register user with this email!");
        }
    }

//    public void updateUserInfo(UserDto userDto) {
//        userDao.update(userDto);
//    }

    public ResponseUser registerUser(User userDto) {
        userDto.setPassword(digestService.digest(userDto.getPassword()));
        return converter.convert(repositoryUser.save(userDto));
    }

    public ResponseUser authInApp(User requestUser) throws TypeExceptions {
        User user = checkIfUserInDb(requestUser);
        if (user != null) {
            return converter.convert(repositoryUser.findByEmailAndPassword(user.getEmail(), user.getPassword()));
        } else throw new TypeExceptions("There is no such user in DB!");
    }

//    public boolean deleteUserFromDb(UserDto userDto){
//        return userDao.delete(userDto.getUser_id());
//    }
}
